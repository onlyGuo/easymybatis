package com.aiyi.core.beans;

import com.aiyi.core.sql.where.C;
import com.aiyi.core.test.TestTable1PO;
import com.aiyi.core.util.lambda.LambdaUtil;
import com.aiyi.core.util.lambda.SFunction;

import java.io.Serializable;

/**
 * SQL条件方法
 */
public class Method {

	/**
	 * 可查询外表的 WHERE 条件
	 * @param fieldName
	 * 		表名对应实体类的字段方法
	 * @param c
	 * 		比较符枚举
	 * @param value
	 * 		参考表名对应实体类的字段方法
	 * @param <T>
	 * @param <J>
	 * @return
	 */
	public static <T, J> WherePrams where(SFunction<T, ?> fieldName, C c, SFunction<J, ?>  value) {
		String tableFieldName = LambdaUtil.getTableName(value);
		return new WherePrams(fieldName , c , tableFieldName);
	}

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
	 * WHERE 条件
	 * @param fieldName
	 * 		数据库实体类的字段操作表达式
	 * @param c
	 * 		比较符枚举
	 * @param value
	 * 		比较参考值
	 * @return
	 */
	public static <T> WherePrams where(SFunction<T, ?> fieldName, C c, Object value){
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
