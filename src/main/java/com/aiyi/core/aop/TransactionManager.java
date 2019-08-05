package com.aiyi.core.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 事物相关(丢弃)
 * @Author 郭胜凯
 * @time 2016��5��11������10:02:15
 * @email 719348277@qq.com
 */
@Aspect
@Deprecated
public class TransactionManager {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "sqlSessionTemplateASS")
	private SqlSessionTemplate sqlSessionTemplateASS;

	@Pointcut("@annotation(com.aiyi.core.annotation.po.TraMethod)")
	public void transactionMethod(){}

	@Before("transactionMethod()")
	public void openTra(){
		logger.debug("事物开启");
	}

	@AfterReturning("transactionMethod()")
	public void cmmintTra(){
		logger.debug("事物提交");
	}
}
