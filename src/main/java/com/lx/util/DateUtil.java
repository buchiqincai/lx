package com.lx.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 * @author lx
 * @date 2015-07-11
 *
 */
public class DateUtil {
	// hh是12小时制,HH是24小时制
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static String MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	/**
	 * yyyy-MM-dd
	 */
	public static String DAY_FORMAT = "yyyy-MM-dd";
	/**
	 * yyyyMM
	 */
	public static String MONTH_FORMAT = "yyyyMM";
   /**
    * 将字符床类型转换为时间戳类型
    * @param str 需要转换的字符窜
    * @param format 格式
    * @return
    * @throws ParseException
    */
	public static Timestamp strToStamp(String str,String format) throws ParseException{
		  if(StringUtils.isEmpty(str) || StringUtils.isEmpty(format)){
			  return null;
		  }
		  SimpleDateFormat f = new SimpleDateFormat(format);
          Date d = (Date) f.parseObject(str);
		  Timestamp ts = new Timestamp(d.getTime());
		  return ts;        
	}
	/**
	 * Timestamp类型转换为String类型
	 * @param time
	 * @return
	 */
	public static String stampToStr(Timestamp time){
        String tsStr ="";  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        tsStr = sdf.format(time);  
        return tsStr;
	}
	/**
	 * Timestamp类型转换为String类型,显示到年月日
	 * @param time
	 * @return
	 */
	public static String stampToStrYMD(Timestamp time){
        String tsStr ="";  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        tsStr = sdf.format(time);  
        return tsStr;
	}
  /**
   * 将日期类型转换为字符串类型
   * @param date
   * @return
   */
   public static String dateToStr(Date date){
		 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String str = sdf.format(date);
		 return str;
   }
   /**  
    * 计算两个日期之间相差的天数  
    * @param smdate 较小的时间 
    * @param bdate  较大的时间 
    * @return 相差天数 
    * @throws ParseException  
    */    
   public static int daysBetweenDate(Date smdate,Date bdate) throws ParseException    
   {    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
       smdate=sdf.parse(sdf.format(smdate));  
       bdate=sdf.parse(sdf.format(bdate));  
       Calendar cal = Calendar.getInstance();    
       cal.setTime(smdate);    
       long time1 = cal.getTimeInMillis();                 
       cal.setTime(bdate);    
       long time2 = cal.getTimeInMillis();         
       long between_days=(time2-time1)/(1000*3600*24);  
           
        return Integer.parseInt(String.valueOf(between_days));           
   }
   
   /** 
   *字符串的日期格式的计算 
   */  
       public static int daysBetweenStr(String smdate,String bdate) throws ParseException{  
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
           Calendar cal = Calendar.getInstance();    
           cal.setTime(sdf.parse(smdate));    
           long time1 = cal.getTimeInMillis();                 
           cal.setTime(sdf.parse(bdate));    
           long time2 = cal.getTimeInMillis();         
           long between_days=(time2-time1)/(1000*3600*24);  
               
          return Integer.parseInt(String.valueOf(between_days));     
       } 
       /**
        * 计算两个日期相差多少小时
        * @param startTime
        * @param endTime
        * @param format
        * @return
        * @throws ParseException
        */
       public static Long dateDiff(String startTime, String endTime, String format) throws ParseException {
    	 //按照传入的格式生成一个simpledateformate对象
    	 SimpleDateFormat sd = new SimpleDateFormat(format);
    	 long nd = 1000*24*60*60;//一天的毫秒数
    	 long nh = 1000*60*60;//一小时的毫秒数
    	 //获得两个时间的毫秒时间差异
    	 long diff=sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
    	 long hour = diff%nd/nh;//计算差多少小时
         return hour;
       }
       /**
        * 判断两个日期是不是在同一天
        */
       public static boolean isSameDay(Date day1, Date day2) {
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	    String ds1 = sdf.format(day1);
    	    String ds2 = sdf.format(day2);
    	    if (ds1.equals(ds2)) {
    	        return true;
    	    } else {
    	        return false;
    	    }
    	}
       /**
        * 计算当前日期的前七天
        * @return
        */
       public static Timestamp findSenvenBefore(){
    	   Timestamp time = new Timestamp(System.currentTimeMillis()); 
    	   long stime =time.getTime()-60*60*24*1000*6;
    	   Timestamp sdate = new Timestamp(stime);
    	   return sdate;
       }
       /**
        * 获取某段日期内的所有日期
        * @param dBegin
        * @param dEnd
        * @return
        */
       public static List<Date> findDates(Date dBegin, Date dEnd) {  
           List lDate = new ArrayList();  
           lDate.add(dBegin);  
           Calendar calBegin = Calendar.getInstance();  
           // 使用给定的 Date 设置此 Calendar 的时间    
           calBegin.setTime(dBegin);  
           Calendar calEnd = Calendar.getInstance();  
           // 使用给定的 Date 设置此 Calendar 的时间    
           calEnd.setTime(dEnd);  
           // 测试此日期是否在指定日期之后    
           while (dEnd.after(calBegin.getTime())) {  
               // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
               calBegin.add(Calendar.DAY_OF_MONTH, 1);  
               lDate.add(calBegin.getTime());  
           }  
           return lDate;  
       }  
       
   	/**
   	 * <b>计算当前时间的本周第一天</b>
   	 * @author 李响
   	 * @return
   	 * @throws ParseException 
   	 */
   	public static Timestamp fistTime() throws ParseException{
   		 SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
   		 Calendar ca=Calendar.getInstance();
   		 int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
   		 // 减去dayOfWeek,得到第一天的日期，因为Calendar用０－６代表一周七天，所以要减一
   		 ca.add(Calendar.DAY_OF_WEEK, -(dayOfWeek - 1));
   		 Date firstDateOfWeek = ca.getTime();
   		 String str = formatDate.format(firstDateOfWeek);
   		 //第一天0点的毫秒值
   		 long longTime=formatDate.parse(str).getTime();
   		 Timestamp time=new Timestamp(longTime);
   		 return time;
   	}
   	
   	/**
   	 * <b>时间转换
   	 *  一年前的显示 ：yy-mm-dd
   	 *  一小时前的显示：mm-dd
   	 *  一小时内的显示：N分钟前
   	 * </b>
   	 * @param date
   	 * @return
   	 */
   	public static String datetoStr(Date date){
   		Long datetime=date.getTime();
   		Long nowdate=new Date().getTime();
   		BigDecimal b1=new BigDecimal(datetime);
   		BigDecimal b2=new BigDecimal(nowdate);
   		//一小时以内
   		BigDecimal res=b2.subtract(b1);
   		if(res.compareTo(new BigDecimal((long)60*60*1000)) !=1 ){
   			int r1=res.divide(new BigDecimal(60000),0).intValue();
   			return ""+r1+"分钟前";
   		}
   		//一年以上
   		if(res.compareTo(new BigDecimal((long)365*24*60*60*1000)) ==1){
   			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
   			return sdf.format(date);
   		}
   		//一年以内
   		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
   		return sdf.format(date);
   	}
}
