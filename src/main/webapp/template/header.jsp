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
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="assets/css/common.css" />
    <script src="assets/js/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header id="header">
        <nav id="header-nav" class="header-nav">
            <ul>
                <li><a id="index" href="index.jsp">首页</a></li>
                <li class="menu-list">
                    <span id="store">库存</span>
                    <ul class="menu-list-sub">
                        <li>
                            <a href="store/queryAll.jsp" >全部</a>
                        </li>
                        <li>
                            <a href="store/queryIn.jsp" >入库</a>
                        </li>
                        <li>
                            <a href="store/queryOut.jsp" >出库</a>
                        </li>
                    </ul>
                </li>
                <li class="menu-list">
                    <span id="example">例子菜单</span>
                    <ul class="menu-list-sub">
                        <li>
                            <a href="" >子项1</a>
                        </li>
                        <li>
                            <a href="" >子项2</a>
                        </li>
                        <li>
                            <a href="" >子项3</a>
                        </li>
                        <li>
                            <a href="MarketingSystem/queryMarketingSystem.jsp">销售系统</a>
                        </li>

                    </ul>
                </li>
                <li class="menu-list">
                    <span id="message">库存</span>
                    <ul class="menu-list-sub">
                        <li>
                            <a href="PurchasingSystem/queryorder.jsp" >查看消息</a>
                        </li>
                        <li>
                            <a href="" >发消息</a>
                        </li>
                    </ul>
                </li>
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
            console.log(path);
            if(path.includes("index.jsp"))
                document.getElementById("index").classList.add("active")
            else if(path.includes("store"))
                document.getElementById("store").classList.add("active")
            else if(path.includes("user/info.jsp"))
                document.getElementById("user-info-link").classList.add("active")
            else
                document.getElementById("index").classList.add("active")
        </script>
    </header>
    <section class="main-body"></section>