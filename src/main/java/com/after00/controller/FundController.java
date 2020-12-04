package com.after00.controller;

import com.after00.common.BaseResponse;
import com.after00.service.FundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * fund查询
 *
 * @author wangbiao
 */
@Api(tags = "Redis操作")
@RestController
@RequestMapping(value = "/redis")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundController {


    final FundService fundService;
    /**
     * 查询明细
     */
    @ApiOperation(value = "获取今日", response = BaseResponse.class)
    @GetMapping("/getTodayEarningsById")
    public BaseResponse getTodayEarningsByUserId(@RequestParam(name = "userId") Integer userId) {
       return fundService.getTodayEarningsByUserId(userId);
    }


    @ApiOperation(value = "添加数据", response = BaseResponse.class)
    @GetMapping("/addFundByCode")
    public BaseResponse addFundByCode(@RequestParam(name = "code") String code, @RequestParam(name = "money")BigDecimal money,@RequestParam(name = "userId") Integer userId) {
        return fundService.addFundByCode(code,money,userId);
    }

}
