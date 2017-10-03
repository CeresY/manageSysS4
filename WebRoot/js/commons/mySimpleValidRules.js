/**
 * 扩展easyui验证的框架
 */
$.extend($.fn.validatebox.defaults.rules, {
	 equalsMatch: {
		 validator: function(value,param){
			 return value == $(param[0]).val();
		 },
		 message: '两次输入相同'
	 },
	 
	 //只允许为数字母
	 englishCheckSub : {
		validator : function(value) {
			return /^[a-zA-Z0-9]+$/.test(value);
		},
		message : "只能包括英文字母、数字"
	}, 
	
	//计算字符长度，汉字算3个字节
	lenScope: {
		validator : function(value, param) {
			var valLen = value.replace(/[^\x00-\xff]/g, '___').length;
			return valLen >= param[0] && valLen <= param[1];
		},
		message : "请输入最小{0}位字符最大{1}位字符"
	},
	// 字符验证
	stringCheck : {
		validator : function(value) {
			return /^[\u0391-\uFFE5\w]+$/.test(value);
		},
		message : "只能包括中文字、英文字母、数字和下划线"
	},
	//不能为空
	isNotEmpty: {
		validator: function(value) {
			return value.length>0;
		},
		message: '不能为空'
	}
 });