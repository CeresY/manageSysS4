<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	<script type="text/javascript" src="../js/main.js"></script>
</head>
<body>
	
	<!-- layout开始 -->
	<div id="mainPage" class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'north',title:'人事档案管理系统-标题'" style="height:100px;">
	    	<div style="padding:5px;">
	    		<input type="text" value="<%=basePath%>">
	    		<input type="text" value="${username}">
			</div>
	    </div>

<!-- 	    <div data-options="region:'east',title:'East', title:'右侧边栏'" style="width:100px;"> -->
<!-- 	    </div> -->
	    
	    <!-- 菜单栏 -->
	    <div data-options="region:'west',title:'菜单栏'" style="width:250px;">
    		<div id="menusArea" class="easyui-accordion" style="width:100%;height:100%;">
			</div>
	    </div>
	    
	    <!-- 内容区域 -->
	    <div id="pageTabs" class="easyui-tabs" data-options="region:'center', fit:true" style="padding:0px;background:#eee;">
	    	
	    </div>
	    
	    <!-- 页脚 -->
   	    <div data-options="region:'south'" style="height:30px;">
	    	<div id="footer" style="padding:5px;">
				页脚
			</div>
	    </div>
	</div>
	<!-- layout结束 -->
</body>
</html>