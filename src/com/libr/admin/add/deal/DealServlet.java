package com.libr.admin.add.deal;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.book.Book;

/**
 * Servlet implementation class FormData
 */
@WebServlet(urlPatterns = { "/libr/admin/DealServlet" })//asyncSupported = true, 
public class DealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //http://localhost:8080/BookManage/libr/admin/DealServlet
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		System.out.println("DealServlet正在处理中。。。");
		
		ArrayList<Book>list = new ArrayList<>();
		String kinds = request.getParameterValues("kinds")[0];
		
		System.out.println("DealServlet正在处理中 kinds:"+kinds);
		StringBuilder builder = new StringBuilder();
		boolean b = false;
		for(int i=0;i<10;i++) {
			Book book = new Book();
			String bookname = request.getParameter("bookname"+i);
			book.setBookname(bookname);
			book.setAuthor(request.getParameter("author"+i));
			book.setPublisher(request.getParameter("publisher"+i));
			String datestr =request.getParameter("publishtime"+i);
			if(!isValidDate(datestr)) {
				b = true;
				builder.append(datestr+"时间格式不正确,已为默认为当前时间！\n");
			}
			book.setPublishtime(getValidDate(request.getParameter("publishtime"+i)));//publishtime
			book.setTotal(Integer.valueOf(request.getParameter("total"+i)));
			book.setSurplus(Integer.valueOf(request.getParameter("surplus"+i)));
			if(bookname!=null&&bookname.length()>0) {
			list.add(book);
			}
		}
		InsertData data = new InsertData();
		int rows = data.executeInsertData(list,kinds);
		
		System.out.println("HttpServlet正在处理中 kinds:"+kinds);
		
		if(rows>0) {
			if(b) {
				out.print("<b>注意:"+builder.toString()+"</b>");
			}
			out.print("<h2>提示：成功插入【"+rows+"】"+"数据！</h2>");
			
		}else {
			out.print("<h2>注意：此次添加信息失败！</h2>");
		}
		out.print("<b><a href='AddServlet'>继续添加</a><b>");
		
	}
	public String getValidDate(String datestr) {
		if(!isValidDate(datestr)) {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			datestr = sd.format(date);
		}
		return datestr;
			
	}
	public boolean isValidDate(String datestr) {
		
		if(datestr.trim().contains(" ")) {
			datestr = datestr.replace(" ","-");
			System.out.println("datestr:"+datestr);
		}
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");//括号内为日期格式，y代表年份，M代表年份中的月份（为避免与小时中的分钟数m冲突，此处用M），d代表月份中的天数
		try {
			sd.setLenient(false);//此处指定日期/时间解析是否不严格，在true是不严格，false时为严格
			sd.parse(datestr);//从给定字符串的开始解析文本，以生成一个日期
		}
		catch (Exception e) {
			return false;
		}
		return true;
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
