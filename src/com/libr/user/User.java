package com.libr.user;

public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}
	private static  String id = "";
	private static String password = "";
	private static int grade = -1;
	public static String getId() {
		return id;
	}
	public static String getPassword() {
		return password;
	}
	public static int getGrade() {
		return grade;
	}
	
	public static void setId(String id) {
		User.id = id;
	}
	public static void setPassword(String password) {
		User.id = password;
	}
	public static void setGrade(int grade) {
		User.grade = grade;
	}
}
