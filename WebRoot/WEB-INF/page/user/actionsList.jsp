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
	<script type="text/javascript" src="<%=basePath%>js/actionsList.js"></script>

</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:false" style="width:300px;">
			<table id="roleGrid"></table>
		</div>
	    <div data-options="region:'center'" style="width:512px;">
	    	<table id="actionsGrid"></table>
	    </div>
	</div>
	
	<div id="tb" style="padding:5px;height:auto">
		<div id="tb" style="height:auto">
    		<a href="javascript:void(0)" class="easyui-linkbutton c1" 
    			data-options="iconCls:'icon-add', plain:true" onclick="append();">新增角色</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton c2" 
    			data-options="iconCls:'icon-edit', plain:true" onclick="editIt()">修改角色</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton c2" 
    			data-options="iconCls:'icon-remove', plain:true" onclick="removeIt()">删除角色</a>
    	</div>
	</div>
	
	<!-- 隐藏表单 -->
    <input type="hidden" id="basePath" value="<%=basePath%>">
</body>
</html>