package com.aiyi.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * @Author: 郭胜凯
 * @Date: 2019-05-29 19:43
 * @Email 719348277@qq.com
 * @Description: Spring Boot 程序全局容器管理工具类
 */
@Component
public class SpringBootApplicationUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    protected Logger logger = LoggerFactory.getLogger(SpringBootApplicationUtil.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringBootApplicationUtil.applicationContext == null) {
            SpringBootApplicationUtil.applicationContext = applicationContext;
        }
        logger.info("---------------------------------------------------------------------");
        logger.info("------------SpringBootApplicationUtil Init Successfully--------------");
        logger.info("---------------------------------------------------------------------");
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name
     *      Bean Name
     * @return
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     * @param clazz
     *      预获取对象的Class
     * @param <T>
     *      对象泛型
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param name
     *      对象名称
     * @param clazz
     *      对象类型
     * @param <T>
     *      对象泛型
     * @return
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
