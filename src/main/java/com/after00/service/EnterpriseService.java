package com.after00.service;

import com.after00.common.BaseResponse;
import com.after00.entity.EnterpriseInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "enterprise")
public interface EnterpriseService {

    //修改时候使用这个注解
//    @Cacheable
    BaseResponse queryEnterprisePager(int pagerNum, int pageSize, EnterpriseInfo enterpriseInfo);

    //    @CachePut(value = "userCache", key = "#enterpriseInfo.id", unless="#result == null")
    @CachePut(key = "#enterpriseInfo.id", unless = "#result == null")
    EnterpriseInfo updateEnterprise(EnterpriseInfo enterpriseInfo);

    @CacheEvict(value = "enterprise", allEntries = true)
        //所有缓存
//    @CacheEvict(key = "#id")
//    @CacheEvict(value = "userCache",key = "#id")
    BaseResponse deleteEnterPrise(int id);

    //    @Cacheable(value = "userCache", key = "#id", unless="#result == null")
    @Cacheable(key = "#id", unless = "#result == null")
    EnterpriseInfo queryEnterpriseById(int id);


    BaseResponse uploadTest(String url, String methon, String jsonString);
}
