package com.helloqidi.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.helloqidi.dao.UserDao;
import com.helloqidi.model.User;
import com.helloqidi.util.DbUtil;
import com.helloqidi.util.StringUtil;

public class LoginServlet extends HttpServlet {

	DbUtil dbUtil=new DbUtil();
	UserDao userDao=new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		//保存住用户输入的表单的原始信息
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		
		System.out.println(userName);
		System.out.println(password);
		
		//判空
		if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)){
			request.setAttribute("error", "用户名或密码为空");
			//服务器端跳转
			request.getRequestDispatcher("index.jsp").forward(request, response);
			//不往下执行
			return;
		}
		
		User user=new User(userName,password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				request.setAttribute("error", "用户名或密码错误！");
				// 服务器跳转
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				// 获取Session
				HttpSession session=request.getSession();
				session.setAttribute("currentUser", currentUser);
				// 客户端跳转
				response.sendRedirect("main.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
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
