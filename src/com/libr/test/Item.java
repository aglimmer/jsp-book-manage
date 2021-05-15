package com.libr.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Item
 */
@WebServlet("/test/Item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Item() {
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
		System.out.println("Item->name attribute"+request.getAttribute("name"));
		System.out.println("Item->realname para"+request.getParameter("realname"));
		System.out.println("Item->realname attribu"+request.getAttribute("realname"));
		
		System.out.println("Item->idcard parameter"+request.getParameter("idcard"));
		
		System.out.println("Item->book1 attribute:"+request.getAttribute("book1"));
		System.out.println("Item->book1 getParameter:"+request.getParameter("book1"));
		System.out.println("Item->book2 attribute:"+request.getAttribute("book2"));
		System.out.println("Item->book2 getParameter:"+request.getParameter("book2"));
		System.out.println("Item->info"+request.getParameter("info"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
