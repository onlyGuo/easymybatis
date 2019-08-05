package com.aiyi.core.exception;

/**
 * @Project : Xunhengda
 * @Prackage Name : com.aiyi.core.exception
 * @Description : 用户请求(参数)异常类: Http = 400
 * @Author : 郭胜凯
 * @Creation Date : 2018/5/6 下午1:58
 * @ModificationHistory Who When What ---------- ------------- -----------------------------------
 * 郭胜凯 2018/5/6
 */
public class RequestParamException extends RuntimeException {
  public RequestParamException(){
    super("请求参数有误!");
  }

  public RequestParamException(String msg){
    super(msg);
  }

  public RequestParamException(String msg, Throwable e){
    super(msg, e);
  }

  public RequestParamException(Throwable e, String msg){
    super(msg, e);
  }
}
