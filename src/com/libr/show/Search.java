package com.libr.show;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.book.Book;
import com.libr.show.search.SearchBook;

/**
 * Servlet implementation class Borrow
 */
@WebServlet("/libr/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
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
		String message = request.getParameter("message");
		String columnName = request.getParameter("ways");
		String kinds = request.getParameter("kinds");
		
		System.out.println("message:"+message);
		System.out.println("columnName:"+columnName);
		System.out.println("kinds:"+kinds);
		
		int currpage = 1;	//首次分页，currPage=0
		SearchBook sear = new SearchBook();
		int count = 0;
		if(request.getParameter("count")!=null&&request.getParameter("count").length()>0) {
			System.out.println("count异常处："+request.getParameter("count"));
			count = Integer.parseInt(request.getParameter("count"));//第一次进入 count=null;
		}else {
		count = sear.getSearchCount(kinds, columnName, message);
		}
		if(request.getParameter("currpage")!=null) {
			System.out.println("currPage字符串："+request.getParameter("currpage"));
			currpage = Integer.valueOf(request.getParameter("currpage"));//第一次以后，page在request对象里
			System.out.println("currPage!=null ："+currpage);
		}
		System.out.println("currpage:"+currpage);
		ArrayList<Book> bookList = sear.getSearchBook(kinds, columnName, message,currpage);
//		根据类别、搜索方式、搜索信息、当前页码，分页查询
		request.setAttribute("bookList",bookList);
		int pages = 0;
		pages =(int) Math.ceil(count*1.0/SearchBook.PAGE_SIZE);
		System.out.println("pages:"+pages);
		StringBuilder bar = new StringBuilder();
		for(int i = 1;i<=pages;i++) {
			if(i==currpage) {
				bar.append("『"+i+"』");
			}else {//在该页面实现循环
				bar.append("<a href='Search?currpage="+i
			+"&message="+URLEncoder.encode(message,"utf-8")
			+"&ways="+columnName
			+"&kinds="+kinds
			+"&count="+count
			+"'>"+i+"</a>");
			}
			bar.append(" ");
		}
		request.setAttribute("currpage",currpage);
		request.setAttribute("ways", request.getParameter("ways"));
		request.setAttribute("kinds", request.getParameter("kinds"));
		request.setAttribute("message", message);
		request.setAttribute("count", count);
		request.setAttribute("bar", bar.toString());
		request.setAttribute("bookList",bookList);
		request.getRequestDispatcher("searchView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
