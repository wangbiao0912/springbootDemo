package com.after00.entity;

import com.after00.utils.BeanConvert;
import org.springframework.beans.BeanUtils;

public class EnterpriseDTOConvert implements BeanConvert {

    @Override
    public Object convert(Object o) {
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        BeanUtils.copyProperties(o, enterpriseInfo);
        return enterpriseInfo;
    }
}
