package com.aiyi.core.annotation.po;

import java.lang.annotation.*;

/**
 * 标识某个字段不录入数据库中
 * 标识该注解的字段将没有增删改查的功能。
 * @author Administrator
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TempField {

	 
}
