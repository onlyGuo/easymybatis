package com.aiyi.core.plugins.field;

/**
 * @Author: 郭胜凯
 * @Date: 2019-07-23 16:32
 * @Email 719348277@qq.com
 * @Description: 将某些特殊类型的字段值格式化为SQL支持的字符串接口类
 */
public interface FieldTypeFmt {

    /**
     * 将制定类型的对象格式化为可拼入SQL的字符串
     * @param fieldValue
     *      字段值
     * @return
     */
    String fmt(Object fieldValue);

}
