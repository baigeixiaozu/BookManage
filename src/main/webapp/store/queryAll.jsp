<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.bookmanage.service.impl.StoreServiceImpl" %>
<%@ page import="cn.bookmanage.service.StoreService" %>
<%@ page import="cn.bookmanage.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="cn.bookmanage.entity.User" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/13
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%--权限检查START--%>
<%
    User user = (User) request.getSession().getAttribute("user");
    String req = request.getRequestURI();
    String query = request.getQueryString();
    String redirect = req + (query==null?"":"?"+query);

    // URL编码,处理含参地址
    redirect = URLEncoder.encode(redirect, "UTF-8");

    // if(user == null){
    //     response.sendRedirect("../error/401.jsp?redirect=" + redirect);
    //     return;
    // }
%>
<%--<c:if test="${sessionScope.user.level!=10}">--%>
<%--    <jsp:forward page="../error/403.jsp" />--%>
<%--</c:if>--%>
<%--权限检查END--%>

<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="查询库存"/>
</jsp:include>

<link rel="stylesheet" href="assets/css/github-markdown.css">
<article class="markdown-body">
    <h2>这是查询全部库存的页面</h2>
    <table>
        <thead>
        <tr id="tableHead">
            <td>序号</td>
            <td>书名</td>
            <td>作者</td>
            <td>出版社</td>
            <td>ISBN</td>
            <td>价格</td>
            <td>数量</td>
        </tr>
        </thead>
        <tbody id="tbody">
        </tbody>
    </table>

    <!--页码跳转-->
    <div class="page-nav" id="page-nav">
            <div>
                <button id="pre">上一页</button>
            </div>
            <div id="middle">
            </div>
            <div>
                <button id="next">下一页</button>
            </div>
    </div>
    <style>
        .page-nav{
            display: flex;
            justify-content: center;
        }
        .page-nav a, .page-nav span{
            padding: 10px;
        }
    </style>
</article>
<script src="assets/js/store.js"></script>
<script>
    $(document).ready(()=>{
        tableBody.init("All")
    });
</script>
<jsp:include page="../template/footer.jsp" />