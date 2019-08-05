package com.aiyi.core.exception;

/**
 * @Project : Xunhengda
 * @Prackage Name : com.aiyi.core.exception
 * @Description : 服务器处理异常: Http = 500
 * @Author : 郭胜凯
 * @Creation Date : 2018/5/6 下午2:05
 * @ModificationHistory Who When What ---------- ------------- -----------------------------------
 * 郭胜凯 2018/5/6
 */
public class ServiceInvokeException extends RuntimeException {
  public ServiceInvokeException(){
    super("内部服务异常");
  }
  public ServiceInvokeException(String msg){
    super(msg);
  }
  public ServiceInvokeException(String msg, Throwable e){
    super(msg, e);
  }
  public ServiceInvokeException(Throwable e, String msg){
    super(msg, e);
  }
}
