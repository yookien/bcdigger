package com.bcdigger.kingdee.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 *<辅助类>
 *@author  liubei
 *@version 
 *@date 2010-8-12 
 **/
public class DataUtil {

	/**
	 * 是否全部是数字
	 * @param dataStr
	 * @return
	 */
	public static boolean isAllDigit(String dataStr)
	{
		if(dataStr==null||"".equals(dataStr.trim()))
		{
			return false;
		}
		for (int i=0; i< dataStr.length();i++) { 
			if (!Character.isDigit(dataStr.charAt(i))) 
			{ 
				return false;
			} 
		}
		return true;
	}
	/**
	 * 是否为空
	 */
	public static boolean isNullForStr(String str)
	{
		if(str==null||"".equals(str))
		{
			return true;
		}
		else return false;
	}
	public static boolean matchingStr(String expression, String str)
	{
		Pattern p = Pattern.compile(expression); // 正则表达式
		   Matcher m = p.matcher(str); // 操作的字符串
		   boolean b = m.matches();
		   return b;
	}
	/**
	 * 是否为整形
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
		try{
			Integer.parseInt(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	/**
	 * 转换为整数
	 * @param str
	 * @return
	 */
	public static int parseInteger(String str){
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
			return 0;
		}
	}
	/**
	 * 转换为double
	 * @param str
	 * @return
	 */
	public static double parseDouble(String str){
		try{
			return Double.parseDouble(str);
		}catch(Exception e){
			return -1;
		}
	}
	
}
