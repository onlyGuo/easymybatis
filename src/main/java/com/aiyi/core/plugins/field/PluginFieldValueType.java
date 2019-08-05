package com.aiyi.core.plugins.field;

/**
 * @Author: 郭胜凯
 * @Date: 2019-07-23 16:42
 * @Email 719348277@qq.com
 * @Description: 插件字段值类型
 */
public @interface PluginFieldValueType {
    Class<?> value() default String.class;
}
