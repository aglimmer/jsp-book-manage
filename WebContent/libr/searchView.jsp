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
#navbar{
display:bolck;width:100%;height:35px;
}
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
<div id="navbar">
	<span style="display:block;float:left;">
	<b><a href="search.jsp">返回上一级</a></b>
	</span>
	<span style="display:bolck;float:right;">
	<b><a href="home.jsp">返回主页</a></b>
	</span>
</div>
<div>
<%	ArrayList<Book>list = (ArrayList<Book>)request.getAttribute("bookList"); %>
<%
	if(list!=null&&list.size()>0){	
%>
<%@page import="java.net.URLEncoder"%>
<table rules="all" align="center" id="content">
<caption>检索结果</caption>
<tr ><th>索书号</th><th>书名<th>作者</th><th>出版社</th><th>出版日期</th><th>库存</th><th>可借数量</th><th>&nbsp;</th></tr>
<% 		
		for(Book book:list){
%>
<tr><td><%=book.getBookid()%></td>
	<td><%=book.getBookname() %></td>
	<td><%=book.getAuthor() %></td>
	<td><%=book.getPublisher() %></td>
	<td><%=book.getPublishtime() %></td>
	<td><%=book.getTotal() %></td>
	<td><%=book.getSurplus() %></td>
	<td>
	<a href="Borrow?kinds=<%=request.getParameter("kinds")%>
	&bookid=<%=book.getBookid()%>
	&bookname=<%=URLEncoder.encode(book.getBookname(),"utf-8")%>
	&author=<%=URLEncoder.encode(book.getAuthor(),"utf-8")%>
	&publisher=<%=URLEncoder.encode(book.getPublisher(),"utf-8")%>
	&publishtime=<%=URLEncoder.encode(book.getPublishtime(),"utf-8")%>
	&message=<%=URLEncoder.encode(request.getAttribute("message").toString(),"utf-8")%>
	&count=<%=request.getAttribute("count").toString()%>
	&currpage=<%=request.getAttribute("currpage").toString()%>
	&ways=<%=request.getAttribute("ways").toString()%>">
	<span>借阅</span>
	</a>
	</td>
</tr>
<%
}
%>
<tr><td colspan="7" align="center"><%=request.getAttribute("bar")%></td></tr>
</table>
<%
}else{

	out.print("<b>提示：没有搜索到记录!</b>");
}
%>
</div>
</body>
</html>