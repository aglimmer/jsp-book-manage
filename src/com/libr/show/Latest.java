package com.libr.show;

import java.io.IOException;
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
 * Servlet implementation class Latest
 */
@WebServlet("/libr/Latest")
public class Latest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Latest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currpage = 1;
		if(request.getParameter("currpage")!=null) {
			currpage = Integer.valueOf(request.getParameter("currpage"));
		}
		Latest late = new Latest();
		ArrayList<Book> bookList = late.getLatestBook(currpage);
		int sum = 0;
		if(request.getParameter("sum")!=null) {
			sum = Integer.valueOf(request.getParameter("sum"));
		}else {
		sum = late.getAllCount();
		}
		System.out.println("sum:"+sum);
		StringBuilder bar = new StringBuilder();
		int sumpage = (int)Math.ceil(sum*1.0/PAGE_SIZE);
		System.out.println("sumpage:"+sumpage);
		for(int i = 1; i<=sumpage;i++) {
			if(i==currpage) {
				bar.append("『"+i+"』");
			}else {
				bar.append("<a href='Latest?currpage="+i+"&sum="+sum+"'>"+i+"</a>  ");
			}
		}
		request.setAttribute("bar",bar.toString());
		request.setAttribute("bookList",bookList);
		request.getRequestDispatcher("latest.jsp").forward(request,response);
		
	}
	public static final int PAGE_SIZE = 10;
	private int getAllCount() {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		String sql = "select sum(allcount.subcount) as result from(select count(*) as subcount from literary_novel union all select count(*) as subcount from romantic_faction union all select count(*) as subcount from computer_science union " + 
				"all select count(*) as subcount from history_biography) as allcount";
		Statement state=null;
		ResultSet set = null;
		int sum = 0;
		try {
			state=con.createStatement();
			set = state.executeQuery(sql);
			if(set.next()) {
				sum = set.getInt("result");
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
		return sum;
		
	}
//	public static void main(String[] args) {
//		Latest late = new Latest();
//		ArrayList<Book> bookList = late.getLatestBook(2);
//		if(bookList!=null) {
//		System.out.print("size:"+bookList.size());
//		}
////		System.out.print("all:"+ late.getAllCount());
//		
//	}
	public ArrayList<Book> getLatestBook(int pagecode){
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "select * from literary_novel "
				+"union all select * from romantic_faction "
				+"union all select * from computer_science "
				+"union all select * from history_biography "
				+"order by publishtime desc limit ?,?";
		ResultSet set=null;
		ArrayList<Book> list = new ArrayList<>();
		try {
			pre = con.prepareStatement(sql);
			System.out.println("(pagecode-1)*PAGE_SIZE:"+(pagecode-1)*PAGE_SIZE);
			System.out.println("pAGE_SIZE:"+PAGE_SIZE);
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
		if(set!=null)
		{
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
