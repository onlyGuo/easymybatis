package com.aiyi.core.annotation.po;

import java.lang.annotation.*;

/**
 * 自动日志, 添加到指定方法中将自动打印日志
 * @author guosk
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
	String action() default "null";
	
}
