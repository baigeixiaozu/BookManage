
<%@ page import="cn.bookmanage.entity.User" %>
<%@ page import="java.net.URLEncoder" %>
<%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/13
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

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
<c:if test="${sessionScope.user.level!=10&&sessionScope.user.level!=6}">
    <jsp:forward page="../error/403.jsp" />
</c:if>
<%--权限检查END--%>

<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="查询库存"/>
</jsp:include>

<link rel="stylesheet" href="assets/css/github-markdown.css">
<article class="markdown-body">
    <link rel="stylesheet" href="assets/css/font/iconfont.css">
    <style>
        #tableHead{
            cursor: pointer;
        }
        #tableHead i {
            float: right;
            display: none;
        }
    </style>
    <h2>查询全部库存</h2>
    <table>
        <thead>
        <tr id="tableHead">
            <td>序号<i class="iconfont icon-down-copy-copy" style="transform: rotate(180deg);"></i></td>
            <td>书名<i class="iconfont icon-down-copy-copy" style="transform: rotate(180deg);"></i></td>
            <td>作者<i class="iconfont icon-down-copy-copy" style="transform: rotate(180deg);"></i></td>
            <td>出版社<i class="iconfont icon-down-copy-copy" style="transform: rotate(180deg);"></i></td>
            <td>ISBN<i class="iconfont icon-down-copy-copy" style="transform: rotate(180deg);"></i></td>
            <td>价格<i class="iconfont icon-down-copy-copy" style="transform: rotate(180deg);"></i></td>
            <td>数量<i class="iconfont icon-down-copy-copy" style="transform: rotate(180deg);"></i></td>
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