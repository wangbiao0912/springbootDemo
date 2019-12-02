package com.after00.service;

import com.after00.entity.EnterpriseInfo;
import com.after00.service.impl.EnterpriseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 雪崩：因某一时刻，缓存集中失效导致
 *
 * 解决办法：加锁
 */
@Slf4j
//@Service("EnterpriseInfoServiceImpl3")
@CacheConfig(cacheNames = "enterprise")
class EnterpriseInfoServiceImpl3 extends EnterpriseServiceImpl {
    @Resource
    private CacheManager cm;
    private ConcurrentHashMap<Integer, ReentrantLock> locks = new ConcurrentHashMap<Integer, ReentrantLock>();//线程安全的

    private static final String CACHE_NAME = "province";

    //
    public EnterpriseInfo detail(int provinceid) {
        // 1.从缓存中取数据
        Cache.ValueWrapper valueWrapper = cm.getCache(CACHE_NAME).get(provinceid);
        if (valueWrapper != null) {
            log.info("缓存中得到数据");
            return (EnterpriseInfo) (valueWrapper.get());
        }

        //2.加锁排队，阻塞式锁
        doLock(provinceid);//32个省，最多只有32把锁，1000个线程
        try {//第二个线程进来了
            // 一次只有一个线程
            //双重校验，不加也没关系，无非是多刷几次库
            valueWrapper = cm.getCache(CACHE_NAME).get(provinceid);//第二个线程，能从缓存里拿到值？
            if (valueWrapper != null) {
                log.info("缓存中得到数据");
                return (EnterpriseInfo) (valueWrapper.get());//第二个线程，这里返回
            }

            EnterpriseInfo EnterpriseInfo = super.queryEnterpriseById(provinceid);
            // 3.从数据库查询的结果不为空，则把数据放入缓存中，方便下次查询
            if (null != EnterpriseInfo) {
                cm.getCache(CACHE_NAME).put(provinceid, EnterpriseInfo);
            }
            return EnterpriseInfo;
        } catch (Exception e) {
            return null;
        } finally {
            //4.解锁
            releaseLock(provinceid);
        }
    }

    private void releaseLock(int userCode) {
        ReentrantLock oldLock = (ReentrantLock) locks.get(userCode);
        if (oldLock != null && oldLock.isHeldByCurrentThread()) {
            oldLock.unlock();
        }
    }


    private void doLock(int lockcode) {

        //provinceid有不同的值，参数多样化
        //provinceid相同的，加一个锁，---- 不是同一个key，不能用同一个锁

        ReentrantLock newLock = new ReentrantLock();//创建一个锁

        Lock oldLock = locks.putIfAbsent(lockcode, newLock);//若已存在，则newLock直接丢弃
        if (oldLock == null) {
            newLock.lock();
        } else {
            oldLock.lock();
        }
    }
}
