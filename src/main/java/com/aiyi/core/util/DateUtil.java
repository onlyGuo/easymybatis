package com.aiyi.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 一天的时间(毫秒值)
     */
    public static long DAY_TIME = 1000L * 60 * 60 * 24;

    /**
     * 昨天
     * @return
     */
    public static Date yesterday(){
        return parse(yesterdayTime().getTime(), "yyyy-MM-dd");
    }

    /**
     * 某个时间段的上一天
     * @param date
     * @return
     */
    public static Date yesterday(Date date){
        return parse(yesterdayTime(date).getTime(), "yyyy-MM-dd");
    }

    /**
     * 某个时间段的上一天的这个时间
     * @param date
     *      时间段
     * @return
     */
    public static Date yesterdayTime(Date date){
        return new Date(date.getTime() - DAY_TIME);
    }

    /**
     * 昨天的这个时候
     * @return
     */
    public static Date yesterdayTime(){
        return new Date(System.currentTimeMillis() - DAY_TIME);
    }

    /**
     * 获取指定时间相对于格林威治时间中经历的所有天数
     * @param date
     * @return
     */
    public static int getAllDay(Date date){
        return (int) (date.getTime() / DAY_TIME);
    }

    /**
     * 获取当前时间是几号
     * @param date
     * @return
     */
    public static int getDay(Date date){
        String format = format(date);
        String[] split = format.split("-");
        return Integer.valueOf(split[2]);
    }

    /**
     * 获取当前时间是几月份
     * @param date
     * @return
     */
    public static int getMonth(Date date){
        String format = format(date);
        String[] split = format.split("-");
        return Integer.valueOf(split[1]);
    }

    /**
     * 获取当前时间年份
     * @param date
     * @return
     */
    public static int getYear(Date date){
        String format = format(date);
        String[] split = format.split("-");
        return Integer.valueOf(split[0]);
    }

    /**
     * 普通格式化：yyyy-MM-dd
     * @return
     */
    public static String format(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    /**
     * 普通格式化：yyyy-MM-dd
     * @return
     */
    public static String format(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(time);
    }

    /**
     * 普通格式化：yyyy-MM-dd
     * @return
     */
    public static String format(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 带时分秒：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatAll(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    /**
     * 带时分秒：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatAll(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    /**
     * 带时分秒：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatAll(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 中文：yyyy年MM月dd日
     * @return
     */
    public static String formatCN(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    /**
     * 中文：yyyy年MM月dd日
     * @return
     */
    public static String formatCN(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(time);
    }

    /**
     * 中文：yyyy年MM月dd日
     * @return
     */
    public static String formatCN(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(date);
    }

    /**
     * 中文带时分秒：yyyy年MM月dd日 HH时mm分ss秒
     * @return
     */
    public static String formatCNAll(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    /**
     * 中文带时分秒：yyyy年MM月dd日 HH时mm分ss秒
     * @return
     */
    public static String formatCNAll(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return simpleDateFormat.format(time);
    }

    /**
     * 中文带时分秒：yyyy年MM月dd日 HH时mm分ss秒
     * @return
     */
    public static String formatCNAll(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return simpleDateFormat.format(date);
    }

    /**
     * 自定义
     * @param pra
     * @return
     */
    public static String formatPramm(String pra){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pra);
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    /**
     * 自定义
     * @param pra
     * @return
     */
    public static String formatPramm(long time, String pra){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pra);
        return simpleDateFormat.format(new Date(time));
    }

    /**
     * 自定义
     * @param pra
     * @return
     */
    public static String formatPramm(Date time, String pra){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pra);
        return simpleDateFormat.format(time);
    }

    /**
     * 时间戳转格式化的标准时间
     * @param time
     *      时间戳
     * @param pra
     *      格式化表达式
     * @return
     */
    public static Date parse(long time, String pra){
        String s = formatPramm(time, pra);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pra);
        try {
            Date parse = simpleDateFormat.parse(s);
            return parse;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 日期时间字符串转日期对象
     * @param dateStr
     *      日期时间字符串
     * @param pra
     *      格式化表达式
     * @return
     */
    public static Date parse(String dateStr, String pra){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pra);
        try {
            Date parse = simpleDateFormat.parse(dateStr);
            return parse;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
