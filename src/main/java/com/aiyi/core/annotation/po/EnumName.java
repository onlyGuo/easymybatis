package com.aiyi.core.annotation.po;

import java.lang.annotation.*;

/**
 * @Author: 郭胜凯
 * @Date: 2019-06-10 09:19
 * @Email 719348277@qq.com
 * @Description: 枚举名称
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnumName {
    String value() default "";
}