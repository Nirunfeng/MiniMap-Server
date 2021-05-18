package com.minimap.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *自定义监听的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PointcutAnnotation {
    /**
     * 指定时间内不可重复提交,单位毫秒
     *
     * 暂时不用
     */
    long timeout() default 30000;
}
