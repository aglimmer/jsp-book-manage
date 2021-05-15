<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>读者还书</title>
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
</head>
<body>
<%@page import="com.libr.book.Book" %>
<%@page import="java.util.*" %>
<%@page import="com.libr.user.User" %>
<%	ArrayList<Book>list = (ArrayList<Book>)request.getAttribute("bookList"); %>
<%
	if(list!=null&&list.size()>0){	
%>
<%@page import="java.net.URLEncoder"%>
<table rules="all" align="center" id="content">
<caption>读者记录</caption>
<tr><th>索书号</th><th>书名<th>借阅日期</th><th>应还日期</th><th>借阅状态</th><th>选择</th></tr>
<% 		
		for(Book book:list){
%>
<tr><td><%=book.getBookid()%></td>
	<td><%=book.getBookname() %></td>
	<td><%=book.getBorrowdate() %></td>
	<td><%=book.getBackdate() %></td>
	<td><%=book.getOverdue()%>
	<%
	if(book.getIsPermitted()){
	%>
	<td><a href="BackBook?userid=<%=User.getId()%>&bookid=<%=book.getBookid()%>">归还</a></td>
	<%}else{ %>
	<td>&nbsp;</td>
	<%} 
		}
%>
</tr>
<%
	}else{
%>
<h2>提示：借阅无记录</h2>
<%} %>
<h2><a href="home.jsp">返回</a></h2>
</body>
</html>