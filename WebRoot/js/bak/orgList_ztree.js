
$(function() {
	createPanel();
	loadOrg();
});

var tree_setting = {
		check : {
			/** 复选框* */
			enable : false,
			chkboxType : {
				"Y" : "",
				"N" : ""
			}
		},
		view : {
			showLine : false
//			showIcon : false,
//			expandSpeed : 300
		},
		data : {
			key : {
				name : "orgname"
			},
			simpleData : {
				enable : true,
				idKey : "orgid",
				pIdKey : "parentid",
				rootPId : ""
			}
		},
		callback : {
			onClick : function(e, treeId, treeNode){	/**点击树节点，查询设备的监测类型**/
			}
		}
};

function loadOrg() {
	var basePath = $("#basePath").val();
	$.ajax({
		type: "post",
		url: basePath+'org/findOrg.do',
		async: false,
		//data: {},
		dataType: "json",
		success: function(data) {
			zTree = $.fn.zTree.init($("#orgTree"), tree_setting, data);
			zTree.expandAll(false);
			var node = zTree.getNodeByParam('orgid', data[0].orgid, null);
			if (node != null) {
				zTree.selectNode(node);
				zTree.expandNode(node, true, true, false);
			}
			//$.messager.alert(msgOption(150, 300, 'info', data.MSG));
		},
		error: function() {
			$.messager.alert(msgOption(150, 300, 'warning', '服务器失效!'));
		}
	});
}

/**
 * 创建面板
 * @returns
 */
function createPanel() {
	$('#p').panel({
	    width:1024,
	    height:460,
	    title:'<div style="width:100%;height:40px;margin: 0px;border: 0px;background-color: #ddd;display: block;">vdasvasvasvdasvas</div>',
	    //tools:'#tt'//表头工具栏
	    /*tools:[{
		    iconCls:'icon-add',
		    title: '新增同级机构',
		    handler:function(){alert('new')}
	    },{
		    iconCls:'icon-save',
		    handler:function(){alert('save')}
	    }]*/
	}); 
}
