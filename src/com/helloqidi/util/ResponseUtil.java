package com.helloqidi.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResponseUtil {

	//Object o������jsonObject������jsonArray
	public static void write(HttpServletResponse response,Object o)throws Exception{
		//��Ϣͷ
		response.setContentType("text/html;charset=utf-8");
		//IO��
		PrintWriter out=response.getWriter();
		//json����ת��Ϊ�ַ���
		out.println(o.toString());
		//ˢ��
		out.flush();
		out.close();
	}
}
