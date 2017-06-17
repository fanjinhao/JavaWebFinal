<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/bootstrap-responsive.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
</script>
</head>
<body>
<div id="header" class="wrap" style="width: 1200px; margin: 0 auto;">
	<jsp:include page="../common/top.jsp"/>
</div>
<div style="width: 1200px; margin: 0 auto;">
	<h2>个人信息管理</h2>
	<table class="table table-bordered table-striped with-check">
		<tr>
			<th style="text-align: center;vertical-align: middle;">ID</th>
			<th style="text-align: center;vertical-align: middle;">昵称</th>
			<th style="text-align: center;vertical-align: middle;">真实姓名</th>
			<th style="text-align: center;vertical-align: middle;">密码</th>
			<th style="text-align: center;vertical-align: middle;">性别</th>
			<th style="text-align: center;vertical-align: middle;">头像</th>
			<th style="text-align: center;vertical-align: middle;">邮箱</th>
			<th style="text-align: center;vertical-align: middle;">联系电话</th>
			<th style="text-align: center;vertical-align: middle;">注册时间</th>
			<th style="text-align: center;vertical-align: middle;">用户类型</th>
			<th style="text-align: center;vertical-align: middle;">操作</th>
		</tr>
		<tr>
			<td style="text-align: center;vertical-align: middle;">${currentUser.id }</td>
			<td style="text-align: center;vertical-align: middle;">${currentUser.nickName }</td>
			<td style="text-align: center;vertical-align: middle;">${currentUser.trueName }</td>
			<td style="text-align: center;vertical-align: middle;">${currentUser.password }</td>
			<td style="text-align: center;vertical-align: middle;">${currentUser.sex }</td>
			<td style="text-align: center;vertical-align: middle;">
				<c:choose>
						<c:when test="${(currentUser.face==null||currentUser.face=='')&&currentUser.sex=='男'}">
							<img alt=""
								src="${pageContext.request.contextPath}/images/user/user0.gif"
								style="width: 100px; height: 100px;">
						</c:when>
						<c:when test="${(currentUser.face==null||currentUser.face=='')&&currentUser.sex=='女'}">
							<img alt=""
								src="${pageContext.request.contextPath}/images/user/female.gif"
								style="width: 100px; height: 100px;">
						</c:when>
						<c:otherwise>
							<img alt="" src="${pageContext.request.contextPath}/${currentUser.face}"
								style="width: 100px; height: 100px;">
						</c:otherwise>
					</c:choose>
				</td>
			<td style="text-align: center;vertical-align: middle;">${currentUser.email }</td>
			<td style="text-align: center;vertical-align: middle;">${currentUser.mobile }</td>
			<td style="text-align: center;vertical-align: middle;">${currentUser.regTime }</td>
			<td style="text-align: center;vertical-align: middle;">
				<c:choose>
						<c:when test="${currentUser.sectionList.size()==0&&currentUser.type!=2 }">
							<font style="color: black;">普通用户</font>
						</c:when>
						<c:when test="${currentUser.sectionList.size()!=0&&currentUser.type!=2 }">
							<font style="color: blue;">版主</font>
					                       【<c:forEach items="${currentUser.sectionList }" var="section">
					            ${section.name }；
					         </c:forEach>】
					    </c:when>
						<c:otherwise>
							<font style="color: red;">管理员</font>
						</c:otherwise>
					</c:choose>
				</td>
				<td style="text-align:center; vertical-align: middle;">
					<a class="btn btn-info" type="button" href="User_preSave.action">修改</a>&nbsp;&nbsp;
				</td>
		</tr>
	</table>
</div>
<div id="footer" style="width: 1200px; margin: 0 auto;">
	<jsp:include page="../common/footer.jsp"/>
</div>
</body>
</html>