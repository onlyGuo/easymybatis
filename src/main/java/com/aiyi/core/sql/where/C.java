package com.aiyi.core.sql.where;

/**
 * 比较运算符枚举
 */
public enum C {

	/**
	 * 比较运算符
	 */
	EQ("=", "等于"),
	NE("<>", "不等于"),
	LIKE("like", "相似"),
	DA(">", "大于"),
	XIAO("<", "小于"),
	IN( "IN", "在XXX内"),
	NOTIN("NOT IN", "不在XXX内"),
	DE(">=", "大于或等于"),
	XE("<=", "小于或等于");

	private String value;

	private String dscp;

	C(String value, String dscp){
		this.value = value;
		this.dscp = dscp;
	}

	public String value(){
		return value;
	}

}
