package com.after00.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2017/8/16.
 */
public class RedisOptionsUtils {
    /**
     * 获取缓存
     *
     * @param token
     * @return
     */
    public static String getCached(RedisTemplate<String, String> redisTemplate, String token) {
        if (token == null || "".equals(token))
            return null;
        String prik = redisTemplate.opsForValue().get(token);
        return prik;
    }

    /**
     * 删除缓存
     *
     * @param redisTemplate
     * @param token
     */
    public static void deleteCached(RedisTemplate<String, String> redisTemplate, String token) {
        redisTemplate.opsForValue().getOperations().delete(token);
    }

    /**
     * 创建缓存
     *
     * @param redisTemplate
     * @param token
     * @param strValue
     */
    public static void createCached(RedisTemplate<String, String> redisTemplate, String token, String strValue) {
        redisTemplate.opsForValue().set(token, strValue);
    }

    public static void createCachedByTime(RedisTemplate<String, String> redisTemplate, String token, String strValue, long time) {
        redisTemplate.opsForValue().set(token, strValue, time, TimeUnit.MINUTES);
    }


    /**
     * 更新缓存失效日期
     *
     * @param redisTemplate
     * @param token
     * @param time
     */
    public static void expireCachedByTime(RedisTemplate<String, String> redisTemplate, String token, long time) {
        redisTemplate.expire(token, time, TimeUnit.MINUTES);
    }

}
