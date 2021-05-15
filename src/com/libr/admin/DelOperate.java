package com.libr.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.book.Book;
import com.libr.server.ConnectService;

/**
 * Servlet implementation class DelBook
 */
@WebServlet("/libr/admin/DelOperate")
public class DelOperate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelOperate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DelOperate deal = new DelOperate();
		PrintWriter out = response.getWriter();
		if(request.getParameter("bookid")!=null) {
			if((deal.delBookRecord(request.getParameter("bookid")))!=0) {
				out.print("<h2>提示：删除成功！</h2>");
				delPopualrRecord(request.getParameter("bookid"));
			}else {
				out.print("<h2>提示：删除失败！</h2>");
			}
			String header ="ways="+request.getParameter("ways")
			+"&sum="+request.getParameter("sum")
			+"&range="+request.getParameter("range")
			+"&message="+URLEncoder.encode(request.getParameter("message"),"utf-8")
			+"&pagecode="+request.getParameter("pagecode");	
			out.println("<h2><a href='Edit?"+header+"'>返回</a></h2>");
		}
	
	}
	
	public int delBookRecord(String bookid) {
		HashMap<String,String>map = new HashMap<>();
		map.put("comp","computer_science");
		map.put("lite","literary_novel");
		map.put("rima","romantic_faction");
		map.put("hist","history_biography");
		String kinds = map.get(bookid.substring(0,4));
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "delete from "+kinds+" where bookid = ?";
		int row = 0;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1,bookid);
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
	public int delPopualrRecord(String bookid) {
//		HashMap<String,String>map = new HashMap<>();
//		map.put("comp","computer_science");
//		map.put("lite","literary_novel");
//		map.put("rima","romantic_faction");
//		map.put("hist","history_biography");
//		String kinds = map.get(bookid.substring(0,4));
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "delete from popular_book where bookid = ?";
		int row = 0;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1,bookid);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
