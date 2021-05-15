package com.libr.admin;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.book.Book;
import com.libr.server.ConnectService;

/**
 * Servlet implementation class ScanBook
 */
@WebServlet("/libr/admin/ScanBook")
public class ScanBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanBook() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pagecode = 1;
		if(request.getParameter("pagecode")!=null) {
			pagecode = Integer.valueOf(request.getParameter("pagecode"));
		}
		
		ScanBook scan = new ScanBook();
		int sum = 0;
		if(request.getParameter("sum")!=null) {					
			sum = Integer.valueOf(request.getParameter("sum"));	//request.getParameter("sum")
		}else {													
		//获取数量
		sum = scan.getAllCount(request.getParameter("kinds"));
		}
		System.out.println("sum:"+sum);
		//获取所有符合的记录
		System.out.println("pagecode:"+pagecode);
		System.out.println("kinds:"+request.getParameter("kinds"));
		
		ArrayList<Book> bookList = scan.gainAllInfo(request.getParameter("kinds"),pagecode);
		request.setAttribute("bookList", bookList);
		
		int sumPages =(int)Math.ceil(sum*1.0/PAGE_SIZE); 
		System.out.println("sumPages:"+sumPages);
		
		StringBuilder bu = new StringBuilder();
		for(int i = 1; i <= sumPages; i++) {
			if(i==pagecode) {
				bu.append("『"+i+"』");
			}else {
				bu.append("<a href='ScanBook?pagecode="+i
						+"&sum="+sum
						+"&kinds="+request.getParameter("kinds")+"'>"+i+"</a>  ");
			}
		}
		request.setAttribute("bar", bu.toString());
		request.getRequestDispatcher("scan.jsp").forward(request, response);
		
		
	}
	public static final int PAGE_SIZE = 10;
	public ArrayList<Book> gainAllInfo(String kinds,int pagecode){
		
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "";
		ResultSet set = null;
		 ArrayList<Book> list = new ArrayList<>();
		 try {
		if(kinds.equals("all")) {
		sql = 	"select * from literary_novel"
				+" union select * from romantic_faction"
				+" union select * from history_biography"
				+" union select * from computer_science limit ?,?";
			
		}else {
			sql = "select * from "+kinds+" limit ?,?";
			
		}
		pre = con.prepareStatement(sql);
		pre.setInt(1,(pagecode-1)*PAGE_SIZE);
		pre.setInt(2,PAGE_SIZE);
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
	public int getAllCount(String kinds) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		String sql = "";
		ResultSet set = null;
		Statement state = null;
		int allCount = 0;
		 try {
		if(kinds.equals("all")) {
		sql = "select sum(allcount.subcount) as result from"
				+" (select count(bookid) as subcount from literary_novel"
				+" union select count(bookid) as subcount from romantic_faction"
				+" union select count(bookid) as subcount from history_biography"
				+" union select count(bookid) as subcount from computer_science"
				+" ) as allcount";
		}
		else {
			sql = "select count(bookid) as result from "+kinds;
		}
			state = con.createStatement();
			set = state.executeQuery(sql);
			if(set.next()) {
				allCount = set.getInt("result");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
