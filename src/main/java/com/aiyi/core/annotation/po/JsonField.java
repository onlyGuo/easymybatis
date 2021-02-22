package com.aiyi.core.annotation.po;

import java.lang.annotation.*;

/**
 * 标识该字段为JSON格式
 * @author 郭胜凯
 * @time 2016年2月26日下午3:32:40
 * @email 719348277@qq.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonField {
//    Class<?> value();
}
