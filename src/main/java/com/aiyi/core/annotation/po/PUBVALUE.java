package com.aiyi.core.annotation.po;

/**
 * 定义公共值得约束类
 * @author 郭胜凯
 * @time 2016年2月26日下午3:37:37
 * @email 719348277@qq.com
 */
public class PUBVALUE {

	/**
	 * FieldName注解中默认的name值
	 */
	public static final String FIELD_NAME_DEFAUL_VALUE = "_nullName_this_is_default";

	/**
	 * FieldType 类型
	 */
	public static final String FIELD_TYPE_DEFAUL_VALUE = "_nullType_this_is_default";
	
	/**
	 * ID注解中type值得类型，该字段表示整数型自增Id
	 */
	public static final int TABLE_ID_TYPE_INTEGER = 0;
	
	/**
	 * ID注解中type值得类型，该字段表示String型UUID
	 */
	public static final int TABLE_ID_TYPE_UUID = 1;
	
}
