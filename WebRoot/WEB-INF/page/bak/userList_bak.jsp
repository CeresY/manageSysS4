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
	<script type="text/javascript" src="<%=basePath%>js/myplugin.js"></script>

</head>
<body>
	<table class="easyui-datagrid" title=""
			data-options="rownumbers:true, singleSelect:false, collapsible:false, url:'<%=basePath%>user/queryAllUser.do',method:'post', 
						  fit:true, toolbar:'#tb'">
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'username',width:150,align:'center'">账号</th>
				<th data-options="field:'chname',width:250, align:'center'">姓名</th>
				<th data-options="field:'contact',width:200,align:'center'">联系方式</th>
				<th data-options="field:'createtime',width:200,align:'center', 
				formatter:function(date){
					if(date != null && date != '') {
						var d1 = date.toString().substring(0,10);
						return formatterDate(d1);
					} else {
						return '';
					}
				}">创建时间</th>
			</tr>
		</thead>
	</table>
	
	<!-- 表格窗口工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
   		<div id="tb" style="height:auto">
    		<a href="javascript:void(0)" class="easyui-linkbutton" 
    			data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" 
    			data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" 
    			data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" 
    			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" 
    			data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
    	</div>
    	
   		<div>
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
   		</div>
    </div>
</body>
</html>