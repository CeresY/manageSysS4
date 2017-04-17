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
   		<div>
   			账号<input class="easyui-textbox" id="username_s"> 
   			姓名<input class="easyui-textbox" id="chname_s">
   			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="findUser();">搜索</a>
   			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cut" onclick="clean();">重置</a>
   		</div>
    </div>
	
	<div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>
	
	<!-- 新增用户弹出框 bootstrap风格 -->
	<div id="addUserDialog" class="easyui-dialog" style="width:725px;height:300px;text-align:left;overflow:hidden;padding:15px;"
    	data-options="title:'新增用户', modal:true, closed:true, resizable:true, draggable:false, buttons:'#bt-add-user',
    		onClose:function() {
    			$(this).form('clear');
    		}">
    	<div class="container" style="text-align:left; width:550px;">
    		<form id="userForm" method="post" class="form-horizontal">
    			<div class="row form-group">
					<label for="username" class="col-sm-2 control-label">账号<span style="color: #ff0000;">*</span></label>
	    			<div class="col-sm-4">
		    			<input id="username" name="username" type="text" class="form-control input-sm easyui-validatebox" placeholder="输入账号"
								data-options="required:true,validType:['englishCheckSub', 'lenScope[1,32]'], missingMessage:'请输入账号'">
	    			</div>
					<label for="chname" class="col-sm-2 control-label">姓名<span style="color: #ff0000;">*</span></label>
					<div class="col-sm-4">
						<input id="chname" name="chname" type="text" class="form-control input-sm easyui-validatebox" placeholder="姓名"
								data-options="required:true, validType:['lenScope[1,32]', 'stringCheck'], missingMessage:'请输入姓名'">
					</div>
				</div>
				<div class="row form-group">
					<label for="role" class="col-sm-2 control-label">角色<span style="color: #ff0000;">*</span></label>
	    			<div class="col-sm-4">
		    			<select id="role" name="role" class="form-control input-sm easyui-validatebox" placeholder="选择角色"
		    					data-options="required:true, validType:['isNotEmpty'], missingMessage:'请选择用户类型'">
		    					<option value=""></option>
		    			</select>
	    			</div>
					<label for="org" class="col-sm-2 control-label">所属机构<span style="color: #ff0000;">*</span></label>
					<div class="col-sm-4">
						<input id="org" name="org" lass="form-control easyui-validatebox" 
							data-options="required:true, validType:['isNotEmpty'], missingMessage:'请选择所属机构'">
					</div>
				</div>
				<div class="row form-group">
					<label for="dept" class="col-sm-2 control-label">所属岗位<span style="color: #ff0000;">*</span></label>
					<div class="col-sm-4">
						<input id="dept" name="dept" lass="form-control easyui-validatebox" 
							data-options="required:true, validType:['isNotEmpty'], missingMessage:'请选择所属岗位'">
					</div>
				</div>
				<div class="row form-group">
					<label for="contact" class="col-sm-2 control-label">联系方式</label>
	    			<div class="col-sm-10">
		    			<input id="contact" name="contact" type="email" class="form-control input-sm easyui-validatebox" placeholder="jane.doe@example.com"
		    					data-options="required:false, validType:['lenScope[0,128]']">
	    			</div>
				</div>
    		</form>
    	</div>
    </div>
    
    <!-- 新增用户-保存按钮 -->
    <div id="bt-add-user" style="text-align:center;">
	    <a href="#" class="easyui-linkbutton" style="width:80px" onclick="saveUser();">保存</a>
    </div>
    
    <!-- 修改用户弹出框 bootstrap风格-->
	<div id="editUserDialog" class="easyui-dialog" style="width:725px;height:300px;text-align:left;overflow:hidden;padding:15px;"
    	data-options="title:'修改用户', modal:true, closed:true, resizable:true, draggable:false, buttons:'#bt-edit-user',
    		onClose:function() {
    			$(this).form('clear');
    		}">
   		<div class="container" style="text-align:left; width:550px;">
    		<form id="userEditForm" method="post" class="form-horizontal">
    			<div class="row form-group">
					<label for="edit-username" class="col-sm-2 control-label">账号<span style="color: #ff0000;">*</span></label>
	    			<div class="col-sm-4">
		    			<input id="edit-username" type="text" class="form-control input-sm" disabled>
	    			</div>
					<label for="edit-chname" class="col-sm-2 control-label">姓名<span style="color: #ff0000;">*</span></label>
					<div class="col-sm-4">
						<input id="edit-chname" name="chname" type="text" class="form-control input-sm easyui-validatebox" placeholder="姓名"
								data-options="required:true, validType:['lenScope[1,32]', 'stringCheck'], missingMessage:'请输入姓名'">
					</div>
				</div>
				<div class="row form-group">
					<label for="edit-role" class="col-sm-2 control-label">角色<span style="color: #ff0000;">*</span></label>
	    			<div class="col-sm-4">
		    			<select id="edit-role" name="role" class="form-control input-sm easyui-validatebox" placeholder="选择角色"
		    					data-options="required:true, validType:['isNotEmpty'], missingMessage:'请选择用户类型'">
		    					<option value=""></option>
		    			</select>
	    			</div>
					<label for="edit-org" class="col-sm-2 control-label">所属机构<span style="color: #ff0000;">*</span></label>
					<div class="col-sm-4">
						<input id="edit-org" name="org" lass="form-control easyui-validatebox" 
							data-options="required:true, validType:['isNotEmpty'], missingMessage:'请选择所属机构'">
					</div>
				</div>
				<div class="row form-group">
					<label for="edit-dept" class="col-sm-2 control-label">所属岗位<span style="color: #ff0000;">*</span></label>
	    			<div class="col-sm-4">
		    			<input id="edit-dept" type="text" class="form-control input-sm" disabled>
	    			</div>
				</div>
				<div class="row form-group">
					<label for="edit-contact" class="col-sm-2 control-label">联系方式</label>
	    			<div class="col-sm-10">
		    			<input id="edit-contact" name="contact" type="email" class="form-control input-sm easyui-validatebox" placeholder="jane.doe@example.com"
		    					data-options="required:false, validType:['lenScope[0,128]']">
	    			</div>
				</div>
				<input id="edit-userid" type="hidden" name="userid">
    		</form>
    	</div>
    	
		<input id="edit-role-old" type="hidden">
		<input id="edit-org-old" type="hidden">
	</div>
	
	<!-- 修改用户-保存按钮 -->
    <div id="bt-edit-user" style="text-align:center;">
	    <a href="#" class="easyui-linkbutton" style="width:80px" onclick="editUser();">保存</a>
    </div>
    
    <!-- 隐藏表单 -->
    <input type="hidden" id="basePath" value="<%=basePath%>">
</body>
</html>