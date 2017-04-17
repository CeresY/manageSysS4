

$(function() {
	createDept();
	$.messager.alert(msgOption(150, 300, "info", "点击鼠标右键新增、删除岗位!"));
});

function createDept() {
	var basePath = $("#basePath").val();
	$("#depttree").treegrid({
		url: basePath+'dept/findDeptDetail.do',
		fit: true,
		//lines: true,//显示虚线
		loadFilter: function(data){     
            return data;        
        },//返回要显示的过滤数据
        idField:'id',
        treeField:'name',
        showFooter:true,
        onContextMenu: onContextMenu,//右键事件
        columns: [[
	        {title:'岗位名称',field:'name',width:220},
	        {title:'人数',field:'persons',width:100, align:'center'},
	        {title:'百分比',field:'per',width:250, formatter: per},
	        {title:'上级岗位id',field:'parentid',width:70, hidden:true}
        ]]
	});
}

function per(value){
	if (value){
    	var s = '<div style="width:100%;border:1px solid #ccc">' +
    			'<div style="width:' + value + '%;background:#2E1CD1;color:#fff">' + value + '%' + '</div>'
    			'</div>';
    	return s;
	} else {
    	return '';
	}
}

/**
 * 右键事件
 * @param e
 * @param row
 * @returns
 */
function onContextMenu(e,row){
	if (row){
		e.preventDefault();
		$(this).treegrid('select', row.id);
		$('#menus').menu('show',{
			left: e.pageX,
			top: e.pageY
		});				
	}
}

function append(){
	var node = $('#depttree').treegrid('getSelected');
	$.messager.prompt({
		title: '',
		msg: '请输入岗位名称',
		top: 150,
		left: 300,
		ok: '确定',
		cancel: '取消',
		fn: function(r) {
			if(typeof r != 'undefined' && r != null && r != '') {
				addDept(r, node.id);
			}
		}
	});
}

function removeIt(){
	$.messager.confirm({
		title: '提示',
		msg: '确定要删除岗位吗？删除后不可恢复!',
		ok: '确定',
		cancel: '取消',
		top: 150,
		left: 300,
		fn: function(r) {
			if(r) {
				delDept();
			}
		}
	});
}

/**
 * 删除选中岗位
 * @returns
 */
function delDept() {
	var basePath = $("#basePath").val();
	var node = $('#depttree').treegrid('getSelected');
	var id = node.id;
	var childNodes = $('#depttree').treegrid('getChildren', id);
	
	if(childNodes.length == 0) {
		$.ajax({
	        type: "post",
	        url: basePath+'dept/delDept.do',
	        //async: false,
	        data: {"id":id},
	        dataType: "json",
	        success: function(data) {
	        	if(data.SUCCESS === 'true') {
	        		//$('#depttree').treegrid('remove', id);
	        		$("#depttree").treegrid('reload');
	        		$.messager.alert(msgOption(150, 300, "info", data.MSG));
	        	} else {
	        		$.messager.alert(msgOption(150, 300, "warning", data.MSG));
	        	}
	        },
	        error: function() {
	            $.messager.alert(msgOption(150, 300, "warning", "服务器错误，请联系管理员!"));
	        }
	    });
	} else {
		$.messager.alert(msgOption(150, 300, "info", "该岗位有下属岗位，不可删除!"));
	}
}

function addDept(deptname, parentid) {
	var basePath = $("#basePath").val();
	$.ajax({
        type: "post",
        url: basePath+'dept/addDept.do',
        //async: false,
        data: {"name":deptname, "parentid":parentid},
        dataType: "json",
        success: function(data) {
        	if(data.SUCCESS === 'true') {
        		$("#depttree").treegrid('reload');
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
 * 修改岗位名称
 * @returns
 */
function editIt() {
	var node = $('#depttree').treegrid('getSelected');
	$.messager.prompt({
		title: '',
		msg: '请输入新的岗位名称',
		top: 150,
		left: 300,
		ok: '确定',
		cancel: '取消',
		fn: function(r) {
			if(typeof r != 'undefined' && r != null && r != '') {
				editDept(node.id, r);
			}
		}
	});
}

function editDept(id, name) {
	var basePath = $("#basePath").val();
	$.ajax({
        type: "post",
        url: basePath+'dept/editDept.do',
        //async: false,
        data: {"id":id, "name":name},
        dataType: "json",
        success: function(data) {
        	if(data.SUCCESS === 'true') {
        		$("#depttree").treegrid('reload');
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









