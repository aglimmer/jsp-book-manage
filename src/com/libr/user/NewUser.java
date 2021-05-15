package com.libr.user;

public class NewUser {

	public NewUser() {
		// TODO Auto-generated constructor stub
	}
	
	private String userid;
	private String userpassword;
	private String email;
	
//	[我最好的朋友叫什么名字][我最想去的地方][我最喜欢的水果]
	private String question1;
	private String question2;
	private String answer1;
	private String answer2;
	private int grade;
	private String phone;
	private String realname;
	private String idcard;
	
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserid() {
		return userid;
	}
	
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUserpassword() {
		return userpassword;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setGrade(int grade) {
		this.grade = grade;
		
	}
	public int getGrade() {
		return grade;
		
	}
	
	
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String reamname) {
		this.realname = reamname;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getIdcard() {
		return idcard;
	}
	public String getPhone() {
		// TODO Auto-generated method stub
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}
	public String getQuestion1() {
		return question1;
	}
	public void setAnswer1(String answer1) {
		this.answer1=answer1;
	}
	public String getAnswer1() {
		// TODO Auto-generated method stub
		return answer1;
	}
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}
	public String getQuestion2() {
		return question2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer2() {
		// TODO Auto-generated method stub
		return answer2;
	}
	
	
	
}
