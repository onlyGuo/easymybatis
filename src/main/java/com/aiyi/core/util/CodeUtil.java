package com.aiyi.core.util;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/28 11:40
 * @Email 719348277@qq.com
 * @Description: 号码相关工具类
 */
public class CodeUtil {

    /**
     * 取一定范围内的随机数
     * @param min
     *      最小数
     * @param max
     *      最大数
     * @return
     */
    public static int rand(int min, int max){
        return (int) (Math.random()*(max-min)+min);
    }

}