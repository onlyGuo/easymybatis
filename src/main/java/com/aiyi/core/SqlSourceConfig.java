package com.aiyi.core;

import com.aiyi.core.enums.SqlType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: 郭胜凯
 * @Date: 2019-07-23 16:20
 * @Email 719348277@qq.com
 * @Description: 数据源配置
 */
@Component
public class SqlSourceConfig {

    private static SqlType type;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @PostConstruct
    public void init(){
        if (driverClass.toUpperCase().contains(SqlType.MYSQL.name())){
            type = SqlType.MYSQL;
        }
        if (driverClass.toUpperCase().contains(SqlType.ORACLE.name())){
            type = SqlType.ORACLE;
        }
    }

    public static SqlType getType(){
        return type;
    }
}