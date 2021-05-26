<%--
  Created by IntelliJ IDEA.
  User: 17237
  Date: 2021/5/20
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.bookmanage.service.impl.StoreServiceImpl" %>
<%@ page import="cn.bookmanage.service.StoreService" %>
<%@ page import="cn.bookmanage.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="cn.bookmanage.entity.User" %>
<%@ page import="java.net.URLEncoder" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%
    User user = (User) request.getSession().getAttribute("user");
    String req = request.getRequestURI();
    String query = request.getQueryString();
    String redirect = req + (query==null?"":"?"+query);

    // URL编码,处理含参地址
    redirect = URLEncoder.encode(redirect, "UTF-8");

    if(user == null){
        response.sendRedirect("../error/401.jsp?redirect=" + redirect);
        return;
    }
%>--%>
<%--<c:if test="${sessionScope.user.level!=10}">
    <jsp:forward page="../error/403.jsp" />
</c:if>--%>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="销售系统"/>
</jsp:include>

<html>
<head>
    <title>This is Marketing System</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js" charset="UTF-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/MarketingSystem.css">
    <script src="${pageContext.request.contextPath}/assets/js/MarketingSystem.js" charset="UTF-8"></script>
    <script src="${pageContext.request.contextPath}/assets/js/MarketingSystemAjax.js" charset="UTF-8"></script>
</head>
<body>
<% response.setCharacterEncoding("UTF-8");%>
<% request.setCharacterEncoding("UTF-8");%>

<h1>Welcome to MarketingSystem!</h1>

<div class="box">
    <div>查询</div>
    <div>发布通知</div>
    <div>查看通知</div>
    <div>提交订书单</div>
</div>
<div class="box1">
    <div class="item">
        <ul>
            <li>
                <div class="search"><a href="${pageContext.request.contextPath}/MarketingSystem/searchByName.jsp">按书名查询</a> </div>
                <div class="search"><a href="${pageContext.request.contextPath}/MarketingSystem/searchByAuthor.jsp">按作者查询</a> </div>
                <div class="search"><a href="${pageContext.request.contextPath}/MarketingSystem/searchByISBN.jsp">按ISBN查询</a> </div>
            </li>
        </ul>
    </div>
    <div class="item">
        请输入要提交的消息<input type="text">
    </div>
    <div class="item">消息栏</div>
    <div class="item">
        <ul id="books">
            <li >JSP实用教程（第4版）</li><input type="text" value="0">
            <li >计算机组成原理微课版</li><input type="text" value="0">
            <li >软件工程：实践者的研究方法（原书第8版）（本科教学版）</li><input type="text" value="0">
            <li >web前端设计基础——html5、css3、java(二版)</li><input type="text" value="0">
            <li >新目标大学英语系列教材：科技英语教程学生用书</li><input type="text" value="0">
            <li >JSP实用教程（第4版）</li><input type="text" value="0">
            <li >概率论与数理统计及其应用（第2版）</li><input type="text" value="0">
       </ul>
        <br>
        <input type="button" value="提交" id="btn">
    </div>
</div>
</body></body>
</html>
