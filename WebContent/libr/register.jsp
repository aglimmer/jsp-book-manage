<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册</title>
<style type="text/css">
#main{
	margin:0 auto;
	width:1280px;
}
#content table{
	margin:0 auto;
	width:480px;
	background-color:#AFEEEE;
	font-size:18px;
}
</style>
<script type="text/javascript" src="JS/AjaxRequest.js"></script>
<script type="text/javascript">
var count = 60;
function settime() {
//	var inter;
    if (count == -1) {
    	count = 60;
    	document.getElementById("butt").removeAttribute("disabled");
    	document.getElementById("butt").value="获取验证码";
   // 	document.getElementById("time").innerHTML="";
  //  	clearInterval(inter);
    } else {
    	document.getElementById("butt").setAttribute("disabled", true);
        
   	     document.getElementById("butt").value="重新获取"+count;
   	  	 count--;
 //       document.getElementById("time").innerHTML=count;
        setTimeout("settime()",1000);
    //    inter = setInterval("settime()", 1000);
    }

}
/******************错误处理的方法*******************************/
function onerror(){
	alert("您的操作有误！");
}
/******************实例化Ajax对象的方法*******************************/
function getInfo(){
	
	var email = document.getElementById("email");
	var pat = /[1-9]{1}\d{7,10}@qq\.com/;
	if(email.value!=""&&email.value.match(pat)){
		alert(email.value);
		var loader=new net.AjaxRequest("EmailDeal?nocache="+new Date().getTime()+"&email="+encodeURI(encodeURI(email.value)),deal_getInfo,onerror,"GET");
	}else{
	document.getElementById("tip").innerHTML="<span><font color='red' size='0.6em'>qq邮箱格式有误！</font></span>";
//	document.getElementById("tip").style.display="block";
	}
}
/************************回调函数**************************************/

 var safecode="";
function deal_getInfo(){
//	alert(this.req.responseText.contains("成功"));
	var tips = this.req.responseText;
	if(tips!=""&&tips.charAt(0)=='已'){
		settime();
//		alert(tips.substring(tips.length-6));
		safecode = tips.substring(tips.length-6);
//		alert(safecode);
	//	document.getElementById("safecode").value=tips.substring(tips.length-6);
		document.getElementById("tip").innerHTML="<span><font color='red' size='0.6em'>"+tips.substring(0,tips.length-6)+"</font></span>";
	}else{
		document.getElementById("tip").innerHTML = "<span><font color='red' size='0.6em'>"+tips+"</font></span>";
	}
	
}
function checkuserid(){
	
	var userid = document.getElementById("userid").value;
	
	if(userid!=""&&userid.length>5){
		document.getElementById("usertip").innerHTML="&nbsp;";
		return true;
	}else{
		document.getElementById("usertip").innerHTML="<span><font color='red' size='0.6em'>请填写用户名！</font></span>";
		return false;
	}
}
function checkpassword(){
	var password = document.getElementById("userpassword").value;
	if(password!=""&&password.length>5){
		document.getElementById("passwordtip").innerHTML="&nbsp;";
		return true;
	}else{
		document.getElementById("passwordtip").innerHTML="<span><font color='red' size='0.6em'>请填写密码！</font></span>";
		return false;
	}
	
}

function checkcode(){
	var usercodes = document.getElementById("safecode").value;
	var b = false;
	if(!checkuserid()&&!checkpassword()){
		return false;
	}
	if(usercodes!=""){
		if(usercodes==safecode)
		{
			if(count!=60){
				return true;	
			}else{
				alert("验证超时");
			}
		}else{
			alert("验证码错误");
		}
	}else{
		alert("验证码不能为空");
	}
	return b;
	
	
}

//3223782091@qq.com
</script>
</head>
<body>
<div id="content"><!-- Register -->
<form action="door.jsp" method="post">	<!--onsubmit="return checkcode()" -->
<table>
	<tr><td colspan="2" align="center"><strong>欢迎注册</strong></td></tr>
	<tr><td colspan="2" align="center"><hr></td></tr>
	<tr>
		<td align="right">用户名</td>
		<td><input type="text" name="userid" id="userid" onblur="checkuserid()"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><div id="usertip">&nbsp;</div></td>
	<tr>
		<td align="right">密&emsp;码</td>
		<td><input type="password" name="userpassword" id="userpassword" onblur="checkpassword()"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><div id="passwordtip">&nbsp;</div></td>
	<tr>
	
	<tr>
		<td align="right">邮&emsp;箱</td>
		<td><input type="text" name="email" id="email"></td>
	</tr>
	<tr>
	<td align="right">
		<input type="button" value="获取验证码" style="background-color:#DDDDDD" id="butt" onclick="getInfo()">
	</td>
	<td align="left">
		<input type="text" name="safecode" id="safecode"/>
	</td>
	</tr>
	
	<tr>
		<td>&nbsp;</td>
		<td align="center"><div id="tip"></div></td>
	</tr>
	
	<tr>
		<td colspan="2" align="center"><hr></td>
	</tr>
	<tr>
		<td colspan="2" align="center" style=""> <strong>问题设置 </strong></td>
	</tr>
	<tr>
		<td align="center">问题</td>
		<td align="center">答案</td>
	</tr>
	<tr>
		<td align="center">
		<input list="list1" type="text" name="question1"/>
		<datalist id="list1">
        <option value="我最想去的地方是"></option>
        <option value="我最好的朋友叫什么名字"></option>
        <option value="我的家乡在哪里"></option>
    	</datalist>
    	</td>
    	<td align="center">
   	 	<input type="text" name="answer1"/>
    	</td>
	<tr>
	
	
	<tr>
		<td align="center">
		<input list="list2" type="text" name="question2"/>
		<datalist id="list2">
        	<option value="我最喜欢的数字"></option>
        	<option value="最喜欢的花"></option>
        	<option value="最喜欢的歌星"></option>
    	</datalist>
    	</td>
    	<td align="center">
   	 	<input type="text" name="answer2"/>
    	</td>
	<tr>
	
	<tr><td colspan="2" align="center">
	<span> <strong>类型:
	<label><input type="radio" name="grade" value="0" checked="checked">用&emsp;户</label>
	<label><input type="radio" name="grade" value="1">管理员</label>
	</strong>
	</span>
	</td>
	</tr>
	<tr>
	<td colspan="2" align="center"><input type="submit" value="注册">
	</td>
	</tr>
</table>
</form>
</div>

</body>
</html>