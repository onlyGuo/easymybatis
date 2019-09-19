package com.aiyi.core.beans;


import com.aiyi.core.annotation.po.vali.Validation;
import com.aiyi.core.annotation.po.vali.enums.ValidationType;
import com.aiyi.core.sql.where.SqlUtil;
import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description : 统一约束参数实体类
 * @Creation Date : 2018/5/11 下午6:03
 * @Author : 郭胜凯
 */
public class PO {

  /**
   * 校验自身某些字段是否合法
   * @param fieldNames
   */
  public void check(String... fieldNames) {
    Field[] declaredFields = this.getClass().getDeclaredFields();
    List<String> fieldList = Arrays.asList(fieldNames);
    for (Field field : declaredFields) {
      if (!fieldList.contains(field.getName())) {
        continue;
      }
      Validation validation = field.getAnnotation(Validation.class);
      if (validation == null) {
        continue;
      }
      field.setAccessible(true);
      Object value = null;
      try {
        value = field.get(this);
      } catch (IllegalAccessException e) {
        throw new RuntimeException("字段校验失败", e);
      }
      String fieldName = validation.name();
      if ("".equals(fieldName)) {
        fieldName = field.getName();
      }
      if (null == value){
        throw new IllegalArgumentException(fieldName + "不能为空");
      }
      if (value instanceof String) {
        String regex = validation.value().getRegex();
        if (null != regex) {
          if (!Pattern.matches(validation.value().getRegex(), (String) value)) {
            throw new IllegalArgumentException(fieldName + "格式不符合规范");
          }
        }
        if ("".equals(value)) {
          throw new IllegalArgumentException(fieldName + "不能为空");
        }
        if (validation.minLength() != 0 && validation.minLength() > ((String) value).length()) {
          throw new IllegalArgumentException(fieldName + "内容长度不能小于" + validation.minLength() + "位字符");
        }
        if (validation.maxLength() != 0 && validation.maxLength() < ((String) value).length()) {
          throw new IllegalArgumentException(fieldName + "内容长度不能大于" + validation.maxLength() + "位字符");
        }
      } else if (value instanceof Number) {
        if (Double.valueOf(value.toString()).doubleValue() < validation.minValue()) {
          throw new IllegalArgumentException(fieldName + "最小不能小于" + validation.minValue());
        }
        if (Double.valueOf(value.toString()).doubleValue() > validation.maxValue()) {
          throw new IllegalArgumentException(fieldName + "最大不能大于" + validation.maxValue());
        }
      } else {
        // TODO 留作扩展...
      }
    }
  }

  /**
   * @param value   要校验的值
   * @param name    要校验的值中文名称
   * @param type    要校验的值类型
   * @Description : 检验某个字段值是否符合指定类型
   * @Creation Date : 2018/5/21 下午4:41
   * @Author : 郭胜凯
   */
  public static void checkField(String value, String name, ValidationType type){
    if (null == value || "".equals(value)){
      throw new IllegalArgumentException(name + "不能为空");
    }
    if(!Pattern.matches(type.getRegex(), value)){
      throw new IllegalArgumentException(name + "格式不符合规范");
    }
  }

  /**
   * 从源数据中拷贝字段值
   * @param source
   *      源数据实体
   * @param ignoredNull
   *      是否忽略空值
   * @param <T>
   */
  public <T extends PO> void copyFieldsValue(T source, boolean ignoredNull){
    if (null == source){
      throw new IllegalArgumentException("Copy Po fail: source entity is null!");
    }

    Class<? extends PO> sourceClass = source.getClass();
    Class<? extends PO> thisClass = this.getClass();

    if (!thisClass.isInstance(sourceClass)) {
      throw new IllegalArgumentException("Target class ["
              + sourceClass.getClass().getName() + "] not assignable to Editable class ["
              + thisClass.getName() + "]");
    }


    List<Pram> pramListofStatic = SqlUtil.getPramListofStatic(source);
    for(Pram pram: pramListofStatic){
      if (ignoredNull){
        if (null == pram.getValue()){
          continue;
        }
      }
      SqlUtil.setFileValueByOracle(this, pram.getField().getName(),
              (Serializable) pram.getValue());
    }
  }

  /**
   * 获得实体类对应的表名
   * @return
   */
  public String tableName(){
    String tableName = new SqlUtil<>().getTableNameByClazz(this.getClass());
    return tableName;
  }


  @Override
  public String toString() {
    return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss");
  }
}
