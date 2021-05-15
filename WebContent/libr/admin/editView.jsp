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
<caption>检索结果</caption>
<tr><th><input type="checkbox" name="checkall">全选/反选</th>
	<th>索书号</th>
	<th>书名</th>
	<th>作者</th>
	<th>出版社</th>
	<th>出版日期</th>
	<th>库存</th>
	<th>可借数量</th>
	<th>操作</th>
</tr>
<% 	//request.getParameter("ways")
	//request.getParameter("sum")
	//request.getParameter("range")
	//request.getParameter("message")
	//request.getParameter("pagecode")
	String header ="ways="+URLEncoder.encode(request.getParameter("ways"),"utf-8")
		+"&sum="+request.getAttribute("sum")
		+"&range="+request.getParameter("range")
		+"&message="+URLEncoder.encode(request.getParameter("message"),"utf-8")
		+"&pagecode="+request.getAttribute("pagecode")
		+"&bar="+URLEncoder.encode(request.getAttribute("bar").toString(),"utf-8");

	for(int i = 0; i<list.size();i++){
		Book book = list.get(i);
%>
<tr><td><input type="checkbox" name="box" value="<%=book.getBookid()%>"></td>
	<td><%=book.getBookid()%></td>
	<td><%=book.getBookname() %></td>
	<td><%=book.getAuthor() %></td>
	<td><%=book.getPublisher() %></td>
	<td><%=book.getPublishtime() %></td>
	<td><%=book.getTotal() %></td>
	<td><%=book.getSurplus() %></td>
	<td>
		<a href="DelOperate?<%=header%>&bookid=<%=book.getBookid()%>">删除</a>&nbsp;
		<a href="edit.jsp?<%=header%>
		&bookid=<%=URLEncoder.encode(book.getBookid(),"utf-8")%>
		&bookname=<%=URLEncoder.encode(book.getBookname(),"utf-8")%>
		&author=<%=URLEncoder.encode(book.getAuthor(),"utf-8")%>
		&publisher=<%=URLEncoder.encode(book.getPublisher(),"utf-8")%>
		&publishtime=<%=URLEncoder.encode(book.getPublishtime(),"utf-8")%>
		&total=<%=URLEncoder.encode(""+book.getTotal(),"utf-8")%>
		&surplus=<%=URLEncoder.encode(""+book.getSurplus(),"utf-8")%>">编辑</a>
	</td>
</tr>
<%
}

%>
<tr><td colspan="9" align="center"><%=request.getAttribute("bar")%></td></tr>
</table>
<%
}else{
	out.print("<h2>没有搜索到记录!</h2>");
}
%>
<p><b><a href="../home.jsp">返回首页</a></b></p>
</body>
</html>