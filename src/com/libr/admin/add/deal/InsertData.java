package com.libr.admin.add.deal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;

import com.libr.book.Book;
import com.libr.server.ConnectService;

public class InsertData {
//	public static void main(String[] args) {}
	
	public InsertData() {}
//	private String[] kinds= {"computer_science","history_biography","literary_novel","romantic_faction"};
	
	public int executeInsertData(ArrayList<Book> list,String kinds) {
		String sql = "insert into "+kinds+"(bookid,bookname,author,publisher,publishtime,total,surplus)"
				+ " values(?,?,?,?,?,?,?)";
		PreparedStatement pre = null;
		int sum = list.size();
		
		System.out.println("InsertData正在处理中 sum:"+sum);
		
		BaseSerial update = new BaseSerial();
		String originalSerial = update.getLatestSerial(kinds);
		
		System.out.println("InsertData正在处理中 originalSerial:"+originalSerial);
		
		SerialFormat deal = new SerialFormat();
		Stack<String> stack = deal.makeSerials(kinds, originalSerial, sum);
		int result = 0;
		String latestSerial = null;
		if(!stack.isEmpty()) {
		latestSerial = stack.peek();
		Stack<String> qu = new Stack<>();
		while(!stack.isEmpty()) {
			qu.push(stack.pop());
		}
		ConnectService net = new ConnectService();
		Connection conn = net.getConnect();
		try {
			pre = conn.prepareStatement(sql);
			for(int i = 0; i<sum;i++) {
				pre.setString(1,qu.pop());
				pre.setString(2,list.get(i).getBookname());
				pre.setString(3, list.get(i).getAuthor());
				pre.setString(4, list.get(i).getPublisher());
				pre.setString(5,list.get(i).getPublishtime());
				pre.setInt(6, list.get(i).getTotal());
				pre.setInt(7, list.get(i).getSurplus());
				pre.addBatch();
			}
			int rows[] = pre.executeBatch();
			result = rows.length;
			if(result>0) {
				update.updateSerial(latestSerial,kinds,sum);
			}
			if(pre!=null) {
				pre.close();
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("InsertData对象SQL出现异常...");
			e.printStackTrace();
			
		}
		}
		return result;
		
	}
	

}
