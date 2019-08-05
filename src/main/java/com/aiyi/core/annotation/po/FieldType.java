package com.aiyi.core.annotation.po;

/**
 * 特殊字段值
 */
public enum FieldType {
    DEFAULT("DEFAULT", "", "", "默认通用类型(varchar, text等)"),
    DATE("DATE", "STR_TO_DATE(\'{dtStr}\', \'%Y-%m-%d %H:%i:%s\')", "TO_DATE(\'{dtStr}\', \'yyyy-mm-dd hh24:mi:ss\')", "时间");
    private String value;

    private String mySqlTra;

    private String oracleTra;

    private String dscp;

    FieldType(String value, String mySqlTra, String oracleTra, String dscp){
        this.value = value;
        this.dscp = dscp;
        this.mySqlTra = mySqlTra;
        this.oracleTra = oracleTra;
    }

    public String getMySqlTra() {
        return mySqlTra;
    }

    public void setMySqlTra(String mySqlTra) {
        this.mySqlTra = mySqlTra;
    }

    public String getOracleTra() {
        return oracleTra;
    }

    public void setOracleTra(String oracleTra) {
        this.oracleTra = oracleTra;
    }

    public String getDscp() {
        return dscp;
    }

    public void setDscp(String dscp) {
        this.dscp = dscp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(Enum o){
        if (!this.getClass().equals(o.getClass())){
            return false;
        }
        return this.getValue().equalsIgnoreCase(((FieldType)o).getValue());
    }
}
