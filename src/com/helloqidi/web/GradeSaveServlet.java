package com.helloqidi.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.helloqidi.dao.GradeDao;
import com.helloqidi.model.Grade;
import com.helloqidi.util.DbUtil;
import com.helloqidi.util.ResponseUtil;
import com.helloqidi.util.StringUtil;

/**
 * ���桢�޸�
 */
public class GradeSaveServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	GradeDao gradeDao = new GradeDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String gradeName = request.getParameter("gradeName");
		String gradeDesc = request.getParameter("gradeDesc");
		//û��idʱ��null
		String id = request.getParameter("id");
		Grade grade = new Grade(gradeName, gradeDesc);
		if (StringUtil.isNotEmpty(id)) {
			grade.setId(Integer.parseInt(id));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			if (StringUtil.isNotEmpty(id)) {
				saveNums = gradeDao.gradeModify(con, grade);
			} else {
				saveNums = gradeDao.gradeAdd(con, grade);
			}
			if (saveNums > 0) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
				result.put("errorMsg", "����ʧ��");
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
