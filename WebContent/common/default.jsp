<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="0" style="margin-top: 8;">
		<c:forEach items="${zoneList }" var="zone">
			<tr>
				<td>
					<table style="width: 1200px;" align="center">
						<tr height="30"><td style="text-indent:5;" background="images/index/classT.jpg"><b><font color="white">■ ${zone.name }</font></b></td></tr>
						<tr>
							<td>
								<ul class="unstyled inline" >
									<c:forEach items="${zone.sectionList }" var="section">
										<li style="width: 394px; margin-left: 0px;padding: 0px;">
											<div align="center" style="margin-top: 20px;">
												<div><a href="Topic_list.action?sectionId=${section.id }"><img style="width: 100px;" alt="" src="${pageContext.request.contextPath}/${section.logo }"></a></div>
												<div style="margin: 10px auto;"> <a href="Topic_list.action?sectionId=${section.id }"><font style="font-size: 30px;font-weight: bold;">${section.name }</font></a></div>
												<font style="font-size: 12px;">帖子总数：${sectionTopicCount.get(section) }</font>
												<font style="font-size: 12px;">精华帖子：${sectionGoodTopicCount.get(section) }</font>
												<font style="font-size: 12px;">未回复：${sectionNoReplyTopicCount.get(section) }</font>
												<font style="font-size: 12px;">版主：${section.master.nickName }</font>
											</div>
										</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
						<tr height="25"><td style="text-indent:10" background="images/index/boardE.jpg"><font color="#F9F9F9)">论坛介绍：${zone.description }</font></td></tr>
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>