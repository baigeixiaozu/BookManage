<%@ page import="cn.bookmanage.service.PurchaseSystem.PurchaseService" %>
<%@ page import="cn.bookmanage.entity.Purchase" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
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

<%--权限检查START--%>
<%
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
%>
<c:if test="${sessionScope.user.level!=10&&sessionScope.user.level!=4}">
  <jsp:forward page="../error/403.jsp" />
</c:if>
<%--权限检查END--%>
<jsp:include page="../template/header.jsp">
  <jsp:param name="title" value="采购系统"/>
</jsp:include>


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