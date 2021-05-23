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
<html>
<head>
    <title>This is Marketing System</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/MarketingSystem.css">
    <script src="${pageContext.request.contextPath}/assets/js/MarketingSystem.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/MarketingSystemAjax.js"></script>
</head>
<body>
<% response.setCharacterEncoding("UTF-8");%>
<% request.setCharacterEncoding("UTF-8");%>
<%  response.setContentType("application/text; charset=utf-8");%>
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
                <div>按书名查询</div>
                <div>按作者查询</div>
                <div>按ISBN查询</div>
            </li>
        </ul>
    </div>
    <div class="item">
        请输入要提交的消息<input type="text">
    </div>
    <div class="item">消息栏</div>
    <div class="item">
        <select id="bookName">
            <option value="JSP实用教程（第4版）">JSP实用教程（第4版）</option>
            <option value="计算机组成原理微课版">计算机组成原理微课版</option>
            <option value="软件工程：实践者的研究方法（原书第8版）（本科教学版）">软件工程：实践者的研究方法（原书第8版）（本科教学版）</option>
            <option value="web前端设计基础——html5、css3、java(二版)">web前端设计基础——html5、css3、java(二版)</option>
            <option value="新目标大学英语系列教材：科技英语教程学生用书">新目标大学英语系列教材：科技英语教程学生用书</option>
            <option value="概率论与数理统计及其应用（第2版）">概率论与数理统计及其应用（第2版）</option>
        </select>数量：<input type="text" id="bookCount">&nbsp;<input type="button" value="提交" id="btn">
    </div>
</div>
</body></body>
</html>
