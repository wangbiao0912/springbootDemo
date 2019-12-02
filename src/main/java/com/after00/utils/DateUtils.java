package com.after00.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtils {
    public static Date addMinutes(Date orderTime, int orderTimeout) {
        Date afterDate = new Date(orderTime.getTime() + 60000 * orderTimeout);
        return afterDate;
    }


    public static String format = "yyyy-MM-dd";
    public static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    public static String dbDateFormat = "yyyy-mm-dd HH24:MI:SS";

    /**
     * Date 转 String
     * auther: shijing
     * 2015年3月25日上午11:28:14
     *
     * @param date   日期
     * @param format 转换格式
     */
    public static String dateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            if (date != null) {
                strDate = dateFormat.format(date);
            }
        } catch (Exception e) {
            log.error("Date类型 转 String类型出错：" + e);
        }
        return strDate;
    }


    public static Date addDate(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getMillis(date) + (long) i * 60L * 1000L);
        return calendar.getTime();
    }

    public static Date addDateMonth(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, i);
        return calendar.getTime();
    }

    public static Date addDateSecond(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getMillis(date) + (long) (i * 1000));
        return calendar.getTime();
    }

    public static long diffDate(Date date, Date date1) {
        if (date.after(date1)) {
            return (getMillis(date) - getMillis(date1)) / 60000L;
        } else {
            return (getMillis(date1) - getMillis(date)) / 60000L;
        }
    }

    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    public static String getDateTime() {
        Date date = new Date();
        return format(date, "yyyy-MM-dd HH:mm:ss:ms");
    }

    public static String getDateTime(String s) {
        Date date = new Date();
        return format(date, s);
    }

    public static String getDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateTime(Date date, String s) {
        return format(date, s);
    }

    public static String getDate() {
        Date date = new Date();
        return format(date, "yyyy-MM-dd");
    }

    public static String getDay() {
        String s = getDate();
        return s.substring(s.lastIndexOf("-") + 1);
    }

    public static String getDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String getDate(Date date, String s) {
        return format(date, s);
    }

    public static Date getDateStartTime(String date) {
        return formatStrToDate(date + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getDateEndTime(String date) {
        return formatStrToDate(date + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(Date date, String s) {
        String s1 = "";
        try {
            if (date != null) {
                SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
                s1 = simpledateformat.format(date);
            }
        } catch (Exception exception) {
        }
        return s1;
    }

    public static java.sql.Date getSqlDate(Date date) {
        java.sql.Date date1 = null;
        try {
            date1 = new java.sql.Date(date.getTime());
        } catch (Exception exception) {
        }
        return date1;
    }

    public static Date getUtilDate(java.sql.Date date) {
        Date date1 = null;
        try {
            date1 = new Date(getMillis(date));
        } catch (Exception exception) {
        }
        return date1;
    }

    public static String formatDateString(String s, String s1) {
        SimpleDateFormat simpledateformat = null;
        if (null == s1) {
            simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            simpledateformat = new SimpleDateFormat(s1);
        }
        try {
            return simpledateformat.format(simpledateformat.parse(s));
        } catch (ParseException parseexception) {
            parseexception.printStackTrace();
        }
        return null;
    }

    public static Date formatStrToDate(String s, String s1) {
        SimpleDateFormat simpledateformat = null;
        if (null == s1) {
            simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            simpledateformat = new SimpleDateFormat(s1);
        }
        try {
            return simpledateformat.parse(s);
        } catch (ParseException parseexception) {
            parseexception.printStackTrace();
        }
        return null;
    }

    public static java.sql.Date strToDate(String s, String s1) {
        if (s.length() <= 10) {
            s = (new StringBuilder()).append(s).append(" 00:00:00").toString();
        }
        SimpleDateFormat simpledateformat = new SimpleDateFormat(s1);
        ParsePosition parseposition = new ParsePosition(0);
        return new java.sql.Date(simpledateformat.parse(s, parseposition)
                .getTime());
    }

    public static Timestamp strToTimestamp(String s, String s1) {
        if (s.length() <= 10) {
            s = (new StringBuilder()).append(s).append(" 00:00:00").toString();
        }
        SimpleDateFormat simpledateformat = new SimpleDateFormat(s1);
        ParsePosition parseposition = new ParsePosition(0);
        Date date = simpledateformat.parse(s, parseposition);
        return new Timestamp(date.getTime());
    }

    public static String timeToStr(String s) {
        char ac[] = s.toCharArray();
        char ac1[] = new char[ac.length];
        int i = 0;
        for (int j = 0; j < ac.length; j++) {
            if (ac[j] != '-' && ac[j] != ' ' && ac[j] != ':') {
                ac1[i] = ac[j];
                i++;
            }
        }

        String s1 = new String(ac1);
        return s1.trim();
    }

    public static Date getDateSinceEpoch(long l) {
        Date date = new Date(l * 1000L);
        return date;
    }

    public static long getSecondsSinceEpoch(Date date) {
        return date.getTime() / 1000L;
    }


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     */
    public static Long date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return Long.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return Long.valueOf("0");
        }
    }

    /**
     * 取得当前时间戳（精确到秒）
     */
    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    public static Date stringToDate(String s) {
        if (null == s || "".equals(s.trim())) {
            return new Date();
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 获取年
     *
     * @param date
     * @return
     */
    public static String getYear(Date date) {
        String year = "";
        if (null != date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR) + "";
        }
        return year;
    }

    public static Date StringToDate01(Date date) {
        String year = getYear(date);
        if (!StringUtils.isEmpty(year)) {
            String time = year + "-01-01 00:00:00";
            SimpleDateFormat simpledateformat = new SimpleDateFormat(dateFormat);
            try {
                return simpledateformat.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Date StringToDate12(Date date) {
        String year = getYear(date);
        if (!StringUtils.isEmpty(year)) {
            String time = year + "-12-31 00:00:00";
            SimpleDateFormat simpledateformat = new SimpleDateFormat(dateFormat);
            try {
                return simpledateformat.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
