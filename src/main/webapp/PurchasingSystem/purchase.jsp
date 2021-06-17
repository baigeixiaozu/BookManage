<%@ page import="cn.bookmanage.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: Surface
  Date: 2021/6/1
  Time: 15:34
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
  <jsp:param name="title" value="采购系统"/>
</jsp:include>

<c:if test="${sessionScope.user.level!=10&&sessionScope.user.level!=4}">
  <jsp:forward page="../error/403.jsp" />
</c:if>
<div class="container">
  <form action="http://localhost:8080/BookManage_war_exploded/orderconfirm" method="post">
    <label for="jsp">JSP实用教程（第4版）</label>
    <input type="text" id="jsp" name="jsp" placeholder="jsp">

    <label for="cs">计算机组成原理微课版</label>
    <input type="text" id="cs" name="cs" placeholder="cs">
    <label for="se">软件工程：实践者的研究方法（原书第8版）（本科教学版）</label>
    <input type="text" id="se" name="se" placeholder="se">
    <label for="web">web前端设计基础——html5、css3、java(二版)</label>
    <input type="text" id="web" name="web" placeholder="web">
    <label for="english">新目标大学英语系列教材：科技英语教程学生用书</label>
    <input type="text" id="english" name="english" placeholder="english">

    <label for="po">概率论与数理统计及其应用（第2版）</label>
    <input type="text" id="po" name="po" placeholder="po">
    <input type="submit" value="Submit">
  </form>
</div>