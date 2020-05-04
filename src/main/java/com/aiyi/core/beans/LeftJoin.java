package com.aiyi.core.beans;

import com.aiyi.core.util.lambda.SFunction;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 郭胜凯
 * @Date: 2020-05-04 10:36
 * @Email 719348277@qq.com
 * @Description: 左外关联
 */
public class LeftJoin {

    private List<JoinItem> items = new LinkedList<>();


    private LeftJoin(){}

    public static <T> LeftJoin join(Class<? extends PO> clazz, WherePrams on, SFunction<T, ?>... joinFields){
        LeftJoin leftJoin = new LeftJoin();
        JoinItem joinItem = new JoinItem();
        joinItem.setClazz(clazz);
        joinItem.setOn(on);
        joinItem.setJoinFields(joinFields);
        leftJoin.items.add(joinItem);
        return leftJoin;
    }

    public <T> LeftJoin append(Class<? extends PO> clazz, WherePrams on, SFunction<T, ?>... joinFields){
        JoinItem joinItem = new JoinItem();
        joinItem.setClazz(clazz);
        joinItem.setOn(on);
        joinItem.setJoinFields(joinFields);
        this.items.add(joinItem);
        return this;
    }

    public List<JoinItem> getItems(){
        return items;
    }

    public static class JoinItem{
        private Class<? extends PO> clazz;

        private WherePrams on;

        private SFunction<?, ?>[] joinFields;

        public Class<? extends PO> getClazz() {
            return clazz;
        }

        public void setClazz(Class<? extends PO> clazz) {
            this.clazz = clazz;
        }

        public WherePrams getOn() {
            return on;
        }

        public void setOn(WherePrams on) {
            this.on = on;
        }

        public SFunction<?, ?>[] getJoinFields() {
            return joinFields;
        }

        public void setJoinFields(SFunction<?, ?>[] joinFields) {
            this.joinFields = joinFields;
        }
    }

}