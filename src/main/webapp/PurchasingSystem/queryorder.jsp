<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.bookmanage.service.impl.StoreServiceImpl" %>
<%@ page import="cn.bookmanage.service.StoreService" %>
<%@ page import="cn.bookmanage.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="cn.bookmanage.entity.User" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="cn.bookmanage.dao.MessageDao" %>
<%@ page import="cn.bookmanage.service.purchasing.PurchasingServices" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.bookmanage.entity.info" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Surface
  Date: 2021/5/27
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="采购系统"/>
</jsp:include>

<html>
<head>
    <title>This is Marketing System</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js" charset="UTF-8"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/purchase.css">
    <script src="${pageContext.request.contextPath}/assets/js/MarketingSystem.js" charset="UTF-8"></script>
    <script src="${pageContext.request.contextPath}/assets/js/MarketingSystemAjax.js" charset="UTF-8"></script>
</head>
<body>
<% response.setCharacterEncoding("UTF-8");%>
<% request.setCharacterEncoding("UTF-8");%>
<%

    PurchasingServices ps=new PurchasingServices();
    ArrayList<info> sample= ps.fetch();
    if(sample==null)
        sample=ps.fetch_i();//如果此时purchase里面的数据被删除了
    ps.store(sample);
    for(int i=0;i<sample.size();i++){
        out.print(sample.get(i).getContent()+"<br/>");}
    if(sample.size()==0)
        out.print("当前无采购任务");
%>

</body>
</html>
