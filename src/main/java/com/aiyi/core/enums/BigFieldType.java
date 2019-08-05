package com.aiyi.core.enums;

/**
 * 大字段类型枚举
 */
public enum BigFieldType {
    CLOB("CLOB", "TO_CLOB", "字符大字段"),
    NCLOB("CLOB", "TO_NCLOB", "字符大字段");
    private String name;
    private String sqlFun;
    private String dscp;


    BigFieldType(String name, String sqlFun, String dscp){
        this.dscp = dscp;
        this.name = name;
        this.sqlFun = sqlFun;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSqlFun() {
        return sqlFun;
    }

    public void setSqlFun(String sqlFun) {
        this.sqlFun = sqlFun;
    }

    public String getDscp() {
        return dscp;
    }

    public void setDscp(String dscp) {
        this.dscp = dscp;
    }
}
