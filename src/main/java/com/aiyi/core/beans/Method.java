package com.aiyi.core.beans;

import com.aiyi.core.sql.where.C;

import java.io.Serializable;

/**
 * SQL条件方法
 */
public class Method {


	/**
	 * WHERE 条件
	 * @param fieldName
	 * 		数据库字段名
	 * @param c
	 * 		比较符枚举
	 * @param value
	 * 		比较参考值
	 * @return
	 */
	public static WherePrams where(String fieldName, C c, Object value){
		return new WherePrams(fieldName , c , value);
	}

	/**
	 * 创建空条件的方法
	 * @return
	 */
	public static WherePrams createDefault(){
		return new WherePrams();
	}
	
}
