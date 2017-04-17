
/**
 * 绝对地址
 */


$(function() {
	//渲染表格
	createGrid();
	/*//加载用户类型信息
	loadUserType();*/
});

/**
 * 创建表格
 * @returns
 */
function createGrid() {
	var basePath = $("#basePath").val();
	$("#userGrid").datagrid({
		url: basePath+'user/queryAllUser.do',
		method: 'post',
		columns: [[
		    {field:'ck', checkbox:true },
		    {field:'userid', title:'userid', width:150, align:'center', hidden:true},
		    {field:'username', title:'账号', width:150, align:'center'},
		    {field:'chname', title:'姓名', width:250, align:'center'},
		    {field:'contact', title:'联系方式', width:200,align:'center'},
		    {field:'createtime', title:'创建时间', width:200,align:'center',formatter:function(date){
		    	if(date != null && date != '') {
		    		var d1 = date.toString().substring(0,10);
		    		return formatterDate(d1);
		    	} else {
		    		return '';
		    	}
		    }}
		]],
		rownumbers:true, //记录编号
		singleSelect:false, //可以多选
		selectOnCheck: true, //选择行选中
		checkOnSelect: true, //点击行选中
		collapsible:false, //是否可以关闭表格
		//fit:true, //自动渲染到整个页面
		width: 1024,
		height: 460,
		toolbar:'#tb', //表头工具栏
		pagination: true, //底部工具栏
	});
	
	var p = $('#userGrid').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [10, 15, 20],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
    });
}

/**
 * 新增用户，打开对话框
 * @returns
 */
function addUser() {
	var basePath = $("#basePath").val();
	//加载用户类型
	loadUserType_select("role");
	//加载机构树
	$("#org").combotree({
		url: basePath+'org/findOrg.do',
		required: true,
		width: 154,
		height: 30,
		panelWidth: 200
	});
	//加载岗位树
	$("#dept").combotree({
		url: basePath+'dept/findDeptTree.do',
		required: true,
		width: 154,
		height: 30,
		panelWidth: 200
	});
	
	$("#addUserDialog").dialog('open');
	
	//获取页面的文档高度   
	var h = $(document.body).height();   
	//获取页面的文档宽度   
	var w = $(document.body).width();
	$("#addUserDialog").dialog('move', {
		top: 30,
		left: 130
	});
}

/**
 * 保存新建用户信息
 * @returns
 */
function saveUser() {
	//$.messager.alert('提示信息', $('#userForm').form('validate'), 'info');
	//ajax提交 
	$('#userForm').form({
		url:'/manageSysS4/user/addUser.do',
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(data){
			var data = eval('(' + data + ')');
			if(data.success == 'true') {
				//清除数据
				$('#userForm').form('clear');
				$("#addUserDialog").dialog('close');
				$('#userGrid').datagrid('reload'); 
				//$.messager.alert('提示信息', data.msg, 'info');
				$.messager.alert({
					title: '提示信息',
					iconCls: 'icon-ok',
					top: 150,
					left: 300,
					content: data.msg,
					ok: '确定'
				});
			}
		}
	});
	$('#userForm').submit();
}

/**
 * 加载用户类型信息(用于新增用户，选择用户类型下拉列表框)
 * @returns
 */
function loadUserType_combobox(positoin) {
	$.ajax({
		type: "post",
		url: "/manageSysS4/user/queryAllUsertype.do",
		async: false,
		data: {},
		dataType: "json",
		success: function(data) {
			$("#"+positoin.toString()).combobox({
				data: data,
				valueField: 'typesign',
				textField: 'typename',
				editable: false
			});
		},
		error: function() {
			alert("用户类型加载失败");
		}
	});
}

function loadUserType_select(positoin) {
	$.ajax({
		type: "post",
		url: "/manageSysS4/user/queryAllUsertype.do",
		async: false,
		data: {},
		dataType: "json",
		success: function(data) {
			$("#"+positoin.toString()).empty();
			$("<option value=''></option>").appendTo("#"+positoin.toString());
			$.each(data, function(i, ele) {
				$("<option value='"+ele.typesign+"'>"+ele.typename+"</option>").appendTo("#"+positoin.toString());
			});
		},
		error: function() {
			alert("用户类型加载失败");
		}
	});
}

