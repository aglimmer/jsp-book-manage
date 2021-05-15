<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form action="TestServlet" method="post">
<% request.setCharacterEncoding("utf-8"); %>
<% request.setAttribute("userid","34324ifiodsof"); %>
<input type="text" name="userid">

<input type="submit" >
</form>
</html>