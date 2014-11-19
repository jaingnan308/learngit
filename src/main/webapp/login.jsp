<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户登录</title>
<style type="text/css">
div#container{width:600px}
div#header {background-color:#BA55D3; height:30px;}
div#menu {height:550px;width:500px;float:left;}
div#content {height:200px;width:300px;float:left;}
div#footer {background-color:#BA55D3;clear:both;text-align:center;}
h1 {margin-bottom:0;}
h2 {margin-bottom:0;font-size:18px;}
ul {margin:0;}
li {list-style:none;}
</style>
<script src="${ctx }/js/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript">
		$(function() {
		});
	</script>
</head>
<body>
	
	<div id="header">
		<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		欢&nbsp;迎&nbsp;使&nbsp;用&nbsp;管&nbsp;家&nbsp;婆&nbsp;软&nbsp;件</h2>
	</div>
	<div id="menu">
	<img src="${ctx }/image/caiwu.png" height="500" width="400" />
	</div>
	<div id="content">
		<br /><br /><br />
		<form action="${ctx }/UserController/userLogin" method="post">
			<br />
			<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用&nbsp;户&nbsp;登&nbsp;录</h4>
			<br />
			&nbsp;&nbsp;&nbsp;&nbsp;用户名：<input name="name" value="" /><br />
			<br />
			<br />
			&nbsp;&nbsp;&nbsp;&nbsp;密  &nbsp;码: <input name="password" value="" /><br />
			<br />
			<br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="login" type="submit" style="background-color:#32CD32;" value="登&nbsp;&nbsp;录" />
		</form>
	<br />
	</div>
	<div id="footer">
	<li>&nbsp;&nbsp;&nbsp;&nbsp;如果您还没有账号请点击此处<a href="${ctx }/view/register.jsp">注册</a></li>
	</div>
</body>
</html>