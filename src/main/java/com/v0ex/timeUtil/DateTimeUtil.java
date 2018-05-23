package com.v0ex.timeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zbj on 16/3/6.
 */
public class DateTimeUtil {

    /**
     * 求时间字符串差几个月
     * @param var1
     * @param var2
     * @return
     */
    public static int getOffsetMonth(String var1,String var2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(simpleDateFormat.parse(var1));
        c2.setTime(simpleDateFormat.parse(var2));
        int month = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        int year_month = (c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))*12;
        return Math.abs(month + year_month);
    }

    public static String convert(String str) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(str);
        String format = dateFormat.format(date);
        return format;
    }


    /**
     * 获取月份
     * @param timeStamp
     * @return
     */
    public static int getMonth(long timeStamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeStamp);
        //Time.MONTH及Calendar.MONTH 默认的月份为  0-11
        return c.get(Calendar.MONTH)+1;
    }

    public static void main(String[] args) throws ParseException {
        String convert = convert("2017-03-06 12:00:00");
        System.out.println(convert);
    }
}
