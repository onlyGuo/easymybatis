package com.aiyi.core.annotation.po;

import java.lang.annotation.*;

/**
 * 定义PO类的键类型
 * @author 郭胜凯
 * @time 2016年2月26日下午3:41:06
 * @email 719348277@qq.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ID {

	/**
	 * 主键数据类型，默认整数型
	 * @return
	 */
	int type() default PUBVALUE.TABLE_ID_TYPE_INTEGER;
	
}
