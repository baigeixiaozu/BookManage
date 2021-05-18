<%@ page import="cn.bookmanage.service.impl.StoreServiceImpl" %>
<%@ page import="cn.bookmanage.service.StoreService" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.bookmanage.entity.StoreRecord" %><%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/18
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%--TODO: 权限检查--%>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="查询入库"/>
</jsp:include>

<%
    String p = request.getParameter("page");
    int pg = p==null?1:Integer.parseInt(p);
    int count = 10;
    StoreService ss = new StoreServiceImpl();
    List<Object> data = ss.queryIn(pg, count);
    List<StoreRecord> records = (List<StoreRecord>)data.get(1);
    int total = (int)data.get(0);
    pageContext.setAttribute("pageCnt", total/count + 1);
    pageContext.setAttribute("records", records);
    pageContext.setAttribute("pg", pg);
%>

<link rel="stylesheet" href="assets/css/github-markdown.css">
<article class="markdown-body">
    <h2>这是入库查询页面</h2>
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
        <c:if test="${pg-1>0}">
            <div>
                <a href="store/queryIn.jsp?page=${pg-1}">上一页</a>
            </div>
        </c:if>
        <c:forEach begin="1" end="${pageCnt}" varStatus="s">
            <div>
                <c:if test="${pg==s.index}">
                    <span>${s.index}</span>
                </c:if>
                <c:if test="${pg!=s.index}">
                    <a href="store/queryIn.jsp?page=${s.index}">${s.index}</a>
                </c:if>
            </div>
        </c:forEach>
        <c:if test="${pg!=pageCnt}">
            <div>
                <a href="store/queryIn.jsp?page=${param.page+1}">下一页</a>
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