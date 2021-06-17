<%--
  Created by IntelliJ IDEA.
  User: 17237
  Date: 2021/5/20
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>

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
    <div>购买</div>
    <div><a href="${pageContext.request.contextPath}/MarketingSystem/queryBookOrder.jsp">查看订书单</a> </div>
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

</div>
</body>
</html>
<jsp:include page="../template/footer.jsp" />
