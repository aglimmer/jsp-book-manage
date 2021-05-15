package com.libr.show.borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Stack;

import com.libr.admin.add.deal.BaseSerial;
import com.libr.admin.add.deal.SerialFormat;
import com.libr.book.Book;
import com.libr.server.ConnectService;
import com.libr.user.User;

public class BorrowBook {
//	public static void main(String[]arge) {
//		
//	}

	public BorrowBook() {
		// TODO Auto-generated constructor stub
	}
	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return sdf.format(date);
//		return "20190822";
	}
	
	public int updateSurplus(Book book) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "update "+book.getKinds()+" set surplus=surplus-1 where bookid = ?";
		int row = 0;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, book.getBookid());
			row = pre.executeUpdate();
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
	public int getSurplus(String kinds,String bookid) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "select surplus from "+kinds+" where bookid=?";
		ResultSet set=null;
		int resu = 0;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, bookid);
			set = pre.executeQuery();
			if(set.next()) {
				resu = set.getInt("surplus");
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
		return resu;
		
	}
	
	
	
	public ArrayList<Book> borrowedRecord(String userid) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "select * from record where userid=?";
		ResultSet set = null;
		 ArrayList<Book> list = new ArrayList<>();
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, userid);
			set = pre.executeQuery();
			while(set.next()) {
				Book book = new Book();
				book.setBookid(set.getString("bookid"));
				book.setBookname(set.getString("bookname"));
				book.setBorrowdate(set.getString("borrowdate"));
				book.setBackdate(set.getString("backdate"));
				list.add(book);
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
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
		
		
	}
	public int insertRecord(Book book,Stack<String>stack) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "insert into record(recordserial,bookid,bookname,userid,borrowdate,backdate)values"
				+ "(?,?,?,?,?,date_add(curdate(),interval 30 day))";
		int resu = 0;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, stack.pop());
			pre.setString(2, book.getBookid());
			pre.setString(3, book.getBookname());
			pre.setString(4, User.getId());
			pre.setString(5, getDate());
			resu = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resu;
		
	}

}
