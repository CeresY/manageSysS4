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
	<script type="text/javascript" src="<%=basePath%>js/commons/mySimpleValidRules.js"></script><!-- 验证框架 -->
	<script type="text/javascript" src="<%=basePath%>js/userList.js"></script>

</head>
<body>
	<table id="userGrid"></table>
	
	<!-- 表格窗口工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
   		<div id="tb" style="height:auto">
    		<a href="javascript:void(0)" class="easyui-linkbutton c1" 
    			data-options="iconCls:'icon-add', plain:true" onclick="addUser();">新增用户</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton c2" 
    			data-options="iconCls:'icon-remove', plain:true" onclick="removeit()">删除用户</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton c2" 
    			data-options="iconCls:'icon-edit', plain:true" onclick="editUserPanel()">修改用户</a>
    	</div>
    	
   		<!-- <div>
   			Date From: <input class="easyui-datebox" style="width:80px">
   			To: <input class="easyui-datebox" style="width:80px">
   			Language: 
   			<select class="easyui-combobox" panelHeight="auto" style="width:100px">
   				<option value="java">Java</option>
   				<option value="c">C</option>
   				<option value="basic">Basic</option>
   				<option value="perl">Perl</option>
   				<option value="python">Python</option>
   			</select>
   			<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
   		</div> -->
   		
   		<div>
   			账号<input class="easyui-textbox" id="username_s"> 
   			姓名<input class="easyui-textbox" id="chname_s">
   			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="findUser();">搜索</a>
   			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cut" onclick="clean();">重置</a>
   		</div>
    </div>
	
	<div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>
	
	<!-- 新增用户弹出框 -->
	<div id="addUserDialog" class="easyui-dialog" style="width:565px;height:300px;text-align:left;overflow:hidden;"
    	data-options="title:'新增用户', modal:true, closed:true, resizable:true, draggable:false, buttons:'#bt-add-user',
    		onClose:function() {
    			$(this).form('clear');
    		}">
   		<div class="container" style="margin-top:50px;">
	   		<form id="userForm" method="post">
	   			<div class="row" style="margin-bottom:10px;">
	   				<div class="col-md-1 col-md-offset-1"><label style="font-weight:normal;">账号</label><span style="color: #ff0000;">*</span></div>
	   				<div class="col-md-3" style="text-align:left;">
	   					<input name="username" type='text' style="width:80%;" class="easyui-validatebox textbox" 
	   						data-options="required:true,validType:['englishCheckSub', 'lenScope[1,32]'],
	   						missingMessage:'请输入账号'">
	   				</div>
	   			</div>
	   			<div class="row" style="margin-bottom:10px;">
	   				<div class="col-md-1 col-md-offset-1"><label style="font-weight:normal;">用户名称</label><span style="color: #ff0000;">*</span></div>
	   				<div class="col-md-3">
	   					<input name="chname" type='text' style="width:80%;" class="easyui-validatebox textbox" 
	   						data-options="required:true, validType:['lenScope[1,32]', 'stringCheck'],
	   						missingMessage:'请输入用户名称'">
	   				</div>
	   			</div>
	   			<div class="row" style="margin-bottom:10px;">
	   				<div class="col-md-1 col-md-offset-1"><label style="font-weight:normal;">角色</label><span style="color: #ff0000;">*</span></div>
	   				<div class="col-md-3">
	   					<input id="role" name="role" style="width:210px;" class="easyui-combobox">
	   				</div>
	   			</div>
	   		</form>
   		</div>
	</div>
    
    <!-- 新增用户-保存按钮 -->
    <div id="bt-add-user" style="text-align:center;">
	    <a href="#" class="easyui-linkbutton" style="width:80px" onclick="saveUser();">保存</a>
    </div>
    
    <!-- 修改用户弹出框 -->
	<div id="editUserDialog" class="easyui-dialog" style="width:565px;height:300px;text-align:left;overflow:hidden;"
    	data-options="title:'修改用户', modal:true, closed:true, resizable:true, draggable:false, buttons:'#bt-edit-user',
    		onClose:function() {
    			$(this).form('clear');
    		}">
   		<div class="container" style="margin-top:50px;">
	   		<form id="userEditForm" method="post">
	   			<div class="row" style="margin-bottom:10px;">
	   				<div class="col-md-1 col-md-offset-1"><label style="font-weight:normal;">账号</label><span style="color: #ff0000;">*</span></div>
	   				<div class="col-md-3" style="text-align:left;">
	   					<input id="edit-username" type='text' style="width:80%;" class="textbox" disabled='disabled'>
	   				</div>
	   			</div>
	   			<div class="row" style="margin-bottom:10px;">
	   				<div class="col-md-1 col-md-offset-1"><label style="font-weight:normal;">用户名称</label><span style="color: #ff0000;">*</span></div>
	   				<div class="col-md-3">
	   					<input name="chname" type='text' style="width:80%;" class="easyui-validatebox textbox" 
	   						id="edit-chname"
	   						data-options="required:true, validType:['lenScope[1,32]', 'stringCheck'],
	   						missingMessage:'请输入用户名称'">
	   				</div>
	   			</div>
	   			<div class="row" style="margin-bottom:10px;">
	   				<div class="col-md-1 col-md-offset-1"><label style="font-weight:normal;">角色</label><span style="color: #ff0000;">*</span></div>
	   				<div class="col-md-3">
	   					<input id="edit-role" name="role" style="width:210px;" class="easyui-combobox"
	   						data-options="required:true, missingMessage:'请选择用户类型'">
	   				</div>
	   			</div>
	   			<input id="edit-userid" type="hidden" name="userid">
	   		</form>
   		</div>
	</div>
	
	<!-- 修改用户-保存按钮 -->
    <div id="bt-edit-user" style="text-align:center;">
	    <a href="#" class="easyui-linkbutton" style="width:80px" onclick="editUser();">保存</a>
    </div>
    
    <!-- 隐藏表单 -->
    <input type="hidden" id="basePath" value="<%=basePath%>">
</body>
</html>