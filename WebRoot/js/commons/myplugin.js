
/**
 * 格式化日期函数
 * @returns
 */
function formatterDate() {
    var ts = arguments[0] || 0;
    ts = ts.substring(0,10);
    var t, y, m, d, h, i, s;
    t = ts ? new Date(ts * 1000) : new Date();
    y = t.getFullYear();
    m = t.getMonth() + 1;
    d = t.getDate();
    h = t.getHours();
    i = t.getMinutes();
    s = t.getSeconds();
    // 可根据需要在这里定义时间格式  
    return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);
}

/**
 * 扩展jquery.messager.alert的提示框
 * @param icon 可选为error、question、info、warning
 * @returns
 */
function msgOption(top, left, icon, content) {
	var msgtitle = "<div class='messager-icon messager-"+icon+"'></div>"+content;
	return {
		title: '提示',
		top: top,
		left: left,
		content: msgtitle,
		ok: '确定'
	};
}

