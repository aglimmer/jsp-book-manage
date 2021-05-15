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
#tablemain td{
text-align:center;
}
#table td label{font:bold;
align:left;
}
#navbar{
display:bolck;width:100%;height:35px;
}
</style>
</head>
<body>
<div id="navbar">
	<span style="display:bolck;float:left;">
	<b><a href="../home.jsp">返回主页</a></b>
	</span>
</div>
<form action="Edit" method="post">
<table id="tablemain">
<tr><td colspan="6"><input type="text" name="message"><label><input type="submit" value="搜索"></label></td></tr>
<tr>
	<td><label>检索方式：</label></td>
	<td><input type="radio" name="ways" value="bookname" checked="checked"><label>图书名称</label></td>
	<td><input type="radio" name="ways" value="bookid">图书编号</td>
	<td><input type="radio" name="ways" value="author"><label>作者</label></td>
	<td><input type="radio" name="ways" value="publisher"><label>出版社</label></td>
	<td><input type="radio" name="ways" value="publishtime"><label>出版日期</label></td>
</tr>
<tr>
	<td><label>检索需求：</label></td>
	<td><input type="radio" name="range" value="1" checked="checked"><label>模糊检索</label></td>
	<td><input type="radio" name="range" value="2"><label>精确查找</label></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td><label>查看类型：</label></td>
	<td><label><a href="ScanBook?kinds=all">浏览所有</a></label></td>
	<td><label><a href="ScanBook?kinds=computer_science">计算机类</a></label></td>
	<td><label><a href="ScanBook?kinds=literary_novel">文学小说</a></label></td>
	<td><label><a href="ScanBook?kinds=romantic_faction">浪漫小说</a></label></td>
	<td><label><a href="ScanBook?kinds=history_biography">历史传记</a></label></td>
</tr>
</table>
</form>
</body>
</html>