package com.aiyi.core.util.lambda;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @Author: 郭胜凯
 * @Date: 2020-05-04 08:44
 * @Email 719348277@qq.com
 * @Description: 可序列化的Function对象
 */
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
