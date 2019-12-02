package com.after00.aspects;//package com.htwl.enterprise.aspects;


import com.after00.common.BaseResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Created by panqq on 2017/4/10.
 */
@Aspect
@Component
@Order(2)
@Slf4j
public class SecurityAspect {
    @Around("execution(public * com.after00"
            + ".controller.*.*(..)) ")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object rvt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 记录操作日志
        StringBuffer logMap = new StringBuffer("");
        logMap.append("REQ_TIME: " + sdf.format(new Date()) + ";");
        logMap.append("URL: " + request.getRequestURL().toString() + ";");
        logMap.append("HTTP_METHOD: " + request.getMethod() + ";");
        logMap.append("IP: " + request.getRemoteAddr() + ";");
        logMap.append("ARGS: " + proceedingJoinPoint.getArgs());
        System.out.println(logMap.toString());
        long time1 = System.currentTimeMillis();
        try {
            rvt = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            rvt = BaseResponse.getFailResponse(500, "", "服务端问题，请联系管理员");
            e.printStackTrace();
            System.out.println("-----------------------------------   service continue -------------------------------");
            return rvt;
        } finally {
            log.info("返回结果：" + JSON.toJSONString(rvt) + "于" + sdf.format(new Date()) + ">>>>>接口地址：" + request.getRequestURL() + ">>>>请求耗时：" + (System.currentTimeMillis() - time1));
            return rvt;
        }
    }

}
