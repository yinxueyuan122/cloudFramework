package com.cn.cloud.core.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 
 * @ClassName   : DateUtil.java
 * @Description : 日期工具栏
 * @author Yin Xueyuan
 * @since 2017年3月9日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年3月9日        Yin Xueyuan           fisrt create
 * </pre>
 */
public class DateUtil {

	public static final String DEFAULT_PATTERN 		= "yyyy-MM-dd";
	public static final String DEFAULT_TS_PATTERN 	= "yyyy-MM-dd HH:mm:ss.S";

	public DateUtil(){}

	public static Date convertToDate(String value){
		return convertToDate(value, null);
	}

	public static java.sql.Date convertToSqlDate(String value){
		return convertToSqlDate(convertToDate(value));
	}

	public static java.sql.Date convertToSqlDate(Date date){
		if(date == null){
			return null;
		}else{
			return new java.sql.Date(date.getTime());
		}
	}

	public static Date convertToDate(String value, String pattern) {
        DateFormat df = null;

        try {

            if (StringUtils.isEmpty(value)) {
                return null;
            }

            if(pattern != null){
            	df = new SimpleDateFormat(pattern);
            }else{
            	df = new SimpleDateFormat(DEFAULT_PATTERN);
            }

            return df.parse(value);
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return null;

    }

	public static Timestamp convertToTimestamp(String value){
		return convertToTimestamp(value, null);
	}

	public static Timestamp convertToTimestamp(String value, String pattern){
		Date date = convertToDate(value, pattern);

		if(date != null){
			return new Timestamp(date.getTime());
		}

		return null;
	}

    public static String convertToString(Object value, String pattern) {
    	return convertToString(value, pattern, LocaleContextHolder.getLocale());
    }

    public static String convertToString(Object value, String pattern, Locale locale) {

        if (value instanceof Date) {
            DateFormat df = null;

            try {

	            if(pattern != null){
	            	df = new SimpleDateFormat(pattern, locale);
	            }else{
	            	df = new SimpleDateFormat(DEFAULT_PATTERN, locale);
	            }

	            if (value instanceof Timestamp) {
	                df = new SimpleDateFormat(DEFAULT_TS_PATTERN, locale);
	            }


                return df.format(value);
            } catch (Exception e) {
            	e.printStackTrace();
            }

            return null;
        } else {
            return value.toString();
        }
    }

    public static String convertToDateTimeString(Object value) {

        if (value instanceof Date) {
            DateFormat df = null;

            try {
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return df.format(value);
            } catch (Exception e) {
            	e.printStackTrace();
            }

            return null;
        } else {
            return null;
        }
    }
    public static String convertToDateString(Object value) {

        if (value instanceof Date) {
            DateFormat df = null;

            try {
                df = new SimpleDateFormat("yyyy-MM-dd");
                return df.format(value);
            } catch (Exception e) {
            	e.printStackTrace();
            }

            return null;
        } else {
            return null;
        }
    }


	public static String getDate(String pattern){
		return getDate(Calendar.getInstance().getTime(), pattern, -1, -1);
	}

	public static String getDate(String date, String sourcePattern, String destPattern){
		return getDate(convertToDate(date, sourcePattern), destPattern);
	}


	public static String getDate(String pattern, int field, int amount){
		return getDate(Calendar.getInstance().getTime(), pattern, field, amount);
	}


	public static String getDate(Date date, String pattern){
		return getDate(date, pattern, -1, -1);
	}



	public static String getDate(Date date, String pattern, int field, int amount){
		if(date == null || pattern == null){
			return "";
		}

		if(field != -1){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(field, amount);

			date = calendar.getTime();
		}

		SimpleDateFormat sf=new SimpleDateFormat(pattern);
		return sf.format(date);
	}


	public static Date add(Date date, int field, int amount){

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(field, amount);

			return calendar.getTime();


	}

	public static Date getLastDateOfMonth(Date date){
		return DateUtil.add(DateUtil.add(date, Calendar.MONTH, 1), Calendar.DATE, -1);
	}



	public static String getDate(long millis, String pattern) {
		if(millis == 0L || pattern == null){
			return "";
		}

		return getDate(new Date(millis), pattern, -1, -1);
	}


	public static int getMaximumDateOfMonth(int year, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}


	public static int getMinimalDaysInFirstWeek(int year, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DATE, 1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}


	public static int getMaximumDaysInLastWeek(int year, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, getMaximumDateOfMonth(year, month));
		return calendar.get(Calendar.DAY_OF_WEEK);
	}


	public static int getDayOfWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}


	public static long getCurrentTimeMillis(){
		return Calendar.getInstance(Locale.KOREA).getTimeInMillis();
	}


	public static int daysBetween(Date early, Date late) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(early);
		c2.setTime(late);

		return (int) (toJulian(c2) - toJulian(c1));
	}

	public static final float toJulian(Calendar c) {
		int Y = c.get(Calendar.YEAR);
		int M = c.get(Calendar.MONTH);
		int D = c.get(Calendar.DATE);
		int A = Y / 100;
		int B = A / 4;
		int C = 2 - A + B;
		float E = (int) (365.25f * (Y + 4716));
		float F = (int) (30.6001f * (M + 1));
		float JD = (C + D + E + F) - 1524.5f;
		return JD;
	}


}
