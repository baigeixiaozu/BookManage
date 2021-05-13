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
    <title>${param.title!=null?param.title:"教材订购系统"} - 教材订购系统</title>
    <base href="/BookManage/">
    <link rel="stylesheet" href="assets/css/common.css" />
</head>
<body>
    <header id="header">
        <nav id="header-nav" class="header-nav">
            <ul>
                <li><a id="index" href="index.jsp">首页</a></li>
                <li><a href="">test</a></li>
            </ul>
        </nav>
        <div class="right">
            <c:choose>
                <c:when test="${sessionScope.user!=null}">
                    <a id="user-info-link" href="user/info.jsp">${sessionScope.user.nickname}</a> |
                    <a onclick="USER.logout()" style="cursor: pointer;">注销</a>
                </c:when>
                <c:otherwise>
                    <a href="login.jsp">登录</a>
                </c:otherwise>
            </c:choose>
        </div>
        <script>
            let path = location.pathname;
            if(path.includes("index.jsp"))
                document.getElementById("index").classList.add("active")
            else if(path.includes("user/info.jsp"))
                document.getElementById("user-info-link").classList.add("active")
            else
                document.getElementById("index").classList.add("active")
        </script>
    </header>