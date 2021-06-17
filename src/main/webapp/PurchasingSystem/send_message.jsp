<%@ page import="cn.bookmanage.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Surface
  Date: 2021/5/31
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="管理系统"/>
</jsp:include>
<html>
<head>
    <title>Send info</title>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        * {box-sizing: border-box;}

        input[type=text], select, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
            resize: vertical;
        }

        input[type=submit] {
            background-color: #04AA6D;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .container {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
    </style>
</head>
<body>

<c:if test="${sessionScope.user.level==1}">
    <jsp:forward page="../error/403.jsp" />
</c:if>
<div class="container">
    <form action="purchaseServlet" method="post">
        <label for="content">内容</label>
        <input type="text" id="content" name="content" placeholder="content">

        <label for="receiver">接收方</label>
        <select id="receiver" name="receiver">
            <option name="超级管理员" value="超级管理员">超级管理员</option>
            <option name="采购员" value="采购员">采购员</option>
            <option name="订书员" value="订书员">订书员</option>
        </select>
        <label for="sender">发送方</label>
        <select id="sender" name="sender">
            <option name="超级管理员" value="超级管理员">超级管理员</option>
            <option name="采购员" value="采购员">采购员</option>
            <option name="订书员" value="订书员">订书员</option>
        </select>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
<jsp:include page="../template/footer.jsp" />
