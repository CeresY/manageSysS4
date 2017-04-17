
/**
 * 权限列表
 */
$(function() {
	$.extend($.fn.datagrid.methods, {
		editCell: function(jq,param){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field){
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		}
	});
	//渲染表格
	createGrid();
});

function createGrid() {
	var basePath = $("#basePath").val();
	$("#menuGrid").datagrid({
		url: basePath+'actions/queryAllActions.do',
		method: 'post',
		columns: [[
		    {field:'actionid', title:'actionid', width:150, align:'center', hidden:true},
		    {field:'actionname', title:'子菜单', width:150, align:'center'},
		    {field:'menuname', title:'菜单栏', width:250, align:'center',
		    	editor: {
		    		type:'combobox',
		    		options:{  
		    			valueField: 'menusid',
						textField: 'menuname',  
	                    url: "/manageSysS4/actions/findAllMenus.do",
	                    required:true,
	                    editable: false,
	                    onSelect: function(){
	                    	var groupid = $(this).combobox('getValues');
	                    	var rowObj = $('#menuGrid').datagrid('getSelected');
	                    	var actionid = rowObj.actionid;
	                    	saveCell(actionid, groupid.toString());
	                    }
	                } 
		    	},
		    	formatter: function(val, row) {
		    		return '<span>'+val+'  <img src="../plugin/easyui/css/icons/pencil.png"/></span>';
		    	}
		    },
		    {field:'menugroup', title:'菜单栏id', width:200,align:'center',hidden:true}
		]],
		rownumbers:true, //记录编号
		singleSelect: true, //是否单选
//		selectOnCheck: true, //选择行选中
//		checkOnSelect: true, //点击行选中
		collapsible:false, //是否可以关闭表格
		//fit:true, //自动渲染到整个页面
		width: 1024,
		height: 460,
		//toolbar:'#tb', //表头工具栏
		pagination: false, //底部工具栏
		onClickCell: onClickCell,//单元格编辑
		//加载完grid数据后执行
		onLoadSuccess: function() {
			//loadUserType('loadUserType');
		}
	});
}

/**
 * 编辑单元格
 * @param index
 * @param field
 * @returns
 */
function onClickCell(index, field){
	$('#menuGrid').datagrid('selectRow', index)
			.datagrid('editCell', {index:index,field:field});
}

/**
 * 保存单元格信息
 * @returns
 */
function saveCell(actionid, groupid){
	//alert(actionid +", " + groupid);
	$.ajax({
		type: "post",
		url: "/manageSysS4/actions/editActionMenu.do",
		async: false,
		data: {'actionid': actionid, 'menugroup':groupid},
		dataType: "json",
		success: function(data) {
			$('#menuGrid').datagrid('reload'); 
		},
		error: function() {
			$.messager.alert(msgOption(150, 300, 'warning', '菜单保存失败!'));
		}
	});
}
