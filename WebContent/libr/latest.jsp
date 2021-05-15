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
width:960px;
background:#CCEEFF;
border-bottom:1px solid #CCDDFF;
border-right:1px solid #CCDDFF;
}
#content td{
height:35px;
border-top:1px solid #CCDDFF;
border-left:1px solid #CCDDFF;
text-align:center;
}
span.navbar{
color:#666666;
}
span.navbar a{
text-decoration:none;
}
</style>
</head>
<body>
<table align="center" id="content">
<% request.setCharacterEncoding("utf-8"); %>
<% 
ArrayList<Book> list= (ArrayList<Book>)request.getAttribute("bookList");
if(list!=null&&list.size()>0){
%>
<tr align="center"><th>编号</th><th>书名</th><th>作者</th><th>出版社</th><th>出版时间</th><th>总量</th><th>可借数量</th></tr>
<%	
	for(Book book:list){	
%>
<tr align="center">
	<td><%=book.getBookid()%></td>
	<td><%=book.getBookname() %></td>
	<td><%=book.getAuthor() %></td>
	<td><%=book.getPublisher() %></td>
	<td><%=book.getPublishtime() %></td>
	<td><%=book.getTotal()%></td>
	<td><%=book.getSurplus() %></td>
</tr>
<%}%>
<tr><td align="center" colspan="7"><span id=".navbar"><%=request.getAttribute("bar")%></span></span></td></tr>
<%
}else{
	out.print("<h2>没有数据！</h2>");
}
%>
</table>
<p><b><a href="home.jsp">返回</a></b></p>
</body>
</html>