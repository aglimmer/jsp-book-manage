<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<jsp:useBean id="newuser" class="com.libr.user.NewUser"></jsp:useBean>
<jsp:useBean id="acc" class="com.libr.login.account.Account"></jsp:useBean>
<jsp:setProperty property="*" name="newuser"/>
<%
int row = acc.register(newuser);
if(row!=1) {
	out.print("<h2>提示：注册失败，账户已存在</h2>");
	out.print("<p><b><a href=\"login.jsp\">返回</a></b></h2>");
}else{
	out.print("<h2>提示：注册成功！</h2>");
}
%>
<%
	int x = newuser.getGrade();
	if(x==1){
%>
<div id="main">
<form action="" method="post">
<table>
<tr>
	<td colspan="2"  align="center">管理员身份认定</td>
</tr>
<tr>
	<td align="right">真实姓名</td>
	<td align="left"><input type="text" name="realname"></td>
</tr>
<tr>
	<td align="right">身份证号</td>
	<td align="left"><input type="text" name="idcard"></td>
</tr>
<tr>
	<td colspan="2" align="center">申请说明</td>
</tr>
<tr>
	<td colspan="2" align="center"><textarea name='applyinfo' wrap='hard'></textarea></td>
<tr>
<tr>
	<td colspan="2" align="center"><input type="submit" value="提交"></td>
</tr>
</table>
</form>
</div>
<%} %>

<!-- 
<jsp:getProperty property="userid" name="newuser"/>
<br>
<jsp:getProperty property="userpassword" name="newuser"/>
<br>
<jsp:getProperty property="email" name="newuser"/>
<br>
<jsp:getProperty property="question1" name="newuser"/>
<br>
<jsp:getProperty property="answer1" name="newuser"/>
<br>
<jsp:getProperty property="question2" name="newuser"/>
<br>
<jsp:getProperty property="answer2" name="newuser"/>
<br>
<jsp:getProperty property="grade" name="newuser"/>
 -->
</body>
</html>