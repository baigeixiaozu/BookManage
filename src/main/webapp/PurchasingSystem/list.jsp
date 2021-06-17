<%@ page import="cn.bookmanage.service.PurchaseSystem.PurchaseService" %>
<%@ page import="cn.bookmanage.entity.Purchase" %>
<%@ page import="java.util.List" %>
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
<c:if test="${sessionScope.user==null}">
  <jsp:forward page="../login.jsp" />
</c:if>
<jsp:include page="../template/header.jsp">
  <jsp:param name="title" value="采购系统"/>
</jsp:include>

<c:if test="${sessionScope.user.level!=10&&sessionScope.user.level!=4}">
  <jsp:forward page="../error/403.jsp" />
</c:if>

<div class="container">
  <link rel="stylesheet" href="assets/css/github-markdown.css">
  <article class="markdown-body">

    <%
      List<Purchase> all = new PurchaseService().getAll();
    %>
    <table>
      <thead>
      <tr id="tableHead">
        <td>序号</td><td>书名</td><td>ISBN</td><td>价格</td><td>缺少数量</td>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="<%=all%>" var="b">
        <tr>
          <td>${b.bookId}</td>
          <td>${b.name}</td>
          <td>${b.isbn}</td>
          <td>${b.price}</td>
          <td>${b.need}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </article>
</div>