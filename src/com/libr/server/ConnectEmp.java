package com.libr.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectEmp {
//	public static void main(String[] args) {
//		ConnectEmp emp = new ConnectEmp();
//		Connection conn = emp.getConnect();
//	}
	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("ConnectEmp.close():Connection对象已关闭");
			} catch (SQLException e) {
				System.out.println("Connect关闭出现异常...");
				e.printStackTrace();

			}
		}

	}

	public ConnectEmp() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 	连接数据库
	 * @return Connection对象
	 */
	public Connection getConnect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动加载成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("加载驱动失败");
//			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8";
		try {
			conn = DriverManager.getConnection(url, "root", "666666");
			System.out.println("Connect.getConnect():" + "数据库连接成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("连接数据库失败...");
//			e.printStackTrace();
		}
		return conn;

	}

}
