package com.aiyi.core.util;

/**
 * @Author: 郭胜凯
 * @Date: 2019-09-19 08:44
 * @Email 719348277@qq.com
 * @Description:
 */
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 找到空值的字段
     * @param source
     *      含有空值字段的对象
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null){
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 拷贝实体类中的字段值到目标字段，并且忽略空值的字段
     * @param src
     *      源实体
     * @param target
     *      目标实体
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        copyProperties(src, target, getNullPropertyNames(src));
    }
}