package com.libr.show;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libr.admin.add.deal.BaseSerial;
import com.libr.admin.add.deal.SerialFormat;
import com.libr.book.Book;
import com.libr.show.borrow.BorrowBook;
import com.libr.show.popular.PopularBook;
import com.libr.user.User;

/**
 * Servlet implementation class Borrow
 */
@WebServlet("/libr/Borrow")
public class Borrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Borrow() {
        super();
        // TODO Auto-generated constructor stub
    }
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Book book = new Book();
		book.setKinds(request.getParameter("kinds"));
		book.setBookid(request.getParameter("bookid"));
		book.setBookname(request.getParameter("bookname"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setPublishtime(request.getParameter("publishtime"));	
		
		BorrowBook borrow = new BorrowBook();
		ArrayList<Book> recordList = borrow.borrowedRecord(User.getId());//获取所有借阅记录
		boolean isPermitted = true;
		Borrow borr = new Borrow();
		HashSet<String> set = new HashSet<>();
		if(recordList!=null&&recordList.size()>0) { //借阅记录
			for(int i = 0;i<recordList.size();i++)
			{	int days = getDiffDays(recordList.get(i).getBackdate());
				if(days>0) {
					recordList.get(i).setOverdue("逾期"+days+"天");
				}else if(days==0){
					recordList.get(i).setOverdue("到期");
					isPermitted = false;
				}else if(days<0){
					recordList.get(i).setOverdue("正常");
				}
				if(!set.add(recordList.get(i).getBookid())) {//存在相同的bookid，则为重复借阅
					isPermitted = false;
				}
				
			}
		}
		if(!set.add(request.getParameter("bookid"))) {//存在相同的bookid，则为重复借阅
			isPermitted = false;
//			recordList.get(i).overdueAppend("/该书已借");
		}
		if(!isPermitted) {	//有逾期记录则输出
				out.print("<table align='center' border='1' rules='all'>"
						+"<caption>借阅状态记录表<caption>"
						+"<tr><th>书名</th><th>借阅日期</th><th>归还日期</th><th>借阅状态</th>");
				for(Book overbook:recordList) {
					out.print("<tr><td>"+overbook.getBookname()+"</td>"
							+"<td>"+overbook.getBorrowdate()+"</td>"
							+"<td>"+overbook.getBackdate()+"</td>"
							+"<td>"+overbook.getOverdue()+"</td></tr>"
							);
				}
				out.print("<tr align='center'><td colspan='4'>读者："+User.getId()+"</td></tr>");
			
		}
		//获取图书可借数量
		int surplus = borrow.getSurplus(request.getParameter("kinds"),request.getParameter("bookid"));
		if(surplus==0) {
			isPermitted = false;
			out.print("<p>提示：•图书已被他人借出<br></p>");
		}
	if(isPermitted) {//若以上符合条件
			String kinds = "record";
			BaseSerial base = new BaseSerial();	//系列号对象
			String originalSerial = base.getLatestSerial(kinds);	//获取系列号
			SerialFormat sf = new SerialFormat();
			Stack<String> stack = sf.makeSerials(kinds, originalSerial, 1);//生成一个不重复的系列号
			String latestSerial = stack.peek();
			int row = borrow.insertRecord(book,stack);	//插入借书记录表
		if(row==1) {	//插入成功
			base.updateSerial(latestSerial, kinds, 1); //更新系列号
			System.out.println("insertRecord:"+row);
			PopularBook popu = new PopularBook();
			int resu = popu.updatePopular(book);//更新热门书库
			borrow.updateSurplus(book);	//更新借阅的书库
			System.out.println("popular_book:"+resu);
			out.print("<h2>提示：该图书已成功借阅！</h2>");
			
		}else {	//借阅失败
			isPermitted=false;
			out.print("系统访问失败");
		}
		}
		String header = "Search?"
				+"&message="+URLEncoder.encode(request.getParameter("message"),"utf-8")
				+"&count="+request.getParameter("count")
				+"&ways="+request.getParameter("ways")
				+"&kinds="+request.getParameter("kinds")
				+"&currpage="+request.getParameter("currpage");
		System.out.println(header);
		out.println("<p><b><a href="+header+">返回上一级</a></b></p>");
		}
	
	public static int getDiffDays(String backdate) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(date);
		Date d1=null;
		Date d2=null;
		int x = 0;
		try {
			d1 = dateFormat.parse(dateStr); //现在时间
			d2 = dateFormat.parse(backdate);//到期时间
//			x = d1.compareTo(d2);
			x = (int) ((d1.getTime() - d2.getTime()) / (1000*3600*24));//大于0，则不能借阅
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
		
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
