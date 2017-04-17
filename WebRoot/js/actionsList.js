
$(function() {
	//渲染表格
	createGrid();
});

function createGrid() {
	var basePath = $("#basePath").val();
	$("#roleGrid").datagrid({
		url: basePath+'actions/findAllUsertype.do',
		method: 'post',
		columns: [[
		    {field:'typesign', title:'角色编码', width:150, align:'center', hidden:true},
		    {field:'typename', title:'角色名称', width:300, align:'center'}
		]],
		rownumbers:false, //记录编号
		singleSelect: true, //是否单选
//		selectOnCheck: true, //选择行选中
//		checkOnSelect: true, //点击行选中
		collapsible:false, //是否可以关闭表格
		fit:true, //自动渲染到整个页面
		//width: 1024,
		//height: 460,
		toolbar:'#tb', //表头工具栏
		pagination: false, //底部工具栏
		onClickRow: function(index, row) {
			loadActions(index, row)
		},
		//加载完grid数据后执行
		onLoadSuccess: function() {
			//loadUserType('loadUserType');
		}
	});
}

function loadActions(index, row){
	var basePath = $("#basePath").val();
	var typesign = row.typesign;
	$("#actionsGrid").datagrid({
		url: basePath+'actions/findActionAbled.do?sign='+typesign,
		method: 'post',
		columns: [[
		    {field:'menusid', title:'菜单编码', width:150, align:'center', hidden:true},
		    {field:'menuname', title:'菜单栏', width:150, align:'center'},
		    {field:'actions', title:'权限', width:450, align:'left', formatter: function(e) {
		    	if(e != null && e != '') {
		    		var html = "";
		    		$.each(e, function(idx, ele) {
		    			if(ele.isabled == '1') {//具有权限
		    				html += "<div style='folat:left;margin-right:10px;'>" +
		    							"<input type='checkbox' value='"+ele.actionid+"' checked='checked' onclick='saveAbled(this);'>   "+ele.actionname+"</div>";
		    			} else {
		    				html += "<div style='folat:left;margin-right:10px;'>" +
		    						"<input type='checkbox' value='"+ele.actionid+"' onclick='saveAbled(this);'>   "+ele.actionname+"</div>";
		    			}
		    		});
		    		return html;
		    	} else {
		    		return '';
		    	}
		    }},
		]],
		rownumbers:false, //记录编号
		singleSelect: true, //是否单选
		collapsible:false, //是否可以关闭表格
		fit:true, //自动渲染到整个页面
		pagination: false //底部工具栏
	});
}

/**
 * 保存选中权限
 * @returns
 */
function saveAbled(ele) {
	var basePath = $("#basePath").val();
	var sign = $("#roleGrid").datagrid('getSelected').typesign;
	var actionid = $(ele).val();
	var url = basePath;
	//选中
	if($(ele).is(':checked')){
		url += 'actions/addActionMapping.do';
	} 
	//取消
	else {
		url += 'actions/delActionMapping.do';
	}
	
	$.ajax({
		type: "post",
		url: url,
		async: false,
		data: {'actionid': actionid, 'usertypesign':sign},
		dataType: "json",
		success: function(data) {
			//$.messager.alert(msgOption(150, 300, 'info', data.MSG));
		},
		error: function() {
			$.messager.alert(msgOption(150, 300, 'warning', '服务器失效!'));
		}
	});
	
}

function append(){
	$.messager.prompt({
		title: '',
		msg: '请输入权限名称',
		top: 150,
		left: 300,
		ok: '确定',
		cancel: '取消',
		fn: function(r) {
			if(typeof r != 'undefined' && r != null && r != '') {
				addAction(r);
			}
		}
	});
}

function addAction(name) {
	var basePath = $("#basePath").val();
	$.ajax({
        type: "post",
        url: basePath+'role/addRole.do',
        //async: false,
        data: {"name":name},
        dataType: "json",
        success: function(data) {
        	if(data.SUCCESS === 'true') {
        		$('#roleGrid').datagrid('reload'); 
        		$.messager.alert(msgOption(150, 300, "info", data.MSG));
        	} else {
        		$.messager.alert(msgOption(150, 300, "warning", data.MSG));
        	}
        },
        error: function() {
            $.messager.alert(msgOption(150, 300, "warning", "服务器错误，请联系管理员!"));
        }
    });
}

/**
 * 修改角色名称
 * @returns
 */
function editIt() {
	var node = $('#roleGrid').datagrid('getSelected');
	//alert(node.typesign);
	$.messager.prompt({
		title: '',
		msg: '请输入新的角色名称',
		top: 150,
		left: 300,
		ok: '确定',
		cancel: '取消',
		fn: function(r) {
			if(typeof r != 'undefined' && r != null && r != '') {
				editRole(node.typesign, r);
			}
		}
	});
}

function editRole(id, name) {
	var basePath = $("#basePath").val();
	$.ajax({
        type: "post",
        url: basePath+'role/editRole.do',
        //async: false,
        data: {"id":id, "name":name},
        dataType: "json",
        success: function(data) {
        	if(data.SUCCESS === 'true') {
        		$('#roleGrid').datagrid('reload'); 
        		$.messager.alert(msgOption(150, 300, "info", data.MSG));
        	} else {
        		$.messager.alert(msgOption(150, 300, "warning", data.MSG));
        	}
        },
        error: function() {
            $.messager.alert(msgOption(150, 300, "warning", "服务器错误，请联系管理员!"));
        }
    });
}

function removeIt(){
	$.messager.confirm({
		title: '提示',
		msg: '确定要删角色吗？删除后不可恢复!',
		ok: '确定',
		cancel: '取消',
		top: 150,
		left: 300,
		fn: function(r) {
			if(r) {
				delRole();
			}
		}
	});
}

/**
 * 删除选中角色
 * @returns
 */
function delRole() {
	var basePath = $("#basePath").val();
	var node = $('#roleGrid').datagrid('getSelected');
	var id = node.typesign;
	$.ajax({
        type: "post",
        url: basePath+'role/delRole.do',
        //async: false,
        data: {"id":id},
        dataType: "json",
        success: function(data) {
        	if(data.SUCCESS === 'true') {
        		$('#roleGrid').datagrid('reload');
        		$.messager.alert(msgOption(150, 300, "info", data.MSG));
        	} else {
        		$.messager.alert(msgOption(150, 300, "warning", data.MSG));
        	}
        },
        error: function() {
            $.messager.alert(msgOption(150, 300, "warning", "服务器错误，请联系管理员!"));
        }
    });
}












