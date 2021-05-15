<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.libr.book.Book" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>大家在看</title>
<style type="text/css">
#content{
border-top:1px solid #BBFFEE;
border-left:1px solid #BBFFEE;
background:#AFEEEE;
}
#content td{
border-right:1px solid #BBFFEE;
border-bottom:1px solid #BBFFEE;
text-align:center;
}
</style>
</head>
<body>
<table align="center" id="content" rules="all">
<% request.setCharacterEncoding("utf-8"); %>
<% 
ArrayList<Book> list= (ArrayList<Book>)request.getAttribute("bookList");
if(list!=null&&list.size()>0){
%>
<tr align="center"><th>编号</th><th>书名</th><th>作者</th><th>出版社</th><th>出版时间</th><th>借阅累计</th></tr>
<%	
	for(Book book:list){	
%>
<tr align="center"><td><%=book.getBookid()%></td><td><%=book.getBookname() %></td><td><%=book.getAuthor() %></td>
	<td><%=book.getPublisher() %></td><td><%=book.getPublishtime() %></td><td><%=book.getBorrowcount()%></td></tr>
<%}
}else{
	out.print("<h2>没有数据！</h2>");
}
%>
</table>
<p><b><a href="home.jsp">返回</a></b></p>
</body>
</html>