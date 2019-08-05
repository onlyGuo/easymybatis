package com.aiyi.core.beans;

import com.aiyi.core.annotation.po.DateTime;
import com.aiyi.core.annotation.po.FieldType;
import com.aiyi.core.plugins.PluginManager;
import com.aiyi.core.plugins.field.FieldTypeFmt;
import com.aiyi.core.sql.where.C;
import com.aiyi.core.util.DateUtil;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.*;

/**
 * 条件参数封装类
 */
public class WherePrams {

	private StringBuffer pram;
	
	private String limit;
	
	private String orderBy;

	private Map<String, Object> whereMap = new HashMap<>();

	public WherePrams(){
		this.pram = new StringBuffer(" WHERE 1=1 ");
	}


	/**
	 * 获得需要的比较符
	 * @param c
	 * 		比较符枚举
	 * @param value
	 * 		预比较的值
	 * @return
	 */
	private String parseComparator(C c, Object value){
		String where = c.value();

		if (null == value){
			if (c == C.EQ) {
				where = "IS";
			}else if (c == C.NE){
				where = "IS NOT";
			}
		}
		return where;
	}

//	/**
//	 * 获得字段值的SQL可识别的对应字符串
//	 * @param value
//	 * 		字段值
//	 * @return
//	 */
//	private String parseReferValue(Object value){
//		if (null == value){
//			return null;
//		}
//		if (value instanceof Script){
//			return value.toString();
//		}
//		if (value instanceof String){
//			return String.format("'%s'", value);
//		}
//
//		// 通过插件
//		FieldTypeFmt typeFmt = PluginManager.getFieldFmtPlugin(value.getClass());
//		if (null != typeFmt){
//			return typeFmt.fmt(value);
//		}
//
//		return String.format("'%s'", value);
//	}

	/**
	 * 获取SQL条件表达式
	 * @param field
	 * 		字段名
	 * @param c
	 * 		字段比较符
	 * @param value
	 * 		字段比较值
	 * @return
	 */
	private StringBuffer parseWhereExpression(String field, C c, Object value){
		String where = parseComparator(c, value);
		String index = String.valueOf(whereMap.size());
		if (c == C.LIKE){
			whereMap.put(String.format("param_%s", index), "%" + value + "%");
		}else if (c == C.IN){
			List<Object> list = null;
			StringBuffer values = new StringBuffer("(");
			if (value.getClass().isArray()){
				list = JSON.parseArray(JSON.toJSONString(value));
			}else if (value instanceof List){
				list = (List) value;
			}
			if (null != list){
				for (int i = 0; i < list.size(); i++){
					whereMap.put(String.format("param_%s", index), list.get(i));
					values.append(String.format("#{param_%s}", index));
					if (i < list.size() - 1){
						values.append(", ");
						index = String.valueOf(whereMap.size());
					}else{
						values.append(")");
					}
				}
				return new StringBuffer(field).append(" ").append(where).append(values);
			}else{
				return new StringBuffer(field).append(" ").append(where).append(" (").append(value).append(")");
			}
		}else{
			whereMap.put(String.format("param_%s", index), value);
		}
		return new StringBuffer(field).append(" ").append(where).append(" ")
				.append(String.format("#{param_%s}", index));
	}


	public WherePrams(String field, C c, Object value){
		if(null == field && null == c && value == c.value()){
			return;
		}
		StringBuffer buffer = parseWhereExpression(field, c, value);
		this.pram = new StringBuffer("WHERE ").append(buffer);
	}
	
	/**
	 * and条件
	 * @param field
	 *		字段名
	 * @param c
	 * 		比较符
	 * @param value
	 * 		比较值
	 * @return
	 */
	public WherePrams and(String field, C c, Object value){
		StringBuffer buffer = parseWhereExpression(field, c, value);
		this.pram.append(" AND ").append(buffer);
		return this;
	}

	/**
	 * Or 条件
	 * @param field
	 * 		字段名
	 * @param c
	 * 		比较符
	 * @param value
	 * 		字段值
	 * @return
	 */
	public WherePrams or(String field, C c, Object value){
		StringBuffer buffer = parseWhereExpression(field, c, value);
		this.pram.append(" OR ").append(buffer);
		return this;
	}

	/**
	 * 分页条件
	 * @param startNum
	 * 		开始位置
	 * @param length
	 * 		分页长度
	 * @return
	 */
	public WherePrams limit(int startNum, int length) {
		this.limit = " LIMIT " + startNum + " , " + length + " ";
		return this;
	}

	public String getLimit() {
		return limit;
	}

	public WherePrams orderBy(String order){
		if(this.orderBy != null){
			this.orderBy += "," + order;
		}else{
			this.orderBy = order;
		}
		return this;
	}

	@Override
	public String toString() {
		return "WherePrams [pram=" + pram + "]";
	}
	
	/**
	 * 获取prams
	 * @return
	 */
	public String getWherePrams(){
		String p = "";
		p += null == this.pram ? "" : this.pram;
		p += null == this.orderBy ? "" : (" ORDER BY " + this.orderBy);
		p += null == this.limit ? "" : this.limit;
		return p;
	}

	/**
	 * 获取参数集合
	 * @return
	 */
	public Map<String, Object> getWhereMap(){
		return whereMap;
	}
}
