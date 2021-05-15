package com.libr.show.popular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.libr.book.Book;
import com.libr.server.ConnectService;

public class PopularBook {

	public PopularBook() {
		// TODO Auto-generated constructor stub
	}
	public int updatePopular(Book book) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		String sql = "insert into popular_book(bookid,bookname,author,publisher,publishtime,borrowcount) "
				+"values(?,?,?,?,?,?) on duplicate key update borrowcount=borrowcount+1";
		PreparedStatement pre = null;
		int resu = 0;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1, book.getBookid());
			pre.setString(2, book.getBookname());
			pre.setString(3, book.getAuthor());
			pre.setString(4, book.getPublisher());
			pre.setString(5, book.getPublishtime());
			pre.setInt(6, 1);
			resu = pre.executeUpdate();
			
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
		return resu;
		
	}
	public ArrayList<Book> getPopularBook(){
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();//select * from book order by putaway desc limit 10;
		String sql = "select * from popular_book order by borrowcount desc limit 0,10";
		Statement state = null;
		ResultSet set = null;
		ArrayList<Book> list = new ArrayList<>();
		try {
			state = con.createStatement();
			set = state.executeQuery(sql);
			while(set.next()) {
				Book book = new Book();
				book.setBookid(set.getString("bookid"));
				book.setBookname(set.getString("bookname"));
				book.setAuthor(set.getString("author"));
				book.setPublisher(set.getString("publisher"));
				book.setPublishtime(set.getString("publishtime"));
				book.setBorrowcount(set.getInt("borrowcount"));
				list.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(state!=null) {
			try {
				state.close();
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
		return list;
	}
	

}
