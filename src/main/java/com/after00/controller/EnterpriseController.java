package com.after00.controller;

import com.after00.entity.EnterpriseInfo;
import com.after00.entity.dto.EnterpriseInfoDto;
import com.after00.service.EnterpriseService;
import com.after00.entity.EnterpriseDTOConvert;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    @PostMapping(value = "test")
    public EnterpriseInfo addUser(@Valid EnterpriseInfoDto enterpriseInfoDto, BindingResult bindingResult) {
        checkDTOParams(bindingResult);

        EnterpriseInfo enterpriseInfo = (EnterpriseInfo) new EnterpriseDTOConvert().convert(enterpriseInfoDto);
        //bean 中的链式风格
//        EnterpriseInfo student = new EnterpriseInfo().setId("22").setName("zs");
//        EnterpriseInfo enterpriseInfo1=EnterpriseInfo.ofName().setAddress("222")
        //Builder 模式我不想再多解释了，读者可以看一下《Head First》(设计模式) 的建造者模式
        EnterpriseInfo enterpriseInfo2 = EnterpriseInfo.builder().addRess("s").id("2").build();
        List<String> list = Lists.newArrayList();
        HashMap<String, String> objectObjectHashMap = Maps.newHashMap();
        //代理模式  对异常捕获  @Delegate
        return enterpriseInfo;
    }

    private void checkDTOParams(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // return  OrderResponse.getFailResponse(orderPaymentManagementParam.getTransid(),orderPaymentManagementParam.getOrderno(),bindingResult.getFieldError().getDefaultMessage());
            //throw new 带验证码的验证错误异常
            //jsr 303 验证
        }
    }

}
