<%@ page import="cn.bookmanage.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Surface
  Date: 2021/5/31
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<%
    User user=(User)request.getSession().getAttribute("user");
    if(user==null)
        response.sendRedirect("denied.jsp");
    else if(user.getLevel()==1)
        response.sendRedirect("denied.jsp");
%>
<div class="container">
    <form action="http://localhost:8080/BookManage_war_exploded/purchaseServlet" method="post">
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
