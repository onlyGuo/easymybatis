package com.aiyi.core.beans;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultBean {

	public static ResultBean success(String msg){
		return new ResultBean().setSuccess(true).setMessage(msg).setCode(0);
	}
	public static ResultBean success(){
		return success("处理成功");
	}

	public static ResultBean error(String msg){
		return success(msg).setSuccess(false);
	}

	public static ResultBean error(){
		return error("处理失败");
	}

	private int code;
	
	private String message;
	
	private Object responseBody;
	
	private boolean success;

	public int getCode() {
		return code;
	}

	public ResultBean setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResultBean setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public ResultBean setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
		return this;
	}

	/**
	 * 直接在ResultBody中PUT指定的键值对, 仅限ResponseBody是Map时.
	 * @param key
	 * 			键
	 * @param value
	 * 			值
	 * @return
	 * 			新的ResultBean
	 */
	public ResultBean putResponseBody(String key, Object value){
		if (null == getResponseBody()){
			setResponseBody(new LinkedHashMap<>());
		}
		if (!(getResponseBody() instanceof Map)){
			throw new RuntimeException("ResponseBody is not instanceof map");
		}
		((Map) getResponseBody()).put(key, value);
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public ResultBean setSuccess(boolean success) {
		this.success = success;
		return this;
	}
	
	public String toString(){
		String str = "ResultBean:";
		str += "code = " + code + ",";
		str += "message = " + message == null ? "null" : message + ",";
		str += "success = " + success;
		return str;
	}
}
