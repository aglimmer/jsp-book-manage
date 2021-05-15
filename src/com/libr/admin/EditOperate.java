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
 * Servlet implementation class EditOperate
 */
@WebServlet("/libr/admin/EditOperate")
public class EditOperate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOperate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Book book = new Book();
		book.setBookid(request.getParameter("bookid"));
		book.setBookname(request.getParameter("bookname"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setPublishtime(request.getParameter("publishtime"));
		if(request.getParameter("total")!=null) {
		book.setTotal(Integer.valueOf(request.getParameter("total")));
		}
		if(request.getParameter("surplus")!=null) {
		book.setSurplus(Integer.valueOf(request.getParameter("surplus")));
		}
		EditOperate edit = new EditOperate();
		PrintWriter out = response.getWriter();
		if(edit.updateBook(book)==1) {
			out.println("<h2>提示：编辑成功！</h2>");
		}else {
			out.println("<h2>提示：编辑失败！</h2>");
		}
		String header ="ways="+request.getParameter("ways")
		+"&sum="+request.getParameter("sum")
		+"&range="+request.getParameter("range")
		+"&message="+URLEncoder.encode(request.getParameter("message"),"utf-8")
		+"&pagecode="+request.getParameter("pagecode");	
		out.println("<h2><a href='Edit?"+header+"'>返回</a></h2>");
	}
	public int updateBook(Book book) {
		HashMap<String,String>map = new HashMap<>();
		map.put("comp","computer_science");
		map.put("lite","literary_novel");
		map.put("rima","romantic_faction");
		map.put("hist","history_biography");
		String kinds = map.get(book.getBookid().substring(0,4));
		ConnectService net = new ConnectService();
		Connection con = net.getConnect();
		PreparedStatement pre = null;
		String sql = "update "+kinds+" set bookname= ? , author = ? ,"
				+" publisher = ? , publishtime = ? , total = ? , surplus = ? where bookid = ?";
		int row = 0;
		try {
			pre = con.prepareStatement(sql);
			pre.setString(1,book.getBookname());
			pre.setString(2,book.getAuthor());
			pre.setString(3,book.getPublisher());
			pre.setString(4,book.getPublishtime());
			pre.setInt(5,book.getTotal());
			pre.setInt(6,book.getSurplus());
			pre.setString(7,book.getBookid());
			row = pre.executeUpdate();
			System.out.println("updateBook："+row);
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
