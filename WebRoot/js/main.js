

$(function() {
	createActions();
});

/**
 * 鼠标移动在上面改变颜色
 * @param obj
 * @returns
 */
function changeColor(obj) {
	$(obj).css('background-color', '#5F86D3');
}

/**
 * 鼠标移开改变颜色
 * @param obj
 * @returns
 */
function resetColor(obj) {
	$(obj).css('background-color', '#E8F0FF');
}

function createActions() {
	$.ajax({
		type: "post",
		url: "/manageSysS4/user/queryActionsAll.do",
		async: false,
		data: {},
		dataType: "json",
		success: function(data) {
			
			var menuGroupHtml = "";
			//key菜单分栏信息,
			for(var key in data) {
				var actionList = data[key];
				//分栏名称
				var menusname = actionList[0].menuname;
				
				var menuContent = "";
				$.each(actionList, function(i, ele) {
					menuContent += "<div class='subMenu' onclick='pageTo(this);' url='"+ele.url+"' onmouseover='changeColor(this);' onmouseout='resetColor(this);'>"+ele.actionname+"</div>";
				});
				
				$('#menusArea').accordion('add',{
					title: menusname,
					content: menuContent,
					iconCls: 'icon-large-smartart'
				});
			}
		},
		error: function() {
			alert("加载菜单失败");
		}
	});
}

/**
 * 跳转到指定页面
 * @param obj
 * @returns
 */
function pageTo(obj) {
	var url = '/manageSysS4/page/goto.do?url='+ $(obj).attr('url');
	var title = $(obj).text();
	
	//如果tabs没有打开，则打开新的
    if (!$("#pageTabs").tabs('exists', title)) {
        var allTabs = $("#pageTabs").tabs("tabs");
        $("#pageTabs").tabs('add', {
            title: title,
            content:  '<iframe frameborder="0" scrolling="true" src="' + url + '" style="width:100%;height:100%;position:relative;"></iframe>',
            closable: true,
            icon: 'icon-ok'
            //href: url//如果用这种方式加载页面，那么请求是get而不是post
         });
    } 
    //已经打开的，则不用刷新
    else {
        $("#pageTabs").tabs('select', title);
    }
}

















