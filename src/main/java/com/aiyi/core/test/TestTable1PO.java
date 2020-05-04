package com.aiyi.core.test;

import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.beans.PO;

/**
 * @Author: 郭胜凯
 * @Date: 2020-05-04 09:05
 * @Email 719348277@qq.com
 * @Description: 测试表1
 */
@TableName(name = "TEST_TABLE1")
public class TestTable1PO extends PO {
    private int id;

    private String name;

    private int age;

    private Boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}