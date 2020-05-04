package com.aiyi.core.util.lambda;
import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.beans.PO;
import com.aiyi.core.beans.Pram;
import com.aiyi.core.sql.where.SqlUtil;
import com.aiyi.core.test.TestTable1PO;
import com.aiyi.core.test.TestTable2PO;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * @Author: 郭胜凯
 * @Date: 2020-05-04 08:46
 * @Email 719348277@qq.com
 * @Description: Lambda表达式工具类
 */
public class LambdaUtil {

    public static <T> String getTableName(SFunction<T, ?> fn) {
        SerializedLambda serializedLambda = getSerializedLambda(fn);
        String fieldName = getBeanName(fn);
        Field field;
        try {
            field = Class.forName(serializedLambda.getImplClass().replace("/", ".")).getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        // 获得表名
        Class<?> clazz;
        String implClass = serializedLambda.getImplClass().replace("/", ".");
        try {
            clazz = Class.forName(implClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        TableName annotation = clazz.getAnnotation(TableName.class);
        String tableName = null;
        if (null == annotation){
            tableName = SqlUtil.toTableString(clazz.getSimpleName());
            if (tableName.toUpperCase().endsWith("PO") || tableName.toUpperCase().endsWith("VO")){
                tableName = tableName.substring(0, tableName.length() - 2);
            }
        }else{
            tableName = annotation.name();
        }


        // 从field取出字段名，可以根据实际情况调整
        FieldName tableField = field.getAnnotation(FieldName.class);
        if (tableField != null && tableField.value().length() > 0) {
            return tableName + "." + tableField.name();
        } else {
            return tableName + "." + fieldName;
        }
    }


    /**
     * 获得实体类对应的字段名
     * @param fn
     * @param <T>
     * @return
     */
    public static <T> String getBeanName(SFunction<T, ?> fn) {
        SerializedLambda serializedLambda = getSerializedLambda(fn);

        // 从lambda信息取出method、field、class等
        String fieldName = serializedLambda.getImplMethodName();
        if (fieldName.startsWith("get") || fieldName.startsWith("set")){
            fieldName = fieldName.substring(3);
        }
        if (fieldName.startsWith("is")){
            fieldName = fieldName.substring(2);
        }
        fieldName = fieldName.replaceFirst(fieldName.charAt(0) + "", (fieldName.charAt(0) + "").toLowerCase());
        return fieldName;
    }

    private static <T> SerializedLambda getSerializedLambda(SFunction<T, ?> fn){
        // 从function取出序列化方法
        Method writeReplaceMethod;
        try {
            writeReplaceMethod = fn.getClass().getDeclaredMethod("writeReplace");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        // 从序列化方法取出序列化的lambda信息
        boolean isAccessible = writeReplaceMethod.isAccessible();
        writeReplaceMethod.setAccessible(true);
        SerializedLambda serializedLambda;
        try {
            serializedLambda = (SerializedLambda) writeReplaceMethod.invoke(fn);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        writeReplaceMethod.setAccessible(isAccessible);
        return serializedLambda;
    }
}