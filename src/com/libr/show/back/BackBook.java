package com.libr.show.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.server.ConnectService;

/**
 * Servlet implementation class BackBook
 */
@WebServlet("/libr/BackBook")
public class BackBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BackBook back = new BackBook();
		String bookid = request.getParameter("bookid");
		String userid = request.getParameter("userid");
		int delrow =  back.delRecord(bookid,userid);
		PrintWriter out = response.getWriter();
		if(delrow==1) {
			back.updateSurplus(bookid);
			out.print("<h2>提示：还书成功！</h2>");
//			response.setHeader("refresh","3;URL=Back?userid="+userid);
		}else {
			out.print("<h2>提示：还书失败！</h2>");
			
		}
		out.println("<h2><a href='Back?userid="+userid+"'>返回</a></h2>");
		
		
	}
	public int delRecord(String bookid,String userid) {
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "delete from record where bookid=? and userid=?";
		int row = 0;
//		ResultSet set=null;
		try {
		pre = con.prepareStatement(sql);
		pre.setString(1,bookid);
		pre.setString(2,userid);
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
	public int updateSurplus(String bookid) {
		HashMap<String,String>map = new HashMap<>();
		map.put("comp","computer_science");
		map.put("lite","literary_novel");
		map.put("rima","romantic_faction");
		map.put("hist","history_biography");
		String kinds = map.get(bookid.substring(0,4));
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "update "+kinds+" set surplus=surplus+1 where bookid = ?";
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
