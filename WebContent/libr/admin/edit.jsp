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
margin:0 auto;
text-align:center;
width:450px;
border-top:1px solid #BBFFEE;
border-left:1px solid #BBFFEE;
background:#AFEEEE;
}
#content td{
border-right:1px solid #BBFFEE;
border-bottom:1px solid #BBFFEE;
text-align:center;
height:35px;
}
</style>
</head>
<body>
<%@page import="java.net.URLEncoder"%>
<form action="EditOperate" method="post">
<table id="content">
<caption>基本信息</caption>
<tr>
	<td>图书编号</td>
	<td><%=request.getParameter("bookid")%></td>
</tr>
<tr>
	<td>书名</td>
	<td><input type="text" name="bookname" value="<%=request.getParameter("bookname")%>"></td>
</tr>
<tr>
	<td>作者</td>
	<td><input type="text" name="author" value="<%=request.getParameter("author")%>"></td>
</tr>
<tr>
	<td>出版社</td>
	<td><input type="text" name="publisher" value="<%=request.getParameter("publisher")%>"></td>
</tr>
<tr>
	<td>出版日期</td>
	<td><input type="text" name="publishtime" value="<%=request.getParameter("publishtime")%>"></td>
</tr>
<tr>
	<td>库存</td>
	<td><input type="text" name="total" value="<%=request.getParameter("total")%>"></td>
</tr>
<tr>
	<td>可借数量</td>
	<td><input type="text" name="surplus" value="<%=request.getParameter("surplus")%>"></td>
</tr>
<tr>
	<td colspan="7" align="center">
	<input type="submit" value="确定">
	</td>
</tr>
</table>
<span>
	<input type="text" name="bookid" style="display:none" value="<%=request.getParameter("bookid") %>">
	<input type="text" name="message" style="display:none" value="<%=request.getParameter("message") %>">
	<input type="text" name="ways" style="display:none" value="<%=request.getParameter("ways") %>">
	<input type="text" name="sum" style="display:none" value="<%=request.getParameter("sum") %>">
	<input type="text" name="range" style="display:none" value="<%=request.getParameter("range") %>">
	<input type="text" name="pagecode" style="display:none" value="<%=request.getParameter("pagecode") %>">
</span>
</form>
<p><b><a href="../home.jsp">返回首页</a></b></p>
</body>
</html>