package com.libr.admin.add.deal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.libr.server.ConnectService;

public class BaseSerial {
	private HashMap<String,String>map = new HashMap<>();
//	public static void main(String[] args) {
//		UpdateQuery p = new UpdateQuery();
//		String serial = p.getLatestSerial("literary_novel");
//		System.out.println("serial:"+serial);
//		
//	}
	public BaseSerial() {
	}
	
//	@SuppressWarnings("unused")
//	private void initKinds() {
//		map.put("roman","romantic_faction");
//		map.put("lite","literary_novel");
//		map.put("hist","history_biography");
//		map.put("comp","computer_science");
//	}
	public void updateSerial(String originalSerial,String kinds,int count) {
		String baseSerial = originalSerial.substring(0,originalSerial.length()-4);
		int num = Integer.valueOf(originalSerial.substring(baseSerial.length()));
		num += count;
		String numFormat = String.format("%04d", num);
		String formSerial = baseSerial+numFormat;
		System.out.println("UpdateQuery对象 formSerial:"+formSerial);//
		ConnectService net = new ConnectService();
		Connection conn = net.getConnect();
		PreparedStatement prep = null;
		int resu = 0;
		String sql = "update serial_number set latestserial=? where kinds = ?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, formSerial);
			prep.setString(2, kinds);
			resu = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("UpdateQuery对象 updateSerial SQL出现异常...");
			e.printStackTrace();
		}
		if(prep!=null) {
			try {
				prep.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				System.out.println("UpdateQuery对象updateSerial prep关闭出现异常...");
				e.printStackTrace();
			}
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getLatestSerial(String kinds) {//调用类Connect
		String serial = null;
		
		Connection conn=null;
		
		ConnectService net = new ConnectService();
	
		conn = net.getConnect();
		if(conn==null) {
			System.out.println("连接失败");
			System.exit(0);
		}
		System.out.println("getLatestSerial对象kinds："+kinds);
		PreparedStatement prep = null;
		ResultSet resu = null;
		String sql = "select latestserial from serial_number where kinds=?";
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1,kinds);
			resu = prep.executeQuery();
			if(resu.next()) {
				serial = resu.getString("latestSerial");
				System.out.println("UpdateQuery获取的系列号："+serial);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("UpdateSerial对象prep异常");
			e.printStackTrace();
		}
		if(prep!=null) {
			try {
				prep.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("UpdateSerial对象prep关闭异常");
				e.printStackTrace();
			}
			
		}
		if(resu!=null) {
			try {
				resu.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("UpdateSerial对象resu关闭异常");
				e.printStackTrace();
			}
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serial;
	}
	

}
