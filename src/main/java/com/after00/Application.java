package com.after00;

import java.nio.charset.Charset;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


/**
 * @author wangbiao
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching  //开启缓存
@EnableScheduling
@MapperScan("com.after00.mapper")
//@EnableNeo4jRepositories
public class Application {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return builder.additionalMessageConverters(m).build();

//        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//
//        return builder.additionalMessageConverters(m)
//            // 连接超时设置 3s
//            .setConnectTimeout(Duration.ofMillis(3000))
//            // 读取超时设置 2s
//            .setReadTimeout(Duration.ofMillis(2000))
//            .build();
    }

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "true");
        SpringApplication.run(Application.class, args);
    }


}
