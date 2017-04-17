

$(function() {
	//渲染表格
	createGrid();
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
		singleSelect:true, //可以多选
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

/**
 * 人员详情
 * @returns
 */
function detail() {
	var basePath = $("#basePath").val();
	//获取选中记录
	var userids = $('#userGrid').datagrid('getChecked');
	if(userids.length != 1){
		$.messager.alert(msgOption(150, 300, 'warning', '请选择一条记录!'));
		return ;
	} else {
		var userid = userids[0].userid;
		//加载用户信息
	    $.ajax({
	        type: "post",
	        url: "/manageSysS4/user/getUserMapByid.do",
	        async: false,//需要同步，因为只有先查出来用户的一些映射关系才可以回显数据
	        data: {"userid":userid},
	        dataType: "json",
	        success: function(data) {
	           $("#username").val(data.username);
	           $("#chname").val(data.chname);
	           $("#contact").val(data.contact);
	           $("#role").val(data.utList[0].usertypename.toString());
	           $('#org').val(data.uoList[0].orgname.toString());
	           $('#dept').val(data.dept);
	        },
	        error: function() {
	            $.messager.alert(msgOption(150, 300, "warning", "用户信息加载失败!"));
	        }
	    });
		
		$("#userDialog").dialog('open');
		$("#userDialog").dialog('move', {top:30, left:150});
	}
}







