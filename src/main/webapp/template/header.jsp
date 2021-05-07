<%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/7
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>${requestScope.title!=null?requestScope.title:"教材订购系统"} - 教材订购系统</title>
    <link rel="stylesheet" href="assets/css/common.css" />
</head>
<body>
    <header id="header">
        <nav id="header-nav" class="header-nav">
            <ul>
                <li><a href="/">首页</a></li>
                <li><a href="/">test</a></li>
            </ul>
        </nav>
        <div>
            <c:choose>
                <c:when test="${sessionScope.user!=null}">
                    ${sessionScope.user.nickname}
                </c:when>
                <c:otherwise>
                    <a href="../login.jsp">登录</a>
                </c:otherwise>
            </c:choose>
        </div>
    </header>