package com.libr.show.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.libr.book.Book;
import com.libr.server.ConnectService;

public class SearchBook {
//	public static void main(String[] args) {
//		SearchBook book = new SearchBook();
//		String kinds = "literary_novel";
//		String columnName = "bookname";
//		String message = "ʱ";
//		int x = book.getSearchCount(kinds, columnName, message);
//		System.out.println("x="+x);
//		book.display(book.getSearchBook(kinds, columnName, message, 1));
//		
//	}
	public void display(ArrayList<Book> book) {
		for(Book list:book) {
			System.out.println(list.getBookname());
		}
	}

	public SearchBook() {
		// TODO Auto-generated constructor stub
	}
	public int getSearchCount(String kinds,String columnName,String message) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		String sql = "select count(bookid) as result from "+kinds+" where "+columnName+" like ?";
		PreparedStatement pre = null;
		ResultSet set = null;
		int count = 0;
		try {
			pre = con.prepareStatement(sql);
			String str = "%"+message+"%";
			pre.setString(1, str);
			set = pre.executeQuery();
			if(set.next()) {
				count = set.getInt("result");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		if(pre!=null) {
			pre.close();
		}
		if(set!=null) {
			
				set.close();
			
		}
		if(con!=null) {
			
				con.close();
		}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return count;
		
	}
	public static final int PAGE_SIZE = 10;
	public ArrayList<Book> getSearchBook(String kinds,String columnName,String message,int page) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		
		String sql = "select * from "+kinds+" where "+columnName+" like ?"+" limit ?,?";//����limit 0,10;
		PreparedStatement pre = null;
		ResultSet set = null;
		ArrayList<Book> list = new ArrayList<>();
		try {
			pre = con.prepareStatement(sql);
			String str = "%"+message+"%";
			pre.setString(1, str);
			pre.setInt(2,(page-1)*PAGE_SIZE);
			pre.setInt(3, PAGE_SIZE);
			set = pre.executeQuery();
			while(set.next()) {
						Book book = new Book();
						book.setBookid(set.getString("bookid"));
						book.setBookname(set.getString("bookname"));
						book.setAuthor(set.getString("author"));
						book.setPublisher(set.getString("publisher"));
						book.setPublishtime(set.getString("publishtime"));
						book.setTotal(set.getInt("total"));
						book.setSurplus(set.getInt("surplus"));
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
