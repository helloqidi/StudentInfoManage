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
		
		//����ס�û�����ı���ԭʼ��Ϣ
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		
		System.out.println(userName);
		System.out.println(password);
		
		//�п�
		if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)){
			request.setAttribute("error", "�û���������Ϊ��");
			//����������ת
			request.getRequestDispatcher("index.jsp").forward(request, response);
			//������ִ��
			return;
		}
		
		User user=new User(userName,password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				request.setAttribute("error", "�û������������");
				// ��������ת
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				// ��ȡSession
				HttpSession session=request.getSession();
				session.setAttribute("currentUser", currentUser);
				// �ͻ�����ת
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
