package com.helloqidi.model;

public class Grade {

	private int id;
	private String gradeName;
	private String gradeDesc;
	
	
	//构造方法
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//构造方法
	public Grade(String gradeName, String gradeDesc) {
		super();
		this.gradeName = gradeName;
		this.gradeDesc = gradeDesc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeDesc() {
		return gradeDesc;
	}
	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}
	
}
