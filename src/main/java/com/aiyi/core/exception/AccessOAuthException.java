package com.aiyi.core.exception;

/**
 * @Project : Xunhengda
 * @Prackage Name : com.aiyi.core.exception
 * @Description : 授权认证异常类: Http = 401
 * @Author : 郭胜凯
 * @Creation Date : 2018/5/6 下午2:01
 * @ModificationHistory Who When What ---------- ------------- -----------------------------------
 * 郭胜凯 2018/5/6
 */
public class AccessOAuthException extends RuntimeException {
  public AccessOAuthException(){
    super("无访问权限!");
  }
  public AccessOAuthException(String msg){
    super(msg);
  }

  public AccessOAuthException(String msg, Throwable e){
    super(msg, e);
  }
  public AccessOAuthException(Throwable e, String msg){
    super(msg, e);
  }
}
