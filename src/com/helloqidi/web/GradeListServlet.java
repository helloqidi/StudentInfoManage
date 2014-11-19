package com.helloqidi.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.helloqidi.dao.GradeDao;
import com.helloqidi.model.Grade;
import com.helloqidi.model.PageBean;
import com.helloqidi.util.DbUtil;
import com.helloqidi.util.JsonUtil;
import com.helloqidi.util.ResponseUtil;


public class GradeListServlet extends HttpServlet{
	DbUtil dbUtil=new DbUtil();
	GradeDao gradeDao=new GradeDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//easyui-datagrid通过ajax发送的请求参数page和rows
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String gradeName=request.getParameter("gradeName");
		//如果没有查询条件,则是null
		if(gradeName==null){
			gradeName="";
		}
		Grade grade=new Grade();
		grade.setGradeName(gradeName);
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			//转换查询结果resultset为json格式
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, pageBean,grade));
			int total=gradeDao.gradeCount(con,grade);
			//easyui-datagrid接收的参数是rows,total
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
}
