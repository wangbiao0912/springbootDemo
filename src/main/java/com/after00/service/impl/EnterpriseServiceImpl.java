package com.after00.service.impl;

import com.after00.common.BaseResponse;
import com.after00.entity.EnterpriseInfo;
import com.after00.mapper.EnterpriseInfoMapper;
import com.after00.service.EnterpriseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service("EnterpriseServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    EnterpriseInfoMapper enterpriseInfoMapper;

    @Override
    public BaseResponse queryEnterprisePager(int pageNum, int pageSize, EnterpriseInfo enterpriseInfo) {
        PageHelper.startPage(pageNum, pageSize);
        List<EnterpriseInfo> enterpriseInfoArrayList = enterpriseInfoMapper.queryAllEnterprisePage();
        PageInfo<EnterpriseInfo> pageInfo = new PageInfo<>(enterpriseInfoArrayList);
        return BaseResponse.getSuccessResponse(pageInfo, "成功请求");
    }

    @Override
    public EnterpriseInfo updateEnterprise(EnterpriseInfo enterpriseInfo) {
        return enterpriseInfo;

    }

    @Override
    public BaseResponse deleteEnterPrise(int id) {
        return BaseResponse.getSuccessResponse(enterpriseInfoMapper.deleteEnterPrise(id), "成功请求");

    }

    @Override
    public EnterpriseInfo queryEnterpriseById(int id) {
        log.info(Locale.getDefault() + "：：：");
        return enterpriseInfoMapper.findEnterpriseById(id);
    }


    @Override
    public BaseResponse uploadTest(String url, String method, String json) {
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put("userId", "userId");
        stringHashMap.put("apppId", "apppId");
        try {
            //调用第三方接口
            String tempResultString = sync(stringHashMap);
            log.info("-----{}", tempResultString);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.getParamsFailedResponse("返回数据格式错误");
        }
        return BaseResponse.getSuccessResponse("成功", "操作成功");
    }

    /**
     * 异步调用
     */
    @Async
    public String sync(HashMap<String, String> stringHashMap) {
        return restTemplate.getForObject("https://baidu.com", String.class, stringHashMap.toString());
    }
}
