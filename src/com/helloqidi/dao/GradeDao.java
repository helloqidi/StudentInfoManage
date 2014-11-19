package com.helloqidi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.helloqidi.model.Grade;
import com.helloqidi.model.PageBean;
import com.helloqidi.util.StringUtil;

public class GradeDao {

	public ResultSet gradeList(Connection con,PageBean pageBean,Grade grade)throws Exception{
		//拼接修改查询字符串时，建议使用StringBuffer
		StringBuffer sb=new StringBuffer("select * from t_grade");

		if(grade!=null && StringUtil.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}
		
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		//PreparedStatement pstmt=con.prepareStatement(sb.toString());
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int gradeCount(Connection con,Grade grade)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_grade");
		if(StringUtil.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}		
	}
	
	/**
	 * delete from tableName where field in (1,3,5)
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int gradeDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_grade where id in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		//删了几条就返回几条
		return pstmt.executeUpdate();
	}
	
	/**
	 * 增加
	 */
	public int gradeAdd(Connection con,Grade grade)throws Exception{
		String sql="insert into t_grade values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		return pstmt.executeUpdate();
	}
	
	public int gradeModify(Connection con,Grade grade)throws Exception{
		String sql="update t_grade set gradeName=?,gradeDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		pstmt.setInt(3, grade.getId());
		return pstmt.executeUpdate();
	}
}
