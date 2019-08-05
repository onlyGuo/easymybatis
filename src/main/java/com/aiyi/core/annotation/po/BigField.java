package com.aiyi.core.annotation.po;

import com.aiyi.core.enums.BigFieldType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BigField {
    BigFieldType value() default BigFieldType.CLOB;
}
