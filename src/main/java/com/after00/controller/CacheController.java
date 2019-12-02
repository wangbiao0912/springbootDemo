package com.after00.controller;

import com.after00.common.BaseResponse;
import com.after00.entity.EnterpriseInfo;
import com.after00.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "接口测试")
@RestController
@RequestMapping("testController")
public class CacheController {


//    @Qualifier("EnterpriseServiceImpl")
//    @Autowired
//    EnterpriseServiceImpl enterpriseService;
    @Qualifier("EnterpriseServiceImpl")
    @Autowired
//    @Resource(name ="EnterpriseServiceImpl" )
    EnterpriseService enterpriseService;

    @ApiOperation(value = "国家接口上传测试", notes = "")
    @ApiImplicitParam(name = "id", value = "测试", paramType = "path", required = true, dataType = "String")
    @PostMapping(value = "uploadTest")
    public BaseResponse uploadTest(@RequestParam(name = "url", required = true) String url, @RequestParam(name = "method", required = true) String method, @RequestParam(name = "json", required = true) String json) {
        return enterpriseService.uploadTest(url, method, json);
    }

    @PostMapping(value = "queyrEnterprisePager")
    public BaseResponse queyrEnterprisePager(int pageNum, int pageSize, EnterpriseInfo enterpriseInfo) {
        return enterpriseService.queryEnterprisePager(pageNum, pageSize, enterpriseInfo);
    }

    @PostMapping(value = "updateEnterPrise")
    public BaseResponse updateEnterPrise(EnterpriseInfo enterpriseInfo) {
        return BaseResponse.getSuccessResponse(enterpriseService.updateEnterprise(enterpriseInfo), "成功请求");
    }

    @PostMapping(value = "deleteEnterPrise")
    public BaseResponse deleteEnterPrise(int id) {
        return BaseResponse.getSuccessResponse(enterpriseService.deleteEnterPrise(id), "成功请求");
    }

    @PostMapping(value = "queryEnterpriseById")
    public BaseResponse queryEnterpriseById(Integer id) {
        return BaseResponse.getSuccessResponse(enterpriseService.queryEnterpriseById(id), "成功请求");

    }
}
