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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>plugin/ztree/zTreeStyle.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mycss1.css">

	<script type="text/javascript" src="<%=basePath%>plugin/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugin/bootstrap-3.3.5/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugin/easyui/js/jquery.easyui.min.js"></script>

<!-- 	<meta charset="utf-8"> -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>欢迎登陆人事管理系统</title>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/resource/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>ztree/zTreeStyle.css">
	
	<script type="text/javascript" src="<%=basePath%>js/resource/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/resource/bootstrap.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>ztree/jquery.ztree.all.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/orgList.js"></script>

</head>
<body>
	<div id="p" style="padding:10px;">
		<ul id="orgTree" class="ztree"></ul>
	</div>
	
	<div id="tt" style="text-align:left; float: left;">
		<div style="width:100%;height:40px;margin: 0px;border: 0px;background-color: #ddd;display: block;">vdasvasvasvdasvas</div>
<!-- 		<div style="float:left; text-align:left;"> -->
<!-- 			<a href="javascript:void(0)" class="icon-add" onclick="javascript:alert('add')"></a> -->
<!-- 			<a href="javascript:void(0)" class="icon-edit" onclick="javascript:alert('edit')"></a> -->
<!-- 			<a href="javascript:void(0)" class="icon-cut" onclick="javascript:alert('cut')"></a> -->
<!-- 			<a href="javascript:void(0)" class="icon-help" onclick="javascript:alert('help')"></a> -->
<!-- 		</div> -->
	</div>
	
	<!-- 隐藏表单 -->
    <input type="hidden" id="basePath" value="<%=basePath%>">
</body>
</html>