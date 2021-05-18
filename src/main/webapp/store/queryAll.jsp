<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.bookmanage.service.impl.StoreServiceImpl" %>
<%@ page import="cn.bookmanage.service.StoreService" %>
<%@ page import="cn.bookmanage.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/13
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%--TODO: 权限检查--%>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="查询库存"/>
</jsp:include>

<%
    String p = request.getParameter("page");
    int pg = p==null?1:Integer.parseInt(p);
    int count = 10;
    StoreService ss = new StoreServiceImpl();
    Map<String, Object> data = ss.queryAll(pg, count);
    List<Book> books = (List<Book>)data.get("books");
    int total = (int)data.get("total");
    pageContext.setAttribute("pageCnt", total/count + 1);
    pageContext.setAttribute("books", books);
%>
<link rel="stylesheet" href="assets/css/github-markdown.css">
<article class="markdown-body">
    <h2>这是查询全部库存的页面</h2>
    <table>
        <thead>
        <tr>
            <td>序号</td>
            <td>书名</td>
            <td>作者</td>
            <td>出版社</td>
            <td>ISBN</td>
            <td>价格</td>
            <td>数量</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="book" varStatus="s">
            <tr>
                <td>${s.index+1}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.publish}</td>
                <td>${book.isbn}</td>
                <td>${book.price}</td>
                <td>${book.count}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!--页码跳转-->
    <div class="page-nav">
        <c:if test="${param.page-1>0}">
            <div>
                <a href="store/queryAll.jsp?page=${param.page-1}">上一页</a>
            </div>
        </c:if>
        <c:forEach begin="1" end="${pageCnt}" varStatus="s">
            <div>
                <a href="store/queryAll.jsp?page=${s.index}">${s.index}</a>
            </div>
        </c:forEach>
        <c:if test="${param.page!=pageCnt}">
            <div>
                <a href="store/queryAll.jsp?page=${param.page+1}">下一页</a>
            </div>
        </c:if>
    </div>
    <style>
        .page-nav{
            display: flex;
            justify-content: center;
        }
        .page-nav a{
            padding: 10px;
        }
    </style>
</article>
<jsp:include page="../template/footer.jsp" />