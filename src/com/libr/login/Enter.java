package com.libr.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.login.account.Account;
import com.libr.user.User;

/**
 * Servlet implementation class UserServet
 */
@WebServlet(urlPatterns="/libr/Enter")
public class Enter extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Enter() {
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
		String id = request.getParameter("userid");
		String password = request.getParameter("userpassword");
		int grade = Integer.valueOf(request.getParameter("grade"));
		Account acc = new Account();
		boolean b = acc.enquiry(id,password,grade);
	
		if(b) {
			out.println("<h2>登录成功！</h2>");
			User.setId(id);
			User.setPassword(password);
			User.setGrade(grade);
//			request.getRequestDispatcher("home.jsp").forward(request, response);
			response.setHeader("refresh", "2;url=home.jsp");
			
		}else {
		out.println("<h2>登录失败，账号或密码错误！</h2>");
		response.setHeader("refresh", "2;url=login.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
