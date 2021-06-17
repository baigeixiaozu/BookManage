<%--
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
    <form id="message" onsubmit="return false">
        <label for="content">内容</label>
        <input type="text" id="content" name="content" placeholder="content">

        <label for="receiver">接收方</label>
        <select id="receiver" name="receiver">
            <option value="10">超级管理员</option>
            <option value="8">图书管理员</option>
            <option value="6">发行人员</option>
            <option value="4">采购人员</option>
            <option value="0">所有用户</option>
        </select>
        <input type="submit" onclick="sendMsg()" value="提交">
    </form>
</div>

<script>
    function sendMsg() {
        httpPost("Api/Msg/send", {
            receiver: message.receiver.value,
            msg: message.content.value
        }).then(res => {
            console.log(res)
            if(res.code===2000)
                Swal.fire({
                    title: '发送成功！',
                    icon: 'success',
                    // html: '准备跳转至',
                    showCloseButton: false,
                    showCancelButton: false,
                    focusConfirm: false
                })
            else
                Swal.fire({
                    title: '发送失败！',
                    icon: 'error',
                    html: '<span style="color: red">' + res.msg + '</span>',
                    showCloseButton: false,
                    showCancelButton: false,
                    focusConfirm: false,
                    timer: 5000
                })
        }).catch(err=>{
            console.error(err);
            Swal.fire({
                title: '发送失败！',
                icon: 'error',
                html: '<span style="color: red">' + err.msg + '</span>',
                showCloseButton: false,
                showCancelButton: false,
                focusConfirm: false,
                timer: 5000
            })
        })
    }
</script>
<jsp:include page="../template/footer.jsp" />
