package com.aiyi.core.exception;

/**
 * @Author: 郭胜凯
 * @Date: 2020/7/21 10:08
 * @Email 719348277@qq.com
 * @Description: 信息校验异常
 */
public class ValidationException extends FlowException {
    public ValidationException(){
        super("请求参数有误!");
    }

    public ValidationException(String msg){
        super(msg);
    }

    public ValidationException(String msg, Throwable e){
        super(msg, e);
    }

    public ValidationException(Throwable e, String msg){
        super(msg, e);
    }
}