package com.aiyi.core.beans;

import com.aiyi.core.enums.OrderBy;
import com.aiyi.core.util.lambda.LambdaUtil;
import com.aiyi.core.util.lambda.SFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020/11/10 10:39
 * @Email 719348277@qq.com
 * @Description: 排序封装
 */
public class Sort {

    private List<String> sortList = new ArrayList<>();


    /**
     * 构建排序规则对象
     * @param fieldName
     *      字段名
     * @param orderBy
     *      排序类型
     * @param <T>
     * @param <J>
     * @return
     */
    public static <T, J> Sort of(SFunction<T, ?> fieldName, OrderBy orderBy){
        Sort sort = new Sort();
        sort.sortList.add(LambdaUtil.getTableName(fieldName) + " " + orderBy.name());
        return sort;
    }

    /**
     * 追加排序规则
     * @param fieldName
     *      字段名称
     * @param orderBy
     *      排序类型
     * @param <T>
     * @param <J>
     * @return
     */
    public <T, J> Sort and(SFunction<T, ?> fieldName, OrderBy orderBy){
        sortList.add(LambdaUtil.getTableName(fieldName) + " " + orderBy.name());
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sortList.size(); i ++){
            sb.append(' ').append(sortList.get(i));
            if (i < sortList.size() - 1){
                sb.append(',');
            }
        }
        return sb.toString();
    }
}