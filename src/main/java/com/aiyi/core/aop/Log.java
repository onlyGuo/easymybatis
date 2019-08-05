package com.aiyi.core.aop;

import com.aiyi.core.util.thread.ThreadUtil;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 自动日志实打印类
 * @Author 郭胜凯
 * @time 2016年5月11日 上午10:02:15
 * @email 719348277@qq.com
 */
@Aspect
@Component
public class Log {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	Map<String, Logger> loggerMap = new HashMap<>();

	@PostConstruct
	public void init(){
		logger.info("AOP Auto-Logger initialized successfully!");
	}

	/**
	 * 获得切入点中对应的日志打印类
	 * @param cls
	 * @return
	 */
	public Logger getLogger(Class cls){
		Logger logger = loggerMap.get(cls.getName());
		if (null != logger){
			return logger;
		}
		logger = LoggerFactory.getLogger(cls);
		if (null != logger){
			loggerMap.put(cls.getName(), logger);
			return logger;
		}
		return null;
	}

	/**
	 * AOP日志 切点
	 */
	@Pointcut("@annotation(com.aiyi.core.annotation.po.Log)")
	public void logMethod(){}

	/**
	 * 方法执行之前日志输出
	 * @param joinPoint
	 * 		切入点
	 */
	@Before("logMethod()")
	public void openTra(JoinPoint joinPoint){
		MethodInfo info = getMethodInfo(joinPoint);
		getLogger(info.targetClass).info("requestId:[{}], msg:[{}], args:{}",
				ThreadUtil.getRequestId(), info.methodName + " 进栈", JSON.toJSONString(info.arguments));
	}

	/**
	 * 方法执行之后日志输出
	 * @param joinPoint
	 * 		切入点
	 */
	@AfterReturning("logMethod()")
	public void cmmintTra(JoinPoint joinPoint) {
		MethodInfo info = getMethodInfo(joinPoint);
		getLogger(info.targetClass).info("requestId:[{}], msg:[{}], args:{}",
				ThreadUtil.getRequestId(), info.methodName + " 出栈", JSON.toJSONString(info.arguments));
	}

	/**
	 * 获得日志方法详情
	 * @param joinPoint
	 * 		切入点
	 * @return
	 */
	private MethodInfo getMethodInfo(JoinPoint joinPoint){
		MethodInfo info = new MethodInfo();
		String targetName = joinPoint.getTarget().getClass().getName();

		String name = joinPoint.getSignature().getName();

		info.arguments = joinPoint.getArgs();
		try {
			info.targetClass = Class.forName(targetName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Method[] methods = info.targetClass.getMethods();

		String methodName = null;

		for (Method method : methods) {
			if (method.getName().equals(name)) {
				@SuppressWarnings("rawtypes")
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == info.arguments.length) {

					methodName = method.getAnnotation(com.aiyi.core.annotation.po.Log.class).action();

					if (methodName.equals("null")) {
						methodName = targetName + "#" + name;
					}

					break;
				}
			}
		}
		info.methodName = methodName;
		return info;
	}

	class MethodInfo{
		Class<?> targetClass;
		String methodName;
		Object[] arguments;
	}


}
