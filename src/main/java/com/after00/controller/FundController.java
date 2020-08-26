package com.after00.controller;

import com.after00.common.BaseResponse;
import com.after00.service.FundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * 查询收益明细
     */
    @ApiOperation(value = "获取今日收益", response = BaseResponse.class)
    @GetMapping("/getTodayEarningsById")
    public BaseResponse getTodayEarningsByUserId(@RequestParam(name = "userId") String userId) {
       return fundService.getTodayEarningsByUserId(userId);
    }


}
