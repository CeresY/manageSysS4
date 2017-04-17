<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统登录</title>
<link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<link href="css/demo.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="plugin/jquery-3.0.0.min.js"></script>

<script>
$(function(){

$(".i-text").focus(function(){
$(this).addClass('h-light');
});

$(".i-text").focusout(function(){
$(this).removeClass('h-light');
});

$("#username").focus(function(){
 var username = $(this).val();
 if(username=='输入账号'){
 $(this).val('');
 }
});

$("#username").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入账号');
 }
});


$("#password").focus(function(){
 var username = $(this).val();
 if(username=='输入密码'){
 $(this).val('');
 }
});


$("#yzm").focus(function(){
 var username = $(this).val();
 if(username=='输入验证码'){
 $(this).val('');
 }
});

$("#yzm").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入验证码');
 }
});



$(".registerform").Validform({
	tiptype:function(msg,o,cssctl){
		var objtip=$(".error-box");
		cssctl(objtip,o.type);
		objtip.text(msg);
	},
	ajaxPost:true
});

	//登陆
	login();
});

//登陆
function login() {
	var form = $("#loginForm");
	form.attr("method","post");
	form.attr("action","/manageSysS4/user/login.do");
	form.submit();
}

</script>


</head>

<body>


<div class="header">
  <h1 class="headerLogo"><a title="后台管理系统"  href="#"><img alt="logo" src="images/logo.gif"></a></h1>
	<div class="headerNav">
		<a target="_blank" href="#">XXX银行官网</a>
		<a target="_blank" href="#">关于XXX银行</a>
		<a target="_blank" href="#">意见反馈</a>
		<a target="_blank" href="#">帮助</a>	
	</div>
</div>

<div class="banner">

<div class="login-aside">
  <div id="o-box-up"></div>
  <div id="o-box-down"  style="table-layout:fixed;">
   <div class="error-box"></div>
   
   <form id="loginForm" class="registerform">
   <div class="fm-item">
	   <label for="logonId" class="form-label">人员管理系统登陆：</label>
	   <input type="text" value="输入账号" maxlength="100" id="username" name="username" class="i-text"  
	   		datatype="s6-18" errormsg="用户名至少6个字符,最多18个字符！"  >    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label">登陆密码：</label>
	   <input type="password" value="" maxlength="100" id="password" name="password" class="i-text" 
	   		datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！">    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item pos-r">
	   <label for="logonId" class="form-label">验证码</label>
	   <input type="text" value="输入验证码" maxlength="100" id="yzm" class="i-text yzm" nullmsg="请输入验证码！" >    
       <div class="ui-form-explain"><img src="img/yzm.jpg" class="yzm-img" /></div>
  </div>
  
  <div class="fm-item" style="margin-top:20px;">
	   <label for="logonId" class="form-label"></label>
		<button type="button" style="height:40px; width:150px;" onclick="login();"><font style="font-weight: bolder;">登&nbsp;陆</font></button>
  </div>
  
  </form>
  
  </div>

</div>

	<div class="bd" style="width: 100%; height: 100%;">
		<ul>
			<li style="background:url(img/theme-pic1.jpg) #CCE1F3 center 0 no-repeat;"></li>
		</ul>
	</div>

	<div class="hd"><ul></ul></div>
</div>

<div class="banner-shadow"></div>

<div class="footer">
   <p>Copyright &copy; 2014.Company name All rights reserved.</p>
</div>
<div style="display:none"></div>
</body>
</html>
