package com.aiyi.core.util;

import org.springframework.jdbc.support.JdbcUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件操作工具类
 * @author 郭胜凯
 * @time 2016年2月27日上午9:35:11
 * @email 719348277@qq.com
 */
public class PropertiesUtil {

	
	/**
	 * 获取Properties配置文件
	 * @param path
	 * @return
	 */
	public static Properties getProp(String path){
		 Properties properties = new Properties();
		 InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream(path);
		 try {  
			 properties.load(inputStream);
		 } catch (IOException e) {  
			 e.printStackTrace();  
		 }
		 return properties;
	}
	
	/**
	 * 获取Properties配置文件
	 * @param path
	 * @return
	 */
	public Properties getProperites(String path){
		 Properties properties = new Properties();
		 InputStream stream = this.getClass().getClassLoader().getResourceAsStream("/" + path);
		 try {  
			 properties.load(stream);
		 } catch (IOException e) {  
			 e.printStackTrace();  
		 }
		 return properties;
	}
	
	
	/**
	 * 打开某个文件的流
	 * @param path
	 * @return
	 */
	public InputStream getStrean(String path){
		return this.getClass().getClassLoader().getResourceAsStream("/" + path);
	}
}
