package com.aiyi.core.plugins;

import com.aiyi.core.plugins.field.FieldTypeFmt;
import com.aiyi.core.plugins.field.PluginFieldValueType;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: 郭胜凯
 * @Date: 2019-07-23 16:45
 * @Email 719348277@qq.com
 * @Description: 插件管理
 */
public class PluginManager {

    protected static final Logger LOGGER = LoggerFactory.getLogger(PluginManager.class);
    private static final String packageName = "com.aiyi.core.plugins";

    /**
     * 字段类型格式化插件
     */
    private static Map<Class<?>, FieldTypeFmt> fieldTypeFmtPluginMap = new HashMap<>();
    static{
        LOGGER.info("正在初始化字段类型格式化插件...");
        Reflections f = new Reflections(packageName);
        Set<Class<?>> set = f.getTypesAnnotatedWith(PluginFieldValueType.class);
        for (Class<?> c : set) {
            LOGGER.info("正在注册 字段类型格式化 插件:{}", c.getName());
            FieldTypeFmt bean = null;
            try {
                bean = (FieldTypeFmt)c.newInstance();
            } catch (InstantiationException|IllegalAccessException e) {
                e.printStackTrace();
            }
            PluginFieldValueType webhoot = c.getAnnotation(PluginFieldValueType.class);
            if (null != webhoot){
                fieldTypeFmtPluginMap.put(webhoot.value(), bean);
            }
        }
    }


    /**
     * 获取字段类型对应的格式化插件
     * @param clazz
     * @return
     */
    public static FieldTypeFmt getFieldFmtPlugin(Class<?> clazz){
        if (fieldTypeFmtPluginMap.containsKey(clazz)){
            return fieldTypeFmtPluginMap.get(clazz);
        }
        return null;
    }

}