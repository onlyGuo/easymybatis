package com.aiyi.core.beans;

/**
 * @Author: 郭胜凯
 * @Date: 2019-07-23 16:16
 * @Email 719348277@qq.com
 * @Description: SQL 脚本片段承载类
 */
public class Script {

    private StringBuffer buffer = new StringBuffer();

    /**
     * 合并SQL片段
     * @param sql
     *      SQL片段
     * @return
     */
    public Script append(String sql){
        this.buffer.append(sql);
        return this;
    }


    /**
     * 根据多个SQL片段生成一个大的脚本片段
     * @param sqls
     *      SQL片段
     * @return
     */
    public static Script of(String... sqls){
        Script script = new Script();
        for(String sql: sqls){
            script.append(sql);
        }
        return script;
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}