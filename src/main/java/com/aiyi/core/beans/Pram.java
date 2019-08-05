package com.aiyi.core.beans;


import java.lang.reflect.Field;

/**
 * POJO字段封装类
 * @author 郭胜凯
 * @time 2015-下午10:54:36
 * @email 719348277@qq.com
 */
public class Pram {

	private String dbField;

	private Field field;
	
	private Object value;

	public Pram(){}
	public Pram(String dbField, Field field, Object value){
		this.dbField = dbField;
		this.field = field;
		this.value = value;
	}

	public String getDbField() {
		return dbField;
	}

	public void setDbField(String dbField) {
		this.dbField = dbField;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
