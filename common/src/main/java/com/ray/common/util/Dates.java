package com.ray.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Dates {
    public static final SimpleDateFormat YMD_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat YMD = new SimpleDateFormat("yyyy年M月d日");

    public static String yMdHms() {
        return YMD_HMS.format(new Date());
    }

    public static String yMdHms(Date date) {
        return YMD_HMS.format(date);
    }

    public static String yMd(Date date) {
        return YMD.format(date);
    }

    public static String formate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date parse(String date) {
        try {
            return YMD_HMS.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }


    public static boolean isToday(long date) {
        long now = System.currentTimeMillis();
        long diff = (now - date) / 1000;
        if (diff >= 86400 || diff <= 0) return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(now);
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(date);
        return c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    public static int diffDays(long begin, long end) {
        return (int) ((end - begin) / 86400000);
    }

    public static int diffDaysNow(long begin) {
        return (int) ((System.currentTimeMillis() - begin) / 86400000);
    }
}
