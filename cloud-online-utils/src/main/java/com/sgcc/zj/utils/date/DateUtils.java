package com.sgcc.zj.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 * @author admin
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_PATTERN2 = "yyyyMMddHHmmss";

    /**
	 * 获得当前日期 同new Date()
	 * @return
	 */
	public static Date now() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();
		return currDate;
	}
	
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String formatDateTime(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }
    
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return -1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据出生日期计算年龄
     *
     * @param birthdayStr
     * @return
     */
    public static int getAgeByBirth(String birthdayStr) {
        SimpleDateFormat format = new SimpleDateFormat(DateUtils.DATE_PATTERN);
        Date birthday = null;
        try {
            birthday = format.parse(birthdayStr);

            int age = 0;

            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {// 如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {// 兼容性更强,异常后返回数据
            return 0;
        }
    }

    /**
     * 判断是否过期 指定日期加上天数后的日期比较现在日期
     *
     * @param updateTime 为增加的天数
     * @return
     */
    public static int plusDay(Date updateTime) {
        Date d = new Date();
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            updateTime.setDate(updateTime.getDate() + 90);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (updateTime.getTime() < d.getTime()) {
            return -1;
        }
        return 1;
    }


    /**
     * 在指定日期基础上增加指定天数，不包含起始日，2-20 + 1 = 2-21
     * 
     * @param fromDate 日期起始日
     * @param interval 增加的时间间隔
     * @return 增加指定日期后的目标 日期
     */
    public static Date plusDay(Date fromDate, Integer interval) {
    	if (null == fromDate || null == interval) {
    		return null;
    	}
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fromDate);
    	calendar.add(Calendar.DATE, interval);
    	return calendar.getTime();
    }

    /**
     * "yyyyMMdd"格式String转date
     */
    public static Date stringtoDate(String dateStr) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse(dateStr);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * "yyyyMMdd"格式 转"yyyy.MM.dd"
     */
    public static String change(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
        Date date = null;
        String str = null;
        try {
            date = format.parse(dateStr);
            str = format1.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return dateStr;
        }
        return str;
    }

    public static String getCurrentTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
        return format.format(date);
    }

    public static String getTimeyyyyMMddHHmmss() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN2);
        return format.format(date);
    }

    /**
     * 获取两个日期之间的天数(天数计算+1把当天加进去)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long getDateNum(Date startTime, Date endTime) {
        long quot = 0;
        quot = startTime.getTime() - endTime.getTime();
        quot = quot / 1000 / 60 / 60 / 24;
        return quot+1;
    }

}
