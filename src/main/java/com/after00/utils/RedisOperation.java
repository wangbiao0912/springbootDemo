package com.after00.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,永无bug
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　━━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + +
 * <p>
 * %Auther    panqq
 */
@Component
@Scope("singleton")
public class RedisOperation {

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisOperation.redisTemplate = redisTemplate;
    }

    /**
     * 向已有列表队尾添加单个元素,返回当前列表元素数量
     *
     * @param key
     * @param value
     * @return
     */
    public static long pushSingleToList(String key, Object value) {
        long index = redisTemplate.opsForList().rightPush(key, JSON.toJSONString(value));
        return index;
    }

    /**
     * 向已有列表队尾批量添加元素，返回当前列表元素数量
     *
     * @param key
     * @param values
     * @return
     */
    public static long pushAllToList(String key, List<String> values) {
        long index = redisTemplate.opsForList().rightPushAll(key, values);
        return index;
    }

    /**
     * 获取列表头部第一个元素并从列表中删除
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T popSingleFromList(String key, Class<T> clazz) {
        String resultStr = redisTemplate.opsForList().leftPop(key, 1000, TimeUnit.MILLISECONDS);
        return JSON.parseObject(resultStr, clazz);
    }

    /**
     * 获取指定列表中元素数量
     *
     * @param key
     * @return
     */
    public static long countSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 获取列表的最后一个元素
     *
     * @param key
     * @return
     */
    public static String catchMax(String key) {
        return redisTemplate.opsForList().index(key, -1);
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public static Boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }
}
