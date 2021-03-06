<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.bookmanage.service.impl.StoreServiceImpl" %>
<%@ page import="cn.bookmanage.service.StoreService" %>
<%@ page import="cn.bookmanage.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="cn.bookmanage.entity.User" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="cn.bookmanage.service.impl.MarketingServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/13
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%--权限检查START--%>
<%--<%--%>
<%--    User user = (User) request.getSession().getAttribute("user");--%>
<%--    String req = request.getRequestURI();--%>
<%--    String query = request.getQueryString();--%>
<%--    String redirect = req + (query==null?"":"?"+query);--%>

<%--    // URL编码,处理含参地址--%>
<%--    redirect = URLEncoder.encode(redirect, "UTF-8");--%>

<%--    if(user == null){--%>
<%--        response.sendRedirect("../error/401.jsp?redirect=" + redirect);--%>
<%--        return;--%>
<%--    }--%>
<%--%>--%>
<%--<c:if test="${sessionScope.user.level!=10}">--%>
<%--    <jsp:forward page="../error/403.jsp" />--%>
<%--</c:if>--%>
<%--权限检查END--%>

<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="书名查询"/>
</jsp:include>

<script type="text/javascript">
    function addCart(id) {
        $.ajax({
            type: "post",
            url: "/BookManage/MarketingServlet",
            data: {"id":id},
            dataType: "text",
            success: function (data) {
                alert("提交成功!"+"\r\n"+data);

            },
            error: function () {
                alert("提交失败,请重试或者检查您的环境");
            }
        })
    }
    function sel() {
        document.thisform.submit();
    }
</script>
<%
    String p = request.getParameter("page");
    int curPage = p==null||p.length()==0?1:Integer.parseInt(p);
    int count = 10;
    MarketingServiceImpl ms = new MarketingServiceImpl();
    String name=request.getParameter("name");
    if(name==null) {
        name = "";
    }
    List<Object> data = ms.queryName(curPage, count, name);
    List<Book> books = (List<Book>)data.get(1);
    int total = (int)data.get(0);
    int pageCnt = total/count + 1;
%>
<link rel="stylesheet" href="assets/css/github-markdown.css">
<article class="markdown-body">
    <form name="thisform" method="get">
        书名：<input type="text" value="<%=name%>" name="name">
        <input type="button" value="查询" onclick="sel()">
    </form>
    <br>
    <table>
        <thead>
        <tr style="border: none">
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
            <tr style="border: none">
                <td>${s.index+1}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.publish}</td>
                <td>${book.isbn}</td>
                <td>${book.price}</td>
                <td>${book.count}</td>
                <td style="background: #F3F7F9;border: none"><button onclick="addCart(${book.id})">购买</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!--页码跳转-->
    <div class="page-nav">
        <c:if test="<%=curPage-1>0%>">
            <div>
                <a href="${pageContext.request.contextPath}/MarketingSystem/searchByName.jsp?page=<%=curPage-1%>&name=<%=name%>">上一页</a>
            </div>
        </c:if>
        <c:forEach begin="1" end="<%=pageCnt%>" varStatus="s">
            <div>
                <c:if test="${param.page==s.index}">
                    <span>${s.index}</span>
                </c:if>
                <c:if test="${param.page!=s.index}">
                    <a href="${pageContext.request.contextPath}/MarketingSystem/searchByName.jsp?page=${s.index}&name=<%=name%>">${s.index}</a>
                </c:if>
            </div>
        </c:forEach>
        <c:if test="<%=(curPage!=pageCnt)%>">
            <div>
                <a href="${pageContext.request.contextPath}/MarketingSystem/searchByName.jsp?page=<%=curPage+1%>&name=<%=name%>">下一页</a>
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