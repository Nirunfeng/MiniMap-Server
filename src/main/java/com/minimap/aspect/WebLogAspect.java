package com.minimap.aspect;

import com.minimap.annotation.PointcutAnnotation;
import com.minimap.utils.HttpContextUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志类，Aspect切片，执行方法不同时刻日志打印不同信息，进行切片处理
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {
    private final Logger logger= LoggerFactory.getLogger(WebLogAspect.class);
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private HttpContextUtils request;

    /**
     * 配置切入点@Pointcut--->切入点，自定义注解修饰的方法
     * 第一个*表示返回任何类型,com.cmh.aop.controller下任何类,任何方法,任何参数
     * 也可以加入参数限定例如com.cmh.aop.controller.*.*(..)&&args(name,..)
     * <p>
     * 下面那中表示方法也是对的,表示com.cmh.aop.下面任何子包下任何方法,任何参数
     **/
    @Pointcut("@annotation(pointcutAnnotation)")
    public void webLog(PointcutAnnotation pointcutAnnotation) {
    }

    /**
     *
     * @param joinPoint
     * @param pointcutAnnotation
     * @throws Throwable
     */
    @Before("webLog(pointcutAnnotation)")
    public void  doBefore(JoinPoint joinPoint, PointcutAnnotation pointcutAnnotation) throws Throwable{
        long time = pointcutAnnotation.timeout();
        startTime.set(System.currentTimeMillis());
        System.out.println(time);
        // 接收到请求，记录请求内容

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ipAddress = HttpContextUtils.getIpAddress();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        logger.info("ip:" + ipAddress);
    }
}
