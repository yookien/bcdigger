package com.bcdigger.kingdee.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 对日期做处理的工具类.
 * 
 * @author robin
 * @version 1.0
 */
public final class DateTime {

	static final int[] DAY_OF_YEAR = { 31, 59, 90, 120, 151, 181, 212, 243,
			273, 304, 334, 365 };

	/** Note: all day ranged from 1-31 all month ranged from 1-12 */

	// minimum number of days in a month
	static final int[] DAYS_IN_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };

	static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");

	static final SimpleDateFormat SDF_NOY_DATE = new SimpleDateFormat("MM-dd");

	static final SimpleDateFormat SSDF_DATE = new SimpleDateFormat("yyyy/M/d");

	static final SimpleDateFormat SDF_DATENO = new SimpleDateFormat("yyyyMMdd");

	static final SimpleDateFormat SDF_DATETIME = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static final SimpleDateFormat SDF_DATE_TIME = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	static final SimpleDateFormat SDF_LONGSTRING = new SimpleDateFormat(
			"yyMMddHHmmssSSS");

	static final SimpleDateFormat SDF_LONGDATETIME = new SimpleDateFormat(
			"MMMM dd, yyyy, EEE. hh:mm a", Locale.US);

	static final SimpleDateFormat SDF_SHORTDATETIME = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm:ss");
	
	static final SimpleDateFormat SDF_T_DATETIME = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss");
	static final SimpleDateFormat SDF_UTC_DATETIME = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS");
	static final SimpleDateFormat SDF_KINDEE=new SimpleDateFormat("MMdd");

	/**
	 * Return an adjusted java.util.Date
	 * 
	 * @param d
	 *            A java.util.Date used to set Calendar's time
	 * @param val
	 *            A value to be set into Calendar
	 * @param field
	 *            A key specifies which field will the value is set to
	 * @return an adjusted java.util.Date
	 */
	public static Date add(java.util.Date d, int val, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(field, val);

		return cal.getTime();
	}

	/**
	 * Return day of week
	 * 
	 * @param dd
	 *            Specifies a date's day
	 * @param mm
	 *            Specifies a date's month
	 * @param yyyy
	 *            Specifies a date's year
	 * @return day of week
	 */
	public static int dayOfWeek(int dd, int mm, int yyyy) {
		if (mm < 3) {
			mm += 13;
			yyyy--;
		} else {
			mm++;
		}

		return (((dd + (int) ((26 * mm) / 10) + yyyy + (int) (yyyy / 4))
				- (int) (yyyy / 100) + (int) (yyyy / 400) + 5) % 7) + 1;
	}

	/**
	 * Return day of year
	 * 
	 * @param dd
	 *            Specifies a date's day
	 * @param mm
	 *            Specifies a date's month
	 * @param yyyy
	 *            Specifies a date's year
	 * @return day of year
	 */
	public static int dayOfYear(int dd, int mm, int yyyy) {
		if (mm == 1) {
			return dd;
		}

		return DAY_OF_YEAR[mm - 2] + (((mm > 2) && isLeapYear(yyyy)) ? 1 : 0)
				+ dd;
	}

	/**
	 * Return the number of days in a specified month and year mm is 1 to 12
	 * return ranged from 28 to 31
	 * 
	 * @param mm
	 *            Specifies a date's month
	 * @param yyyy
	 *            Specifies a date's year
	 * @return the number of days in a specified month and year
	 */
	public static int daysInMonth(int mm, int yyyy) {
		return DAYS_IN_MONTH[mm - 1]
				+ (((mm == 2) && isLeapYear(yyyy)) ? 1 : 0);
	}

	/**
	 * 格式化时间为yyyy-MM-dd.
	 * 
	 * @param d
	 *            Date.
	 * @return String
	 */
	public static String formatDate(java.util.Date d) {
		return d != null ? SDF_DATE.format(d) : "";
	}

	/**
	 * 格式化时间为 MM-dd.
	 * 
	 * @param d
	 * @return
	 */
	public static String formatNoYearDate(java.util.Date d) {
		return d != null ? SDF_NOY_DATE.format(d) : "";
	}
	/**
	 * 格式化MMdd
	 * @param d
	 * @return
	 */
    public static String formatKindeeDayDate(java.util.Date d){
    	return d != null ? SDF_KINDEE.format(d) : "";
    	
    }
	
	
	
	
	/**
	 * 格式化时间为yyyyMMdd.
	 * 
	 * @param d
	 *            Date.
	 * @return String
	 */
	public static String formatDateNO(java.util.Date d) {
		return d != null ? SDF_DATENO.format(d) : "";
	}

	/**
	 * 格式化时间为yyyy-MM-dd HH:mm:ss.
	 * 
	 * @param d
	 *            Date.
	 * @return String
	 */
	public static String formatDateTime(java.util.Date d) {
		return d != null ? SDF_DATETIME.format(d) : "";
	}

	/**
	 * 格式化时间为yyyyMMddHHmmss
	 * 
	 * @param d
	 *            Date.
	 * @return String
	 */
	public static String formatTimeToString(java.util.Date d) {
		return d != null ? SDF_DATE_TIME.format(d) : "";
	}

	/**
	 * 格式化时间为yyMMddHHmmssSSS.
	 * 
	 * @param d
	 *            Date.
	 * @return String
	 */
	public static String formatLongString(java.util.Date d) {
		return d != null ? SDF_LONGSTRING.format(d) : "";
	}

	/**
	 * 格式化时间为MMMM dd, yyyy, EEE. hh:mm a.
	 * 
	 * @param d
	 *            Date.
	 * @return String
	 */
	public static String formatLongDateTime(java.util.Date d) {
		return d != null ? SDF_LONGDATETIME.format(d) : "";
	}

	/**
	 * 格式化时间为yyyy-MM-dd HH:mm.
	 * 
	 * @param d
	 *            Date.
	 * @return String
	 */
	public static String formatShortDateTime(java.util.Date d) {
		return d != null ? SDF_SHORTDATETIME.format(d) : "";
	}

	/**
	 * 格式化时间为HH:mm:ss.
	 * 
	 * @param d
	 *            Date.
	 * @return String
	 */
	public static String formatTime(java.util.Date d) {
		return d != null ? SDF_TIME.format(d) : "";
	}

	static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	static Calendar getCalendar(int year, int month, int date, int hour,
			int minute, int second, int millisecond) {
		Calendar cal = getCalendar();
		cal.set(year, month - 1, date, hour, minute, second);
		cal.set(Calendar.MILLISECOND, millisecond);

		return cal;
	}

	/**
	 * Return an java.util.Date of current time
	 * 
	 * @return an java.util.Date of current time
	 */
	public static Date getDate() {
		return getCalendar().getTime();
	}

	/**
	 * Return an java.util.Date of a given time
	 * 
	 * @param year
	 *            the value used to set the YEAR time field.
	 * @param month
	 *            the value used to set the MONTH time field
	 * @param date
	 *            the value used to set the DATE time field.
	 * @return an java.util.Date of a given time
	 */
	public static Date getDate(int year, int month, int date) {
		return getCalendar(year, month, date, 0, 0, 0, 0).getTime();
	}

	/**
	 * Return an java.util.Date of a given time
	 * 
	 * @param year
	 *            the value used to set the YEAR time field.
	 * @param month
	 *            the value used to set the MONTH time field
	 * @param date
	 *            the value used to set the DATE time field.
	 * @param hour
	 *            the value used to set the HOUR_OF_DAY time field.
	 * @param minute
	 *            the value used to set the MINUTE time field.
	 * @param second
	 *            the value used to set the SECOND time field.
	 * @return an java.util.Date of a given time
	 */
	public static Date getDate(int year, int month, int date, int hour,
			int minute, int second) {
		return getCalendar(year, month, date, hour, minute, second, 0)
				.getTime();
	}

	/**
	 * Return an java.util.Date of a given time
	 * 
	 * @param year
	 *            the value used to set the YEAR time field.
	 * @param month
	 *            the value used to set the MONTH time field
	 * @param date
	 *            the value used to set the DATE time field.
	 * @param hour
	 *            the value used to set the HOUR_OF_DAY time field.
	 * @param minute
	 *            the value used to set the MINUTE time field.
	 * @param second
	 *            the value used to set the SECOND time field.
	 * @param millisecond
	 *            the value used to set the MILLISECOND time field.
	 * @return an java.util.Date of a given time
	 */

	public static Date getDate(int year, int month, int date, int hour,
			int minute, int second, int millisecond) {
		return getCalendar(year, month, date, hour, minute, second, millisecond)
				.getTime();
	}

	/**
	 * Return an java.util.Date of current time
	 * 
	 * @return an java.util.Date of current time
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(getCalendar().getTime().getTime());
	}

	/**
	 * Determines if a year is leap year
	 * 
	 * @param yyyy
	 *            Specifies a year like 2004
	 * 
	 * @return true if the year is leap year,false else
	 */
	public static boolean isLeapYear(int yyyy) {
		return ((yyyy % 400) == 0)
				|| (((yyyy % 4) == 0) && ((yyyy % 100) != 0));
	}

	/**
	 * Parses date from the beginning of the given string according to given
	 * format to produce a date
	 * 
	 * @param str
	 *            A string to be parsed
	 * @return A date parsed from the string
	 * @throws ParseException
	 */
	public static java.util.Date parseDate(String str) throws ParseException {
		if (str == null)
			return null;

		if (str.length() == 10){
			return SDF_DATE.parse(str);
		}else if (str.length() == 8) {
			if (str.indexOf(":") != -1){
				return SDF_TIME.parse(str);
			}else{
				return SSDF_DATE.parse(str);
			}
		}else if (str.length() == 16){
			return SDF_SHORTDATETIME.parse(str);
		}else if (str.length() == 19){
			if(str.indexOf(" ")!=-1){
				return SDF_DATETIME.parse(str);
			}
			return SDF_T_DATETIME.parse(str);
		}else if(str.length()>20 && str.length() <= 23){
			return SDF_UTC_DATETIME.parse(str);
		}
		return SDF_DATETIME.parse(str);
	}

	public static java.util.Date parseDate(String str, Date def) {
		try {
			return parseDate(str);
		} catch (ParseException e) {
			return def;
		}
	}

	/**
	 * Return a java.util.Date object that round / truncate to a specified unit
	 * 
	 * @param d
	 *            a date to be truncated
	 * @param unit
	 *            unit used to truncate this date
	 * @return a java.util.Date object that round / truncate to a specified unit
	 */
	public static Date round(java.util.Date d, int unit) {
		Calendar _cal = Calendar.getInstance();
		_cal.setTime(d);

		if ((unit == Calendar.DATE) || (unit == Calendar.DAY_OF_MONTH)) {
			_cal.set(Calendar.HOUR_OF_DAY, 0);
			_cal.set(Calendar.MINUTE, 0);
			_cal.set(Calendar.SECOND, 0);
			_cal.set(Calendar.MILLISECOND, 0);
		} else if (unit == Calendar.HOUR) {
			_cal.set(Calendar.MINUTE, 0);
			_cal.set(Calendar.SECOND, 0);
			_cal.set(Calendar.MILLISECOND, 0);
		} else if (unit == Calendar.MINUTE) {
			_cal.set(Calendar.SECOND, 0);
			_cal.set(Calendar.MILLISECOND, 0);
		} else if (unit == Calendar.SECOND) {
			_cal.set(Calendar.MILLISECOND, 0);
		}

		return _cal.getTime();
	}

	/**
	 * 返回当月第一天的日期字符（如"2007-12-01"）
	 * 
	 * @param cal
	 *            Calendar
	 * @return String
	 */
	public static String getFirstDateOfMonth(Calendar cal) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date(cal.get(Calendar.YEAR) - 1900, cal
				.get(Calendar.MONTH), 1));
	}

	/**
	 * 返回当月最后一天的日期字符（如"2008-01-31"）
	 * 
	 * @param cal
	 *            Calendar
	 * @return String
	 */
	public static String getLastDateOfMonth(Calendar cal) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date(cal.get(Calendar.YEAR) - 1900, cal
				.get(Calendar.MONTH), cal
				.getActualMaximum(Calendar.DAY_OF_MONTH)));
	}

	/**
	 * 返回指定某天的日期字符（如"2008-01-31"）
	 * 
	 * @param cal
	 *            Calendar
	 * @param int monthDay 指定某天
	 * @return String
	 */
	public static String getFixedDateOfMonth(Calendar cal, int monthDay) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date(cal.get(Calendar.YEAR) - 1900, cal
				.get(Calendar.MONTH), monthDay));
	}

	/**
	 * 取日期前(后)多少天 正数前几天 负数后几天
	 * 
	 * @param curDate
	 * @param days
	 * @return
	 */
	public static Date getDateBeforeDay(Date curDate, int days) {
		Date resultDate;
		days = -days;
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DAY_OF_YEAR, days);
		resultDate = cal.getTime();
		return resultDate;
	}

	public static String getCompareNowTime(Date curDate, int days, int hangup) {
		if (hangup == 1) {
			return "#EE0000";
		}
		Date resultDate;
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DAY_OF_YEAR, days);
		resultDate = cal.getTime();

		if (resultDate.getTime() > new Date().getTime()) {
			return "";
		} else {
			return "#FFC125";
		}

	}

	/**
	 * 取日期前多少天
	 * 
	 * @param curDate
	 * @param days
	 * @return
	 */
	public static String getDateBefOfAft(Date curDate, int days) {
		Date date = getDateBeforeDay(curDate, days);
		return SDF_DATE.format(date);
	}

	/**
	 * 返回指定日期当月第一天的日期字符（如"2007-12-01"）
	 * 
	 * @param cal
	 *            Calendar
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new Date(cal.get(Calendar.YEAR) - 1900, cal.get(Calendar.MONTH),
				1);
	}

	/**
	 * 返回指定日期当月最后一天的日期字符（如"2008-01-31"）
	 * 
	 * @param cal
	 *            Calendar
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new Date(cal.get(Calendar.YEAR) - 1900, cal.get(Calendar.MONTH),
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获取某一时间的下月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfNextMonth(Date date) {
		return getDateBeforeDay(getLastDayOfMonth(date), -1);
	}

	/**
	 * 设置某一时间的时-分-秒具体值 author xieshanggao 2011-8-16 上午09:19:11
	 * 
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date setH_M_S(Date date, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 获取指定日期的小时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("HH");
		String time = f.format(date);
		if (DataUtil.isInteger(time)) {
			return Integer.parseInt(time);
		} else {
			return 1;
		}
	}

	/**
	 * 获取指定日期的分钟数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("mm");
		String time = f.format(date);
		if (DataUtil.isInteger(time)) {
			return Integer.parseInt(time);
		} else {
			return 1;
		}
	}

	/**
	 * 时间减去 指定的 分
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date subtractMinute(Date date, int minute) {
		long Time = (date.getTime() / 1000) - 60 * minute;// 减去 n 分
		Date newdate = new Date();
		newdate.setTime(Time * 1000);
		return newdate;
	}

	/**
	 * 将0时区时间转换为东八区时间
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date transGMTTime(String dateStr) {
		Date chinaDate = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date dateTime = dateFormat.parse(dateStr);
			TimeZone chinaTimeZone = TimeZone.getTimeZone("Etc/GMT+8");
			Long targetTime = dateTime.getTime() - chinaTimeZone.getRawOffset();
			SimpleDateFormat _format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String date = _format.format(targetTime);

			chinaDate = _format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return chinaDate;
	}
	/**
	 * 将东八区时间转换为0时区时间
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String transIOSTime(Date dateStr) {
		if (dateStr == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		TimeZone chinaTimeZone = TimeZone.getTimeZone("Etc/GMT+8");
		Long targetTime = dateStr.getTime() + chinaTimeZone.getRawOffset();
		return dateFormat.format(targetTime);
	}

	/**
	 * 获取当天0点0分
	 * 
	 * @return
	 */
	public static Date getTodayStartTime() {
		long current=System.currentTimeMillis();//当前时间毫秒数
		long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
		
		/**
		 * long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
			long yesterday=System.currentTimeMillis()-24*60*60*1000;//昨天的这一时间的毫秒数
			System.out.println(new Timestamp(current));//当前时间
			System.out.println(new Timestamp(yesterday));//昨天这一时间点
			System.out.println(new Timestamp(zero));//今天零点零分零秒
			System.out.println(new Timestamp(twelve));//今天23点59分59秒
		 */
		return new Timestamp(zero);
	}
	/**
	 * 两个时间差天数
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 时间戳转时间
	 * 
	 * @return
	 */
	public static Date prsDateFromUnixTime(long unixTime) {
		try {
			Date resultDate = null;
			if (unixTime != 0){
				resultDate = new Date(unixTime * 1000);
			}
			return resultDate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//日期转为时间戳
	public static int getUnixTime(Date date) {
		try {
			return (int) (date.getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	//时间戳转日期
	public static Date unixTimeToDate(long unixTime) {
		try {
			if (unixTime != 0){
				return  new Date(unixTime * 1000);
			}else{
				return new Date();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Date();
		}
	}
	public static void main(String[] a) {
		Date endTime = DateTime.getTodayStartTime();
		Date startTime = getDateBeforeDay(endTime, 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = format.format(startTime);
		String endTimeStr = format.format(endTime);
		System.out.println("startTimeStr:"+startTimeStr);
		System.out.println("endTimeStr:"+endTimeStr);
		
	}

}