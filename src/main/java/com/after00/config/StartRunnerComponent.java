package com.after00.config;


import com.after00.utils.RedisOptionsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@Order(value = 3)
public class StartRunnerComponent implements CommandLineRunner {

    //	@Autowired
//	MasterMapper masterMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        //查询所有的行区域
//        RedisOptionsUtils.createCached(redisTemplate, "22", "xx");
        log.info("程序启动---执行方法");
    }
}
