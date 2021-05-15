package com.libr.server;

import java.awt.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wonzeng 
 * 2020年7月3日
 */
public class ConnectTest{
	/**
	 * 	关闭Connection
	 * @param conn
	 */
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
	
	public static void main(String[] args) {
		ConnectTest p = new ConnectTest();
////		连接数据库
		Connection conn = p.getConnect();
////	Statement用法
//		p.testUpdate(conn);
////		PreparedStatement用法
//		p.testQuery(conn);
////		批量添加
//		p.batchAdd(conn);
////		存储过程
		p.callPro(conn);
////		存储函数
//		p.funAdd(conn);
	}

	public ConnectTest() {
	}


	/**
	 * @param 执行存储过程测试 返回多个结果：
	 * 		1. 返回查询所得的满足条件的所有行； 
	 * 		2. 返回存储过程的调用参数为输出类型的参数值
	 */
	public void callPro(Connection conn) {
		String storedProc = "{call pro_testquery(?,?,?)}";
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall(storedProc);
//			第1个参数的值
			cs.setInt(1, 3);
//			第2个参数
			cs.setString(2, "张静");
//			第3个为输出参数的类型
			cs.registerOutParameter(3, Types.INTEGER);
//			执行存储过程
			ResultSet rs = cs.executeQuery();
//			cs.getString(3)表示第3个位置参数返回所对应的结果
			System.out.println("返回记录数：" + cs.getString(3));
			int cnt = rs.getMetaData().getColumnCount();
//			最多返回一条记录，只需用if
			if (rs.next()) {
//			获取查询所得的列数从1开始
				Map<String, Object> obj = new HashMap<String, Object>();
				for (int i = 1; i <= cnt; i++) {
//				获取列名
					String columns = rs.getMetaData().getColumnName(i);
					obj.put(columns, rs.getString(i));
				}
//				遍历map
				obj.forEach((k, v) -> {
					System.out.println("Connect.callPro():" + k + "\t" + v);
				});
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param conn 使用PreparedStatemented的对象addBatch批量插入数据
	 */
	public void batchAdd(Connection conn) {
		ArrayList<HashMap<Integer, Object>> lst = new ArrayList<HashMap<Integer, Object>>();
		int rows = 6;
		int cols = 3;
		PreparedStatement prep = null;
		for (int i = 0; i < rows; i++) {
			HashMap<Integer, Object> tp = new HashMap<Integer, Object>();
			tp.put(1, "姓名" + i);
			tp.put(2, new Random().nextFloat() * 70);
			tp.put(3, LocalDate.now().toString());
			lst.add(tp);
		}
		try {
			prep = conn.prepareStatement("insert tb_test(name,weight,birth) values(?,?,?)");
			for (int i = 0; i < lst.size(); i++) {
				for (int j = 1; j <= cols; j++) {
					prep.setObject(j, lst.get(i).get(j));
				}
				prep.addBatch();
			}
			int[] res = prep.executeBatch();
			for (int x : res) {
				System.out.println("Connect.batchAdd():" + x);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 	使用Connection对象的prepareCall方法调用存储函数 ，计算两个数所在区间上的所有数之和
	 * 
	 */
	public void funAdd(Connection conn) {
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{?=call fun_getsum(?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setObject(2, 1);
			cs.setObject(3, 5);
			cs.execute();
			int res = cs.getInt(1);
			System.out.println("Connect.funAdd():" + res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Statement，一般用于不需要添加参数的查询
	 */
	public void testUpdate(Connection conn) {
//		String sql = "update tb_test set name='小李'  where id=1";
		String sql = "delete from tb_test where num=12";
		try {
			Statement state = conn.createStatement();
//			executeUpdate方法用于删除、修改类型的sql语句，返回操作影响的行数
			int res = state.executeUpdate(sql);
			System.out.println("Connect.testUpdate():"+res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Connection对象的方法prepareStatement用于对于语句预编译和设置参数，可有效避免非法注入参数
	 * 
	 * 
	 */
	public void testQuery(Connection conn) {
		String sql = "select * from tb_test where birth>date(?) and birth<=date(?)";
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setObject(1, "1990-1-1");
			pre.setObject(2, "1996-1-1");
//			使用PrepareStatement对象的方法 prepareStatement(sql)设置了参数
//			这里不能再使用含有参数的executeQuery(sql)
			ResultSet set = pre.executeQuery();
			int column = set.getMetaData().getColumnCount();
			while (set.next()) {
				for (int i = 1; i <= column; i++) {
					System.out.print(set.getString(i) + "\t");
				}
				System.out.println();

			}
			if (pre != null) {
				pre.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
