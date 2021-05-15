package com.libr.login.account;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.libr.server.ConnectService;
import com.libr.user.NewUser;
import com.libr.user.User;

public class Account {

	public Account() {
		// TODO Auto-generated constructor stub
	}
	public int register(NewUser user) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		String sql = "insert into user_info(userid,userpassword,grade,email,phone,question1,answer1,question2,answer2)" + 
				"values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pre = null;
		int row = -1;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, user.getUserid());
			pre.setString(2, user.getUserpassword());
			pre.setInt(3, user.getGrade());
			pre.setString(4, user.getEmail());
			pre.setString(5, user.getPhone());
			pre.setString(6,user.getQuestion1());
			pre.setString(7, user.getAnswer1());
			pre.setString(8, user.getQuestion2());
			pre.setString(9, user.getAnswer2());
			row = pre.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pre!= null) {
			try {
				pre.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return row;
		
	}
	public boolean enquiry(String id,String password,int grade) {
		boolean result = false;
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		String sql = "select userid from user_info where userid=? and userpassword=? and grade=?";
		PreparedStatement pre = null;
		ResultSet set = null;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, id);
			pre.setString(2, password);
			pre.setInt(3, grade);
			set = pre.executeQuery();
			if(set.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pre!=null) {
			try {
				pre.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(set!=null) {
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
		
	}



}
