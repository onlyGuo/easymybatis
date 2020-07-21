package com.aiyi.core.exception;

/**
 * @Project : Xunhengda
 * @Prackage Name : com.aiyi.core.exception
 * @Description : 授权认证异常类: Http = 403
 * @Author : 郭胜凯
 * @Creation Date : 2018/5/6 下午2:01
 * @ModificationHistory Who When What ---------- ------------- -----------------------------------
 * 郭胜凯 2018/5/6
 */
public class AccessResourceOAuthException extends FlowException {
  public AccessResourceOAuthException(){
    super("无访问权限!");
  }
  public AccessResourceOAuthException(String msg){
    super(msg);
  }

  public AccessResourceOAuthException(String msg, Throwable e){
    super(msg, e);
  }
  public AccessResourceOAuthException(Throwable e, String msg){
    super(msg, e);
  }
}
