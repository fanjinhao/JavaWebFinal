<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
<link href="bootstrap/css/docs.css" rel="stylesheet" />
<style type="text/css">
.welcom{
  padding: 60px;
  margin-bottom: 30px;
  font-size: 18px;
  font-weight: 200;
  line-height: 30px;
  color: #fff;
  background-color: #eeeeee;
  
  background: #a20031 /* Old browsers */
  background: -moz-linear-gradient(45deg,  #a20031 0%, #47CA08 100%); /* FF3.6+ */
  background: -webkit-gradient(linear, left bottom, right top, color-stop(0%,#a20031), color-stop(100%,#6d3353)); /* Chrome,Safari4+ */
  background: -webkit-linear-gradient(45deg,  #a20031 0%, #47CA08 100%); /* Chrome10+,Safari5.1+ */
  background: -o-linear-gradient(45deg,  #a20031 0%, #47CA08 100%); /* Opera 11.10+ */
  background: -ms-linear-gradient(45deg,  #a20031 0%, #47CA08 100%); /* IE10+ */
  background: linear-gradient(45deg,  #a20031 0%, #47CA08 100%); /* W3C */
  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#a20031', endColorstr='#47CA08',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
  -webkit-box-shadow: inset 0 3px 7px rgba(0,0,0,.2), inset 0 -3px 7px rgba(0,0,0,.2);
     -moz-box-shadow: inset 0 3px 7px rgba(0,0,0,.2), inset 0 -3px 7px rgba(0,0,0,.2);
          box-shadow: inset 0 3px 7px rgba(0,0,0,.2), inset 0 -3px 7px rgba(0,0,0,.2);
  
  -webkit-border-radius: 6px;
     -moz-border-radius: 6px;
          border-radius: 6px;
}
</style>
</head>
<body>
<div id="register" class="wrap">
	<div class="shadow">
		<div class="welcom"  style="width: 900px; margin: 100px auto;" align="center">
			<div style="padding-top: 150px;padding-bottom: 150px;">
				<font style="font-size: 70px;"><strong>欢迎注册java1234论坛</strong></font>
			</div>
			<!-- <ul class="steps clearfix">
				<li class="finished"><em></em>填写注册信息</li>
				<li class="last-current"><em></em>注册成功</li>
			</ul> -->
			<div class="msg">
				<p>恭喜：注册成功！</p>
				<p>正在进入首页...</p>
				<script type="text/javascript">
					setTimeout("location.href='index.jsp'", 3000);
				</script>
			</div>
		</div>
	</div>
</div>
</body>
</html>