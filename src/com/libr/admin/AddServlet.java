package com.libr.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddBook
 */
@WebServlet(urlPatterns = { "/libr/admin/AddServlet" })//asyncSupported = true, 
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return sdf.format(date);
//		return "20190822";
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String[] kinds= {"computer_science","history_biography","literary_novel","romantic_faction"};
		out.print("<html>");
		out.print("<head><title>添加图书</title>"
				+ "<style type='text/css'>"
				+"#addtable{background:#FFF0F5;border-bottom:1px solid #FFE4B5;border-right:1px solid #FFE4B5;}"
				+"#addtable td{height:35px;text-align:center; border-left:1px solid #FFE4B5;"
				+ "border-top:1px solid #FFE4B5;}"
				+"#addtable input{height:30px;}"
				+"#navibar{display:bolck;width:100%;height:35px;}"
				+"</style>"
				+ "</head>");
		out.print("<body>");
		out.print("<div id=\"navbar\">"
				+"<span style=\"display:block;float:left;\">"
				+"<b><a href=\"addBook.jsp\">返回上一级</a></b>"
				+"</span>"
				+"<span style=\"display:bolck;float:right;\">"
				+"<b><a href=\"../home.jsp\">返回首页</a></b>"
				+"</span>" 
				+"</div>");
		out.print("<form action='DealServlet' method='post'>");
		out.print("<table align='center' id='addtable'>");
		out.print("<tr><td colspan='8' align='center'>添加图书</td></tr>");
		out.print("<tr><th>序号</th><th>书名</th><th>作者</th><th>出版社</th><th>出版时间</th><th>数量</th><th>可借数量</th></tr>");
		for(int i = 0;i<10;i++) {
		out.print("<tr>" + 
				"<td>"+i+"</td>"+
				"<td><input type='text' name='bookname"+i+"'></td>" + 
				"<td><input type='text' name='author"+i+"'></td>" + 
				"<td><input type='text' name='publisher"+i+"'></td>" + 
				"<td><input type='date' name='publishtime"+i+"' value='"+getDate()+"'></td>" + 
				"<td><input type='number' min='0' value='1' name='total"+i+"'></td>" + 
				"<td><input type='number' min='0' value='1' name='surplus"+i+"'></td" + 
				"</tr>");
		}
		out.print("<tr><td colspan='7' align='center'><label>类别:</label><select name='kinds'>"+
				"<option value='"+kinds[0]+"' selected='selected'>计算机技术</option>"+
				"<option value='"+kinds[1]+"'>历史传记</option>"+
				"<option value='"+kinds[2]+"'>文学小说</option>"+
				"<option value='"+kinds[3]+"'>言情小说</option>"+
				"</select>"+
				"</td></tr>");
		System.out.println("AddServlet正在处理中...");
		out.print("<tr><td colspan='8' align='center'><input type='submit' value='确定添加'></td></tr></table>");
		out.print("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
