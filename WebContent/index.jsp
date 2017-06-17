<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>JavaWeb论坛-首页</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />

<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div id="header" class="wrap" style="width: 1200px; margin: 0 auto;">
	<jsp:include page="common/top.jsp"/>
</div>
<div id="content" style="width: 1200px; margin: 0 auto;">
	<%-- <jsp:include page="common/default.jsp"></jsp:include> --%>
	<jsp:include page="common/default.jsp"></jsp:include>
</div>
<div id="footer" style="width: 1200px; margin: 0 auto;">
	<jsp:include page="common/footer.jsp"/>
</div>
</body>
</html>