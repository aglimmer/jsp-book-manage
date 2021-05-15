<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#tablemain{
margin:0 auto;
background:#FFF0F5;
border:1px solid #CCEEFF;
}
#table td label{
font:bold;
}
#navbar{
display:bolck;width:100%;height:35px;
}
</style>
</head>
<body>
<div id="navbar">
	<span style="display:bolck;float:left;">
	<b><a href="home.jsp">返回首页</a></b>
	</span>
</div>
<div>
<form action="Search" method="post">
<table id="tablemain">
<tr><td colspan="6" align="center" id="search"><input type="text" name="message"/>
	<input type="submit" value="搜索">
	</td>
</tr>
<tr><td><label>检索方式：</label></td>
	<td><input type="radio" name="ways" value="bookid"><span>索书号</span></td>
	<td><input type="radio" name="ways" value="bookname" checked="checked"><span>书名</span></td>
	<td><input type="radio" name="ways" value="author"><span>作者</span></td>
	<td><input type="radio" name="ways" value="publisher"><span>出版社</span></td>
	<td><input type="radio" name="ways" value="publishtime"><span>出版时间</span></td>
</tr>
<tr><td><label>检索类别：</label></td>
	<td><input type="radio" name="kinds" value="literary_novel" checked="checked"><span>文学小说</span></td>
	<td><input type="radio" name="kinds" value="computer_science" ><span>计算机类</span></td>
	<td><input type="radio" name="kinds" value="history_biography"><span>历史传记</span></td>
	<td><input type="radio" name="kinds" value="romantic_faction"><span>言情小说</span></td>
	<td>&nbsp;</td>
</tr>
</table>
</form>
</div>
</body>
</html>