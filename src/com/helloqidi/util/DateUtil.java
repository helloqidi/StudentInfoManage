package com.helloqidi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	//日期转字符串
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	//字符串转日期
	public static Date formatString(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
}
