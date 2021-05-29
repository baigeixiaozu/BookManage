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

    if(user == null){
        response.sendRedirect("../error/401.jsp?redirect=" + redirect);
        return;
    }
%>
<c:if test="${sessionScope.user.level!=10}">
    <jsp:forward page="../error/403.jsp" />
</c:if>
<%--权限检查END--%>

<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="查询库存"/>
</jsp:include>

<%
    String p = request.getParameter("page");
    int curPage = p==null||p.length()==0?1:Integer.parseInt(p);
    int count = 10;
    StoreService ss = new StoreServiceImpl();
    Map<String, Object> data = ss.queryAll(curPage, count);
    List<Book> books = (List<Book>)data.get("books");
    int total = (int)data.get("total");
    int pageCnt = total/count + 1;
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
        <c:forEach items="<%=books%>" var="book" varStatus="s">
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
        <c:if test="<%=curPage-1>0%>">
            <div>
                <a href="store/queryAll.jsp?page=<%=curPage-1%>">上一页</a>
            </div>
        </c:if>
        <c:forEach begin="1" end="<%=pageCnt%>" varStatus="s">
            <div>
                <c:if test="${(param.page==null&&s.index==1)||param.page==s.index}">
                    <span>${s.index}</span>
                </c:if>
                <c:if test="${(param.page==null&&s.index!=1)||(param.page!=null&&param.page!=s.index)}">
                    <a href="store/queryAll.jsp?page=${s.index}">${s.index}</a>
                </c:if>
            </div>
        </c:forEach>
        <c:if test="<%=(curPage!=pageCnt)%>">
            <div>
                <a href="store/queryAll.jsp?page=<%=curPage+1%>">下一页</a>
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