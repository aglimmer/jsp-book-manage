package com.libr.admin;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.book.Book;
import com.libr.server.ConnectService;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/libr/admin/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pagecode = 1;
		if(request.getParameter("pagecode")!=null) {
			pagecode = Integer.valueOf(request.getParameter("pagecode"));
		}
		
		Edit edit = new Edit();
		int sum = 0;
		if(request.getParameter("sum")!=null) {					
			sum = Integer.valueOf(request.getParameter("sum"));	//request.getParameter("sum")
		}else {													
		//获取搜索数量
		sum = edit.getBookCount(request.getParameter("ways"),
				request.getParameter("message"),Integer.valueOf(request.getParameter("range")));
		}
		
		//获取所有符合的记录
		ArrayList<Book> bookList = edit.gainBookList(request.getParameter("ways"),
				request.getParameter("message"),Integer.valueOf(request.getParameter("range")),pagecode);
		request.setAttribute("bookList", bookList);
		
		int sumPages =(int)Math.ceil(sum*1.0/PAGE_SIZE); 
		StringBuilder bu = new StringBuilder();
		for(int i = 1; i <= sumPages; i++) {
			if(i==pagecode) {
				bu.append("『"+i+"』");
			}else {
				bu.append("<a href='Edit?pagecode="+i
						+"&sum="+sum
						+"&range="+request.getParameter("range")
						+"&ways="+request.getParameter("ways")
						+"&message="+URLEncoder.encode(request.getParameter("message"),"utf-8")+"'>"+i+"</a>  ");
			}
		}
		//request.getParameter("sum")
		//request.getParameter("range")
		//request.getParameter("message")
		//request.getParameter("pagecode")
		request.setAttribute("bar", bu.toString());
		request.setAttribute("sum",sum);
		request.setAttribute("pagecode",pagecode);
		request.setAttribute("bar", bu.toString());
		request.getRequestDispatcher("editView.jsp").forward(request, response);
	}
	
	
	public static final int PAGE_SIZE=10;
	public ArrayList<Book> gainBookList(String columnName,String message,int range,int pagecode){
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "";
		ResultSet set = null;
		 ArrayList<Book> list = new ArrayList<>();
		 try {
		if(range==1) {
		sql = "select * from literary_novel where "+columnName+" like ?"
				+" union select * from romantic_faction where "+columnName+" like ?"
				+" union select * from history_biography where "+columnName+" like ?"
				+" union select * from computer_science where "+columnName+" like ? limit ?,?";
			pre = con.prepareStatement(sql);
			String str = "%"+message+"%";
			pre.setString(1,str);
			pre.setString(2,str);
			pre.setString(3,str);
			pre.setString(4,str);
		}
		if(range==2) {
			sql = "select * from literary_novel where "+columnName+" = ?"
					+" union select * from romantic_faction where "+columnName+" = ?"
					+" union select * from history_biography where "+columnName+" = ?"
					+" union select * from computer_science where "+columnName+" = ? limit ?,?";
			pre = con.prepareStatement(sql);
			pre.setString(1,message);
			pre.setString(2,message);
			pre.setString(3,message);
			pre.setString(4,message);
		}
		pre.setInt(5,(pagecode-1)*PAGE_SIZE);
		pre.setInt(6,PAGE_SIZE);
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
	public int getBookCount(String columnName,String message,int range) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "";
		ResultSet set = null;
		int allCount = 0;
		 try {
		if(range==1) {
		sql = " select sum(allcount.subcount) as result from"
				+" (select count(bookid) as subcount from literary_novel where "+columnName+" like ?"
				+" union select count(bookid) as subcount from romantic_faction where "+columnName+" like ?"
				+" union select count(bookid) as subcount from history_biography where "+columnName+" like ?"
				+" union select count(bookid) as subcount from computer_science where "+columnName+" like ?"
				+" ) as allcount";
			pre = con.prepareStatement(sql);
			String str = "%"+message+"%";
			pre.setString(1,str);
			pre.setString(2,str);
			pre.setString(3,str);
			pre.setString(4,str);
		}
		if(range==2) {
			sql = "select sum(allcount.subcount) as result from"
					+" (select count(bookid) as subcount from literary_novel where "+columnName+" = ?"
					+" union select count(bookid) as subcount from romantic_faction where "+columnName+" = ?"
					+" union select count(bookid) as subcount from history_biography where "+columnName+" = ?"
					+" union select count(bookid) as subcount from computer_science where "+columnName+" = ?"
					+" ) as allcount";
			pre = con.prepareStatement(sql);
			pre.setString(1,message);
			pre.setString(2,message);
			pre.setString(3,message);
			pre.setString(4,message);
		}
			set = pre.executeQuery();
			if(set.next()) {
				allCount = set.getInt("result");
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
		return allCount;
		
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
