package com.helloqidi.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResponseUtil {

	//Object o可能是jsonObject，或者jsonArray
	public static void write(HttpServletResponse response,Object o)throws Exception{
		//消息头
		response.setContentType("text/html;charset=utf-8");
		//IO流
		PrintWriter out=response.getWriter();
		//json对象转换为字符串
		out.println(o.toString());
		//刷新
		out.flush();
		out.close();
	}
}
