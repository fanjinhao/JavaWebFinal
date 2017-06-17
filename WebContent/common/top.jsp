<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" />
<script type="text/javascript">
function logout() {
	if (confirm("您确定要退出系统吗？")) {
		window.location.href="User_logout.action";
	}
}
function login(){
	var curPage=window.location.href;
	window.location.href="login.jsp?prePage="+curPage;
}
function checkUserLogin(){
	if ('${currentUser.nickName}'==null||'${currentUser.nickName}'=="") {
		alert("您还未登陆！");
	} else {
		window.location.href="User_userCenter.action";
	}
}
</script>
</head>
<body>
<div>
	<div id="header-wrapper">
		<div id="header" class="container">
			<div id="logo">
				<h1><a href="#">JavaWeb论坛</a></h1>
				<p> <a href="http://www.fayne.cn/" rel="nofollow">JavaWeb</a></p>
			</div>
		</div>
	</div>
	<div id="menu-wrapper">
		<div id="menu" class="container">
			<ul>
				<li class="current_page_item"><a href="index.jsp">首　页</a></li>
				<li><a href="#">申请版主</a></li>
				<li><a href="#">在线文档</a></li>
				<li><a href="#">站点帮助</a></li>
				<li><a href="#">资源下载</a></li>
			</ul>
		</div>
		<!-- end #menu --> 
	</div>
<div style="margin: 0 auto;" align="right">
		<c:choose>
			<c:when test="${not empty currentUser }">
				当前用户：<a href="#">${currentUser.nickName }</a>&nbsp;『<c:choose>
					                  	  		<c:when test="${currentUser.sectionList.size()==0&&currentUser.type!=2 }">
					                  	  			<font style="color: black;">普通用户</font>	
					                  	  		</c:when>
					                  	  		<c:when test="${currentUser.sectionList.size()!=0&&currentUser.type!=2 }">
					                  	  			<font style="color: blue;">版主</font>
					                  	  		</c:when>
					                  	  		<c:otherwise>
					                  	  			<font style="color: red;">管理员</font>
					                  	  		</c:otherwise>
					                  	  	</c:choose>』|
				<a href="javascript:logout()">注销</a>|
				<a href="register.jsp">注册</a>|
				<a href="javascript:checkUserLogin()">个人中心</a>
			</c:when>
			<c:otherwise>
				<!-- <a href="login.jsp">登录</a>| -->
				<a href="javascript:login()">登录</a>|
				<a href="register.jsp">注册</a>|
				<a href="javascript:checkUserLogin()">个人中心</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</body>
</html>