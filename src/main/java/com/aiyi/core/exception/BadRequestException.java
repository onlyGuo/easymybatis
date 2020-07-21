package com.aiyi.core.exception;

/**
 * @Author: 郭胜凯
 * @Date: 2020/7/21 09:53
 * @Email 719348277@qq.com
 * @Description: 请求异常, HTTP400类, 不记录栈追踪信息, 不损耗性能, 用于通常业务流程控制
 */
public class BadRequestException extends FlowException {

    /**
     * 指定一个是否追踪栈信息的400异常
     * @param msg
     *      异常消息
     * @param recordStackTrace
     *      是否追踪栈信息
     */
    public BadRequestException(String msg, boolean recordStackTrace) {
        super(msg, recordStackTrace);
    }

    /**
     * 指定一个不追踪栈信息的400异常
     * @param msg
     *      异常消息
     */
    public BadRequestException (String msg){
        super(msg, false);
    }

    /**
     * 指定一个带有自定义消息的400包装异常
     * @param msg
     *      异常消息
     * @param cause
     *      要包装的异常信息
     */
    public BadRequestException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * 指定一个400包装异常
     * @param cause
     *      要包装的信息
     */
    public BadRequestException(Throwable cause) {
        super(cause);
    }
}