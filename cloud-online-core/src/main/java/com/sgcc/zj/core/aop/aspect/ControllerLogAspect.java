package com.sgcc.zj.core.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgcc.zj.core.aop.annotation.PGControllerMonitor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author liyingjie
 * @Title: ControllerLogAspect
 * @Description: 日志切面
 * @date 2019/1/17
 */
@Aspect
@Component
public class ControllerLogAspect {

    private static final Logger log = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("@annotation(com.sgcc.zj.core.aop.annotation.PGControllerMonitor)")
    public void controllerAspect() {
    }

    @Before("controllerAspect() && @annotation(PG)")
    public void doBefore(JoinPoint joinPoint, PGControllerMonitor PG) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Map<String, String[]> paramMaps = request.getParameterMap();
        String paramMap = JSONObject.toJSONString(paramMaps);
        log.info("请求request ==> url:[{}], class:[{}], method:[{}], paramters:[{}], ip:[{}]", new Object[]{
                request.getRequestURL(), className, methodName, paramMap, request.getRemoteAddr()});
    }

    @AfterReturning(value = "controllerAspect()", returning = "retVal")
    public void doAfter(JoinPoint joinPoint, Object retVal) {
        //获取response
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        // 返回参数
        String responseStr = "";
        if (retVal != null) {
            responseStr = JSON.toJSONString(retVal);
        }
        // 响应内容
        log.info("返回response ==> class:[{}], method:[{}], paramters:[{}]", className, methodName, responseStr);
    }

}
