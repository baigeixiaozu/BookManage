<%@ page import="cn.bookmanage.service.impl.StoreServiceImpl" %>
<%@ page import="cn.bookmanage.service.StoreService" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.bookmanage.entity.StoreRecord" %>
<%@ page import="cn.bookmanage.entity.User" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/18
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <jsp:param name="title" value="查询出库"/>
</jsp:include>

<%
    String p = request.getParameter("page");
    int curPage = p==null||p.length()==0?1:Integer.parseInt(p);
    int count = 10;
    StoreService ss = new StoreServiceImpl();
    List<Object> data = ss.queryOut(curPage, count);
    List<StoreRecord> records = (List<StoreRecord>)data.get(1);
    int total = (int)data.get(0);
    pageContext.setAttribute("pageCnt", total/count + 1);
    pageContext.setAttribute("records", records);
    pageContext.setAttribute("curPage", curPage);
%>

<link rel="stylesheet" href="assets/css/github-markdown.css">
<article class="markdown-body">
    <h2>这是出库查询页面</h2>
    <table>
        <thead>
        <tr>
            <td>序号</td>
            <td>书名</td>
            <td>入库时间</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record" varStatus="s">
            <tr>
                <td>${s.index}</td>
                <td>${record.name}</td>
                <td>${record.time}</td>
            </tr>

        </c:forEach>
        </tbody>
    </table>

    <!--页码跳转-->
    <div class="page-nav">
        <c:if test="${curPage-1>0}">
            <div>
                <a href="store/queryOut.jsp?page=${curPage-1}">上一页</a>
            </div>
        </c:if>
        <c:forEach begin="1" end="${pageCnt}" varStatus="s">
            <div>
                <c:if test="${curPage==s.index}">
                    <span>${s.index}</span>
                </c:if>
                <c:if test="${curPage!=s.index}">
                    <a href="store/queryOut.jsp?page=${s.index}">${s.index}</a>
                </c:if>
            </div>
        </c:forEach>
        <c:if test="${curPage!=pageCnt}">
            <div>
                <a href="store/queryOut.jsp?page=${param.page==null?2:param.page+1}">下一页</a>
            </div>
        </c:if>
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
<jsp:include page="../template/footer.jsp" />