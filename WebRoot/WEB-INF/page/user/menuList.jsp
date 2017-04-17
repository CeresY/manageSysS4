<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>欢迎登陆人事管理系统</title>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>plugin/bootstrap-3.3.5/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>plugin/easyui/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>plugin/easyui/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mycss1.css">

	<script type="text/javascript" src="<%=basePath%>plugin/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugin/bootstrap-3.3.5/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugin/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/commons/myplugin.js"></script><!-- 自己写的公共组件 -->
	<script type="text/javascript" src="<%=basePath%>js/menuList.js"></script>

</head>
<body>
	<table id="menuGrid"></table>

	<!-- 隐藏表单 -->
    <input type="hidden" id="basePath" value="<%=basePath%>">
</body>
</html>