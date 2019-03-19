package com.zhh.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 时间工具类
 * @Author zhouhui
 * @Version V1.0
 * @Date 2018/12/28 14:14
 */
public class DateUtils {
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    //将Date类型转为字符类型返回
    public static String transferDateToStr(Date date, String pattern){
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        return sd.format(date);
    }

    //将字符类型转为Date类型返回
    public static Date transferStrToDate(String date, String pattern){
        try {
            SimpleDateFormat sd = new SimpleDateFormat(pattern);
            return sd.parse(date);
        }catch (Exception e){
            return null;
        }
    }

    //获取当前时间以前或以后N分钟时的时间
    public static Date getTimeBeforeOrAfterLimit(Integer limit){
        Calendar nowTime = Calendar.getInstance();
        //limit为负数时，获取前limit分钟时的时间，反之同理
        nowTime.add(Calendar.MINUTE, limit);
        return nowTime.getTime();
    }

    //判断时间先后顺序
    public static Boolean compareDate(Date firstDate, Date secondDate){
        //firstDate大于secondDate，返回true，当小于等于时，返回false；
        return firstDate.after(secondDate);
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    //计算距离当前时间多久之前
    public static String calculateTimesAgo(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    // 通过时间秒毫秒数判断两个时间的间隔天数
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
}