/**
 * 删除用户
 * @returns
 */
function removeit() {
	//获取选中记录
	var userids = $('#userGrid').datagrid('getChecked');
	var useridArray = new Array();
	$.each(userids, function(i, ele) {
		useridArray.push(ele.userid);
	});
	
	if(useridArray.length > 0) {
		$.ajax({
			type: "post",
			url: "/manageSysS4/user/delUsers.do",
			async: false,
			traditional: true,//传入数组
			data: {userids: useridArray},
			dataType: "json",
			success: function(data) {
				var msg = "";
				if(data.success == 'true') {
					msg = "成功";
				} else {
					msg = "失败";
				}
				//刷新用户列表 
				$('#userGrid').datagrid('reload'); 
				$.messager.alert({
					title: '提示',
					top: 150,
					left: 300,
					content: "<div class='messager-icon messager-info'></div>用户删除"+msg+"!",
					ok: '确定'
				});
			},
			error: function() {
				$.messager.alert({
					title: '提示',
					top: 150,
					left: 300,
					content: "<div class='messager-icon messager-warning'></div>用户删除失败!",
					ok: '确定'
				});
			}
		});
	} else {
		//$.messager.alert('提示', '请选择至少一条记录!', 'warning');
		$.messager.alert({
			title: '提示',
			top: 150,
			left: 300,
			content: "<div class='messager-icon messager-warning'></div>请选择至少一条记录!",
			ok: '确定'
		});
	}
}

/**
 * 修改用户信息面板
 * @returns
 */
function editUserPanel() {
	var basePath = $("#basePath").val();
	//获取选中记录
	var userids = $('#userGrid').datagrid('getChecked');
	if(userids.length != 1){
		$.messager.alert(msgOption(150, 300, 'warning', '请选择一条记录!'));
		return ;
	} else {
		var userid = userids[0].userid;
		//加载用户类型
		loadUserType_select("edit-role");
		
		//加载机构树
		$("#edit-org").combotree({
			url: basePath+'org/findOrg.do',
			required: true,
			width: 154,
			height: 30,
			panelWidth: 200
		});
		
		//加载用户信息
	    $.ajax({
	        type: "post",
	        url: "/manageSysS4/user/getUserMapByid.do",
	        async: false,//需要同步，因为只有先查出来用户的一些映射关系才可以回显数据
	        data: {"userid":userid},
	        dataType: "json",
	        success: function(data) {
	           $("#edit-userid").val(data.userid);
	           $("#edit-username").val(data.username);
	           $("#edit-chname").val(data.chname);
	           $("#edit-contact").val(data.contact);
	           $("#edit-role").val(data.utList[0].usertypesign.toString());
	           $('#edit-org').combotree('setValue', data.uoList[0].orgid.toString());
	           $('#edit-dept').val(data.dept);
	        },
	        error: function() {
	            $.messager.alert(msgOption(150, 300, "warning", "用户信息加载失败!"));
	        }
	    });
		
		$("#editUserDialog").dialog('open');
		$("#editUserDialog").dialog('move', {top:30, left:150});
	}
}

/**
 * 保存修改的用户信息
 * @returns
 */
function editUser() {
	 //jax提交
    $('#userEditForm').form({
        url:'/manageSysS4/user/editUser.do',
        onSubmit:function(){
            return $(this).form('validate');
        },
        success:function(data){
            var data = eval('(' + data + ')');
            if(data.success == 'true') {
                //清除数据
                $('#userEditForm').form('clear');
                $("#editUserDialog").dialog('close');
                $('#userGrid').datagrid('reload');
                $.messager.alert(msgOption(150, 300, "info", "用户信息修改成功!"));
            }
        }
    });
    $('#userEditForm').submit();
}

/**
 * 搜索
 * @returns
 */
function findUser() {
	var username = $("#username_s").val();
	var chname = $("#chname_s").val();
	$('#userGrid').datagrid('reload', {
		"username": username,
		"chname": chname
	}); 
}

/**
 * 清空搜索框
 * @returns
 */
function clean() {
	$("#username_s,#chname_s").textbox('clear');
}