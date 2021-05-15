<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统首页</title>
<script type="text/javascript">
function check(form){
	var user = form.userid;
	var password = form.userpassword;
	var b = true;
	if(user.value==""){
		document.getElementById("tip1").innerHTML="<p style='font-size:12px;color:red;margin-top:-2px;'>请填写用户名！</p>";
		document.getElementById("tip1").style.display="block";
		b = false;
	}
	var tip = "";
	if(password.value==""){
			tip = "密码不能为空！";
			b = false;
	}
	else if(password.value!=""&&password.value.length<6){
			tip = "密码长度不能小于6!";
			b = false;
	}
	if(tip!=""){
			document.getElementById("tip2").innerHTML="<p style='font-size:12px;color:red;margin-top:-2px;'>"+tip+"</p>";
			document.getElementById("tip2").style.display="block";
	}	
	return b;
}
function checkid(){
	document.getElementById("tip1").style.display="none";
}
function checkpassword(){
	document.getElementById("tip2").style.display="none";
}

</script>
<style type="text/css">
body{
padding:0;
	margin:0;
	font-family:Arial, Helvetica, sans-serif;
}
#main{
width:942px;
margin:0 auto;
text-align:center;
}

#header{
width:100%;
height:60px;
background-color:#FFCCCC;
}
#tab{
margin:0 auto;
}
#tab table{
margin:20px auto;
font-size:24px;
width:400px;
background-color:#AFEEEE;
}
#tab tr{
margin-top:5px;
}
</style>
</head>
<body>
<div id="main">
<div id="header"><p style="line-height:60px;text-align:center;font-size:28px">欢迎访问致学图书馆！</p></div>
<div id="tab">
<form action="Enter" method="post" onsubmit="return check(this)">
<table >
	<tr><td colspan="2" align="center">用户登录</td></tr>
	<tr>
		<td align="right">用户名</td>
		<td><input type="text" name="userid"  value="user123456" onfocus="checkid()"></td>
	</tr>
	<tr><td colspan="2" align="center"><div id="tip1" style="display:none;"></div></td></tr>
	<tr>
		<td align="right">密&emsp;码</td>
		<td><input type="password" name="userpassword" value="userpw123456" onfocus="checkpassword(this)"></td>
	</tr>
	<tr><td colspan="2" align="center"><div id="tip2" style="display:none;"></div></td></tr>
	<tr>
	<td align="right" >
	<span>类&emsp;型</span>
	</td>
	<td align="center">
	<label><input type="radio" name="grade" value="0" checked="checked">用&emsp;户</label>
	<label><input type="radio" name="grade" value="1">管理员</label>
	</td>
	</tr>
	<tr>
	<td align="center" colspan="2"><input type="submit" value="登录">&nbsp;&nbsp;
	<a href="register.jsp"><button type="button">注册</button></a>
	</td>
	</tr>
	<tr><td colspan="2" align="right"><a href="">忘记密码...</a></td></tr>
</table>
</form>
</div>
</div>
</body>
</html>