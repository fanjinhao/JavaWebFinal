<%--
  Created by IntelliJ IDEA.
  User: ������
  Date: 2017/6/10
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<jsp:directive.page import="com.wy.form.VoteForm"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <link href="CSS/style.css" type="text/css" rel="stylesheet">
    <title>ǰ̨-��ѯͶƱ���</title>
    <%@ page language="java" import="java.util.*" %>
    <style type="text/css">
        <!--
        body {
            background-image: url(images/bg_01.gif);
        }

        .style1 {
            color: #663403;
            font-weight: bold;
        }

        .style2 {
            color: #663403
        }

        .style3 {
            color: #9a6400
        }

        .style4 {
            color: #cc7800;
            font-size: 10pt;
        }

        .style5 {
            color: #663401
        }

        -->
    </style>
</head>
<jsp:useBean id="voteDao" class="com.wy.dao.VoteDao" scope="page"></jsp:useBean>
<%
    List voteList = voteDao.queryVoteList();

%>

<body>
<!--��ҳͷ����-->
<jsp:include page="head_top.jsp" flush="true"/>

<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td width="74"><img src="images/head_06.jpg" width="74" height="846"></td>
        <td height="846" valign="top" background="images/head_07.jpg" width="407">
            <%out.println("<br><p align=left>&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/icon.gif width=10 height=10>&nbsp;&nbsp;ͶƱ��ѯ���</p>");%>
            <table width="369" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF"
                   bgcolor="FECE62">
                <tr>
                    <td height="30">
                        <div align="center">���</div>
                    </td>
                    <td>
                        <div align="center">ͶƱ����</div>
                    </td>
                    <td>
                        <div align="center">Ʊ��</div>
                    </td>
                </tr>
                <%
                    for (int votei = 0; votei < voteList.size(); votei++) {
                        VoteForm voteForm = (VoteForm) voteList.get(votei);
                %>


                <tr bgcolor="#FFFFFF">
                    <td height="30">
                        <div align="center"><%=voteForm.getId()%>
                        </div>
                    </td>
                    <td>
                        <div align="center"><%=voteForm.getVoteName()%>
                        </div>
                    </td>
                    <td>
                        <div align="center"><%=voteForm.getVoteNumber()%>
                        </div>
                    </td>
                </tr>

                <%} %>
            </table>
        </td>
        <td width="10"><img src="images/head_08.jpg" width="13" height="846"></td>
        <td width="184" valign="top">


            <jsp:include page="head_right.jsp" flush="true"/>


        </td>
        <td width="122"><img src="images/head_10.jpg" width="122" height="846"></td>
    </tr>
</table>
<!--��ҳβ����-->
<jsp:include page="head_down.jsp" flush="true"/>


</body>
</html>