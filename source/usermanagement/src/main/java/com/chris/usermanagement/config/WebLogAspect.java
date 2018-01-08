package com.chris.usermanagement.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * The type Web log aspect.
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {

    private static Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * The Start time.
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * Web log.
     */
    @Pointcut("execution(public * com.chris.usermanagement.controller..*.*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void webLog() {
    }

    /**
     * Do before.
     *
     * @param joinPoint the join point
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        LOGGER.info("start--------------------------------------------------------start");
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        LOGGER.info("请求URL : " + request.getRequestURL().toString() + "||" + "请求方式 : " + request.getMethod() + "||"
                + "请求IP : " + request.getRemoteAddr() + "||" + "请求方法 : "
                + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("请求属性 : " + Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * Do after returning.
     *
     * @param ret the ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        LOGGER.info("响应内容 : " + ret + "||" + "耗时 : " + (System.currentTimeMillis() - startTime.get()));
        LOGGER.info("end------------------------------------------------------end");
    }

}