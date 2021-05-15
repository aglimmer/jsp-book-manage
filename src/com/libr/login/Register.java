package com.libr.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.login.account.Account;
import com.libr.user.NewUser;
import com.libr.user.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/libr/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		int grade = Integer.valueOf(request.getParameter("grade"));
		NewUser reg = new NewUser();
		reg.setUserid(request.getParameter("userid"));
		reg.setUserpassword(request.getParameter("userpassword"));
		reg.setEmail(request.getParameter("email"));
		reg.setPhone(request.getParameter("phone"));
		reg.setQuestion1(request.getParameter("question1"));
		reg.setAnswer1(request.getParameter("answer1"));
		reg.setQuestion2(request.getParameter("question2"));
		reg.setAnswer2(request.getParameter("answer2"));
		reg.setGrade(grade);
		if(grade==1) {
			String header = "ApplyAdmin?"
			+"userid="+reg.getUserid()
			+"&userpassword="+reg.getUserpassword()
			+"&email="+reg.getEmail()
			+"&phone="+reg.getPhone()
			+"&question1="+reg.getQuestion1()
			+"&answer1="+reg.getAnswer1()
			+"&question2="+reg.getAnswer2();
			out.print("<h3>提示：请补充你的资料，以便通过审核</h3>");
			out.print("<form action='"+header+"'" +" method='post'>"
					+"<table align='center' style='border:1px solid orange;'>"
					+"<tr align='center'><td colspan='2'>管理员身份认证</td></tr>"
					+"<tr><td>真实姓名：</td><td><input type='text' name='realname'</td></tr>"
					+"<tr><td>身份证号：</td><td><input type='text' name='idcard'</td></tr>"
					+"<tr><td colspan='2' align='center'>关于申请的说明</td></tr>"
					+"<tr><td colspan='2' align='center'><textarea name='applyinfo' wrap='hard'>输入申请理由</textarea></td></tr>"
					+"<tr><td colspan='2' align='center><input type='submit' value='提交认证'</td></tr>"
					+"</table></form>"
					);
		}else {
		Account acc = new Account();
		int row = acc.register(reg);
		if(row!=1) {
			out.print("<h2>提示：注册失败，账户已存在</h2>");
			out.print("<p><b><a href=\"login.jsp\">返回</a></b></h2>");
		}else {
		User.setId(reg.getUserid());
		User.setPassword(reg.getUserpassword());
		User.setGrade(reg.getGrade());
		out.print("<h2>注册成功，正在跳转...</h2>");
		request.getRequestDispatcher("home.jsp").forward(request, response);
		}
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
