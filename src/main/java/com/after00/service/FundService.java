package com.after00.service;

import com.after00.common.BaseResponse;

public interface FundService {
    BaseResponse getTodayEarningsByUserId(String userId);
}
