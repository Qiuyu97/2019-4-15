<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
	if(request.getSession().getAttribute("user")!=null){
		response.sendRedirect("index.jsp");
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="js/jquery-3.4.0.min.js"></script>
<script src="js/register.js"></script>
</head>
<body>

	<!-- 表单 -->
	<form name="f1" method="post">
		
		<h1>用户注册</h1>
		
		用户名：
		<input name="logid" type="text" />
		<span></span>
		<br /><br />
		
		
		密码：
		<input name="pwd" type="password" />
		<span></span>
		<br /><br />
		
		确认密码：
		<input name="rpwd" type="password" />
		<span></span>
		<br /><br />
		
		
		验证码：
		<input name="yzm" type="text" />
		<img src="createyzm" />
		<span></span>
		<br /><br />
		
		
		<input id="sub" type="button" value="提交" />
		
		<input id="res" type="button" value="重置" />
		
	</form>
</body>
</html>