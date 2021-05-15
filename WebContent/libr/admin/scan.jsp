<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="com.libr.book.Book" %>
<%@ page import="java.util.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>检索信息</title>
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

<%	ArrayList<Book>list = (ArrayList<Book>)request.getAttribute("bookList"); %>
<%
	if(list!=null&&list.size()>0){	
%>
<%@page import="java.net.URLEncoder"%>
<table rules="all" align="center" id="content">
<caption>图书列表</caption>
<tr>
	<th>索书号</th>
	<th>书名</th>
	<th>作者</th>
	<th>出版社</th>
	<th>出版日期</th>
	<th>库存</th>
	<th>可借数量</th>
</tr>
<% 	
	for(int i = 0; i<list.size();i++){
		Book book = list.get(i);
%>
<tr>
	<td><%=book.getBookid()%></td>
	<td><%=book.getBookname() %></td>
	<td><%=book.getAuthor() %></td>
	<td><%=book.getPublisher() %></td>
	<td><%=book.getPublishtime() %></td>
	<td><%=book.getTotal() %></td>
	<td><%=book.getSurplus() %></td>
</tr>
<%
}

%>
<tr><td colspan="7" align="center"><%=request.getAttribute("bar")%></td></tr>
</table>
<%
}else{
	out.print("<h2>没有相关记录!</h2>");
}
%>
<p><b><a href="../home.jsp">返回首页</a></b></p>
</body>
</html>