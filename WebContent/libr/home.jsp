<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
<style type="text/css">
body{
margin:0 auto;
width:100%;
height:100%;
}
#mainnav{
margin:0 auto;
width:1280px;
}
#mainnav ul{
padding:0;
margin:0 auto;
list-style-type:none;
display:block;
font-size:18px;
height:35px;
width:600px;
}
#mainnav li{
width:80px;
display:block;
float:left;
margin-left:5px;
}
#mainnav li a{
display:block;
height:35px;
line-height:35px;
text-align:center;
text-decoration:none;
background:#BBFFEE;
border:1 px solid #AAFFEE;
}
</style>
</head>
<body>
<div id="mainnav">
<ul>
<%@page import="com.libr.user.User" %>
<li><a href="home.jsp">首页</a></li>
<li><a href="Popular">大家在看</a></li>
<li><a href="Latest">最新图书</a></li>
<li><a href="search.jsp">搜一搜</a></li>
<li><a href="Back">还书</a></li>
<li><a href="admin/addBook.jsp">管理员</a></li>
</ul>
</div>
</body>
</html>