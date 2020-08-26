package com.after00.service.impl;

import com.after00.common.BaseResponse;
import com.after00.entity.FundList;
import com.after00.entity.vo.FundRequestFundgz;
import com.after00.mapper.FundListMapper;
import com.after00.service.FundService;
import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundServiceImpl implements FundService {

  @Resource
  FundListMapper fundListMapper;

  final RestTemplate restTemplate;
  String url = "http://fundgz.1234567.com.cn/js/{code}.js";

  @Override
  public BaseResponse getTodayEarningsByUserId(String userId) {
    BigDecimal sumMoney = new BigDecimal(0);
    BigDecimal sumEarnings = new BigDecimal(0);

    List<String> list = new ArrayList<>();
    list.add("| 名称 | code | 收益率 | 当前收益 | 份量 | 净值 | 投资 |");
    list.add("| :----- | ----: | :----: | :----: | :----: | :----: | :----: |");

    List<FundList> fundListList = fundListMapper.getTodayEarningsByUserId(userId);
    for (FundList fundList : fundListList) {
      String fundCode = fundList.getFundCode();
      String tempUrl = url.replace("{code}", fundCode);
      try {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(tempUrl, String.class);
        log.info("基金code:{},id={},返回状态：{},返回数据：{}", fundCode, fundList.getId(), responseEntity.getStatusCode(), responseEntity.getBody());
        String body = responseEntity.getBody().replace("jsonpgz(", "").replace(");", "");
        FundRequestFundgz fundRequestFundgz = JSON.toJavaObject(JSON.parseObject(body), FundRequestFundgz.class);
        if (fundRequestFundgz == null) {
          continue;
        }
        fundList.setFundName(fundRequestFundgz.getName());
        fundList.setYesterdayNetWorth(fundRequestFundgz.getDwjz());
        fundList.setFundNetWorth(fundRequestFundgz.getGsz());
        fundList.setUpdateTime(fundRequestFundgz.getGztime());
        fundList.setYesterdayTime(fundRequestFundgz.getJzrq());
        fundList.setNetRate(fundRequestFundgz.getGszzl());
        BigDecimal earnings = (fundList.getFundNetWorth().subtract(fundList.getYesterdayNetWorth())).multiply(fundList.getFundShare());
        // 计算收益
        log.info("基金code:{},当前收益{}", fundCode, earnings.toString());
        sumEarnings = sumEarnings.add(earnings);
        fundList.setCurrentYield(earnings);
        fundListMapper.updateByPrimaryKey(fundList);
        String aColor="red";
        String bColor="red";

        Integer a = earnings.compareTo(BigDecimal.ZERO);
        //a = -1,表示bd1小于bd2；
        //a = 0,表示bd1等于bd2；
        //a = 1,表示bd1大于bd2；
        if (a == -1) {
          //小于
          aColor="#ADD8E6";
        }
        Integer b = fundList.getNetRate().compareTo(BigDecimal.ZERO);
        if (b==-1) {
          //小于
          bColor="#ADD8E6";
        }
//        list.add("| 基金名："+fundList.getFundName()+"     |    基金code："+fundCode+"，   |     收益率："+fundList.getNetRate()+"，      |  当前收益:"+ earnings.toString()+"|");
        list.add("| " + fundList.getFundName() + " | " + fundCode + " | <font color="+bColor+">"+fundList.getNetRate()+"</font> | <font color="+aColor+">"+earnings.toString()+"</font> | "+fundList.getFundShare()+" | "+ fundList.getFundNetWorth() +" |" +fundList.getFundShare().subtract(fundList.getFundNetWorth()) +" |");
        sumMoney=sumMoney.add(fundList.getFundShare().subtract(fundList.getFundNetWorth()));
      } catch (RestClientException e) {
        e.printStackTrace();
        log.error("{},基金报错了{}", url, e.getMessage());
        continue;
      }
    }
    return BaseResponse.getSuccessResponse(list, "总共收益：" + sumEarnings+"  总投资："+sumMoney);
  }
}
