package com.wechat.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理
 * @author lx
 * @date 2016-06-16
 *
 */
public class DateUtils {
	public static  String SECOND_FORMAT="yyyyMMddHHmmss";
	
	/**
	 * 时间转为字符窜
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date,String format){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * 添加指定分钟
	 * @param date
	 * @param format
	 * @param amount 分钟数
	 * @return
	 */
	public static String addMinute(Date date,String format,int amount){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, amount);
		Date resDate=calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(resDate);
	}

	/**
	 * 添加指定天
	 * @param date
	 * @param format 
	 * @param amount 天数
	 * @return
	 */
	public static String addDay(Date date,String format,int amount){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, amount);
		Date resDate=calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(resDate);
	}
}
