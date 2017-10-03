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
	<script type="text/javascript" src="<%=basePath%>js/commons/myplugin.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/userShow.js"></script>

</head>
<body>
	<table id="userGrid"></table>
	
	<!-- 表格窗口工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
		<div style="width:85px;float:left;">
   			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:70px" onclick="detail();">详情</a>
		</div>
   		<div>
   			账号<input class="easyui-textbox" id="username_s"> 
   			姓名<input class="easyui-textbox" id="chname_s">
   			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="findUser();">搜索</a>
   			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cut" onclick="clean();">重置</a>
   		</div>
    </div>
	
	<div id="userDialog" class="easyui-dialog" style="width:725px;height:300px;overflow:hidden;padding:15px;"
    	data-options="title:'人员详情', modal:true, closed:true, resizable:true, draggable:false,
    		onClose:function() {
    			$(this).form('clear');
    		}">
   		<div class="container" style="width:550px;">
   			<form class="form-horizontal">
			<div class="row form-group">
				<label for="username" class="col-sm-2 control-label">账号</label>
				<div class="col-sm-4">
					<input id="username" type="text" class="form-control input-sm" disabled>
				</div>
				<label for="chname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-4">
					<input id="chname" name="chname" type="text" class="form-control input-sm" disabled>
				</div>
			</div>
			<div class="row form-group">
				<label for="role" class="col-sm-2 control-label">角色</label>
				<div class="col-sm-4">
					<input id="role" name="org" class="form-control input-sm" disabled>
				</div>
				<label for="org" class="col-sm-2 control-label">所属机构</label>
				<div class="col-sm-4">
					<input id="org" name="org" class="form-control input-sm" disabled>
				</div>
			</div>
			<div class="row form-group">
				<label for="dept" class="col-sm-2 control-label">所属岗位</label>
				<div class="col-sm-4">
					<input id="dept" type="text" class="form-control input-sm" disabled>
				</div>
			</div>
			<div class="row form-group">
				<label for="contact" class="col-sm-2 control-label">联系方式</label>
				<div class="col-sm-10">
					<input id="contact" type="text" class="form-control input-sm" disabled>
				</div>
			</div>
			</form>
		</div>
	</div>
    
    <!-- 隐藏表单 -->
    <input type="hidden" id="basePath" value="<%=basePath%>">
</body>
</html>