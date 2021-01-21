package com.aiyi.core.annotation.po.vali;

import com.aiyi.core.annotation.po.vali.enums.ValidationType;

import java.lang.annotation.*;

/**
 * @Project : Xunhengda
 * @Prackage Name : com.aiyi.core.annotation.po.vali
 * @Description : 字段校验注解
 * @Author : 郭胜凯
 * @Creation Date : 2018/5/11 下午6:06
 * @ModificationHistory Who When What ---------- ------------- -----------------------------------
 * 郭胜凯 2018/5/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validation {
  // 较严格式
  ValidationType value() default ValidationType.Not;
  // 自定义正则
  String regex() default "";
  // 最大长度, 0 = 不校验
  int maxLength() default 0;
  // 最小长度, 0 = 不校验
  int minLength() default 0;
  // 最大值, 仅对数值型有效
  double maxValue() default Long.MAX_VALUE;
  // 最小值, 仅对数值型有效
  double minValue() default Long.MIN_VALUE;
  // 字段名称
  String name() default "";
}
