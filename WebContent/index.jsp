<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(basePath);
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>图书管理系统登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script>
			//直接跳转首页
			window.location.href="libr/login.jsp"; 
	</script>
  </head>
  
  <body>
  
  </body>
</html>
