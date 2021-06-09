<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/7
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${sessionScope.user==null}">
        <%
            response.sendRedirect("../login.jsp");
        %>
    </c:when>
</c:choose>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="个人信息"/>
</jsp:include>
<link rel="stylesheet" href="assets/css/github-markdown.css">
<article class="markdown-body">
    <h2 style="color: red">个人基础信息</h2>
    <table>
        <thead>
        <tr>
            <td>id</td>
            <td>登录名</td>
            <td>昵称</td>
            <td>权限标识</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${sessionScope.user.id}</td>
            <td>${sessionScope.user.login}</td>
            <td>${sessionScope.user.nickname}</td>
            <td>${sessionScope.user.level}</td>
        </tr>
        </tbody>
    </table>
</article>

<jsp:include page="../template/footer.jsp" />