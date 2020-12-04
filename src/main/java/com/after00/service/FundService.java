package com.after00.service;

import com.after00.common.BaseResponse;
import java.math.BigDecimal;

public interface FundService {

  BaseResponse getTodayEarningsByUserId(Integer userId);

  BaseResponse addFundByCode(String code, BigDecimal money, Integer userId);
}
