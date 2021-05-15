package com.libr.show;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.book.Book;
import com.libr.show.borrow.BorrowBook;
import com.libr.user.User;

/**
 * Servlet implementation class Back
 */
@WebServlet("/libr/Back")
public class Back extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Back() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BorrowBook borr = new BorrowBook();
		String userid = User.getId();
		ArrayList<Book> recordList = borr.borrowedRecord(userid);
		PrintWriter out = response.getWriter();
		boolean isPermitted = true;
		if(recordList!=null&&recordList.size()>0) {
				for(int i = 0;i<recordList.size();i++)
				{	int days = Borrow.getDiffDays(recordList.get(i).getBackdate());
					if(days>0) {
						recordList.get(i).setOverdue("逾期"+days+"天,请到服务台办理");
						isPermitted = false;
						recordList.get(i).setIsPermitted(isPermitted);
					}else if(days==0){
						recordList.get(i).setOverdue("即将到截止期限");
					}else if(days<0){
						recordList.get(i).setOverdue("正常借阅中");
					}
				}
			request.setAttribute("bookList",recordList);
			request.getRequestDispatcher("back.jsp").forward(request,response);
		}
		else {
			out.println("<h2>提示：未查询到相关记录！");
		}
		out.println("<h2><a href='home.jsp'>返回首页</a></h2>");
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
