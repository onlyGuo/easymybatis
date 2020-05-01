package com.aiyi.core.dao;

import com.aiyi.core.beans.PO;
import com.aiyi.core.beans.ResultPage;
import com.aiyi.core.beans.WherePrams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 公共DAO
 * @author 郭胜凯
 * @time 2016年5月3日 下午2:55:13
 * @email 719348277@qq.com
 * @param <T> 实体类
 * @param <PK> PO主键类型
 */
public interface BaseDao<T extends PO, PK extends Serializable> {


	/**
	 * 添加一条记录(添加一个PO上的全部可用字段)
	 * @param po 对应数据表的实体类
	 * @return 受到影响的记录数
	 */
	int add(T po);
	
	/**
	 * 通过ID获取一条记录
	 * @param id 实体类的唯一ID
	 * @return PO 对应数据表的实体类
	 */
	T get(PK id);
	
	/**
	 * 通过自定义条件获取一条记录
	 * @param where 条件表达式
	 * @return PO 对应数据表的实体类
	 */
	T get(WherePrams where);
	
	/**
	 * 通过自定义条件获取一个记录中指定的字段值
	 * @param where 条件表达式
	 * @param fileName 要获取其值得字段名称
	 * @return  字段名称对应的值
	 */
	Serializable getField(WherePrams where, String fileName);

	/**
	 * 通过自定义条件获取记录列表
	 * @param where 条件表达式
	 * @return List<T> 获取到的记录列表
	 */
	List<T> list(WherePrams where);

	/**
	 * 分页获取符合条件的记录列表
	 * @param where
	 * 		条件表达式
	 * @param page
	 * 		页码
	 * @param size
	 * 		每页条数
	 * @return
	 */
	ResultPage<T> list(WherePrams where, int page, int size);

	/**
	 * 通过自定义Sql来查询记录列表
	 * @param sql
	 * 		SQL脚本
	 * @param params
	 * 		参数
	 * @return
	 */
	List<T> list(String sql, Object... params);

	/**
	 * 通过自定义SQL和条件表达式查询记录列表
	 * @param sql
	 * 		SQL脚本
	 * @param wherePrams
	 * 		条件表达式
	 * @return
	 */
	List<T> list(String sql, WherePrams wherePrams);

	/**
	 * 更新一条记录中的全部字段值为指定实体类中字段的直
	 * @param po 对应数据表的实体类
	 * @return 受到影响的记录数
	 */
	int update(T po);
	
	/**
	 * 通过ID删除一条记录
	 * @param id 实体类中的唯一ID
	 * @return 受更改的记录数
	 */
	int del(PK id);
	
	/**
	 * 删除符合指定条件的记录
	 * @param where 条件表达式
	 * @return 受到影响的记录数
	 */
	int del(WherePrams where);
	
	/**
	 * 通过自定义SQL语句查询
	 * @param sql
	 * 		自定义的sql语句
	 * @param p
	 * 		SQL执行参数
	 * @return 查询到的结果列表
	 */
	List<Map<String, Object>> listBySql(String sql, Object... p);
	
	/**
	 * 执行自定义SQL语句
	 * @param sql 自定义的sql语句
	 * @return 受到影响的记录数
	 */
	int execute(String sql, Object... params);
	
	/**
	 * 获取当前表中符合指定条件的记录数量
	 * @param where 条件表达式
	 * @return 查询到的记录数量
	 */
	long count(WherePrams where);
	
	/**
	 * 获取当前表中所有记录数量
	 * @return 查询到的记录数量
	 */
	long size();
	
	/**
	 * 查看符合指定实体类中字段的记录是否存在
	 * @param po 用来做参考的实体类
	 * @return 是否存在
	 */
	boolean isExist(T po);
	
	/**
	 * 查询是否存在符合指定条件的记录
	 * @param where 条件表达式
	 * @return 是否存在
	 */
	boolean isExist(WherePrams where);
	
	/**
	 * 利用 'in' 查询包含给定字段值的记录
	 * @param fileName 字段名称
	 * @param values 给定值得列表
	 * @return 符合条件的实体类列表
	 */
	List<T> in(String fileName, Serializable[] values);
	
}
