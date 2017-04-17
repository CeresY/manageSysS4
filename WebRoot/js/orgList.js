

$(function() {
	createOrg();
	$.messager.alert(msgOption(150, 300, "info", "点击鼠标右键新增、删除机构!"));
});

function createOrg() {
	var basePath = $("#basePath").val();
	$("#orgtree").treegrid({
		url: basePath+'org/findOrg.do',
		fit: true,
		//lines: true,//显示虚线
		loadFilter: function(data){     
            return data;        
        },//返回要显示的过滤数据
        idField:'id',
        treeField:'text',
        onContextMenu: onContextMenu,//右键事件
        columns: [[
	        {title:'机构名称',field:'text',width:220},
	        {title:'上级机构id',field:'parentid',width:70, hidden:true}
        ]]
	});
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
	var node = $('#orgtree').treegrid('getSelected');
	$.messager.prompt({
		title: '',
		msg: '请输入机构名称',
		top: 150,
		left: 300,
		ok: '确定',
		cancel: '取消',
		fn: function(r) {
			if(typeof r != 'undefined' && r != null && r != '') {
				addOrg(r, node.id);
			}
		}
	});

	/*$('#orgtree').treegrid('append',{
		parent: node.id,
		data: [{
			id: idIndex,
			name: 'New Task'+idIndex,
			persons: parseInt(Math.random()*10),
			begin: $.fn.datebox.defaults.formatter(d1),
			end: $.fn.datebox.defaults.formatter(d2),
			progress: parseInt(Math.random()*100)
		}]
	})*/
}

function removeIt(){
	$.messager.confirm({
		title: '提示',
		msg: '确定要删除机构吗？删除后不可恢复!',
		ok: '确定',
		cancel: '取消',
		top: 150,
		left: 300,
		fn: function(r) {
			if(r) {
				delOrg();
			}
		}
	});
}

/**
 * 删除选中机构
 * @returns
 */
function delOrg() {
	var basePath = $("#basePath").val();
	var node = $('#orgtree').treegrid('getSelected');
	var id = node.id;
	var childNodes = $('#orgtree').treegrid('getChildren', id);
	
	if(childNodes.length == 0) {
		$.ajax({
	        type: "post",
	        url: basePath+'org/delOrg.do',
	        //async: false,
	        data: {"id":id},
	        dataType: "json",
	        success: function(data) {
	        	if(data.SUCCESS === 'true') {
	        		$('#orgtree').treegrid('remove', id);
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
		$.messager.alert(msgOption(150, 300, "info", "该机构有下属机构，不可删除!"));
	}
}

function addOrg(orgname, parentid) {
	var basePath = $("#basePath").val();
	$.ajax({
        type: "post",
        url: basePath+'org/addOrg.do',
        //async: false,
        data: {"text":orgname, "parentid":parentid},
        dataType: "json",
        success: function(data) {
        	if(data.SUCCESS === 'true') {
        		$("#orgtree").treegrid('reload');
        		$.messager.alert(msgOption(150, 300, "info", "机构新增成功!"));
        	} else {
        		$.messager.alert(msgOption(150, 300, "warning", "机构新增失败!"));
        	}
        },
        error: function() {
            $.messager.alert(msgOption(150, 300, "warning", "服务器错误，请联系管理员!"));
        }
    });
}

/**
 * 修改机构
 * @returns
 */
function editIt() {
	var node = $('#orgtree').treegrid('getSelected');
	$.messager.prompt({
		title: '',
		msg: '请输入新的机构名称',
		top: 150,
		left: 300,
		ok: '确定',
		cancel: '取消',
		fn: function(r) {
			if(typeof r != 'undefined' && r != null && r != '') {
				editOrg(node.id, r);
			}
		}
	});
}

function editOrg(orgid, orgname) {
	var basePath = $("#basePath").val();
	$.ajax({
        type: "post",
        url: basePath+'org/editOrg.do',
        //async: false,
        data: {"orgid":orgid, "orgname":orgname},
        dataType: "json",
        success: function(data) {
        	if(data.SUCCESS === 'true') {
        		$("#orgtree").treegrid('reload');
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









