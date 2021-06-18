<%@ page import="cn.bookmanage.entity.User" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="cn.bookmanage.utils.JNDIUtils" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="cn.bookmanage.entity.BookBuy" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.bookmanage.service.BookSystem.BookBuyService" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/6/16
  Time: 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

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

<html>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="管理系统"/>
</jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js"></script>
<script>
    var book={};

    $(document).ready(function (){
        $("#Out").click(function(){
            var isbn=$("#ISBN").val();
            var count=$("#number").val();

            book={"name":"","author":"","isbn":isbn,"count":count};

            $.ajax({
                type:"post",
                url:"BookServlet",
                data:JSON.stringify(book),
                dataType:"json",
                contentType:"utf-8",
                success:function(){
                    alert("出库成功！");
                },
                error:function (){
                    alert("出库成功!");
                }
            })
        })
    })
</script>

<head>
    <title>教材出库</title>
</head>

<body>
<h1>图书出库</h1>
<%
    List<BookBuy> book=new BookBuyService().getCount();
%>
<script>
    var bookNeeded={};
    function BookOut(){
/*
        bookNeeded={"id":${b.bookId},"name":"","author":"","publish":"","isbn":"","price":"","count":${b.count}}
        $.ajax({
            type:"post",
            url:"BookServlet",
            data:JSON.stringify(bookNeeded),
            dataType:"json",
            contentType:"utf-8",
        })*/
     /* $.ajax({
          type:"post",
          url:"BookServlet",
          data:"true",
          dataType:"text",
          contentType: "utf-8"
      })*/
        $.ajax({
            type:"post",
            url:"BookQuickOutServlet",
            data:""
        })
        alert("出库成功");
    }
</script>
<div id=table>
    <h4>当前订单</h4>
    <link rel="stylesheet" href="../assets/css/github-markdown.css">
    <article class="markdown-body">
        <table>
            <thead>
            <tr id="tableHead">
                <td>图书编号</td>
                <td>所需出库数量</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="<%=book%>" var="b">
                <tr>
                    <td>${b.bookId}</td>
                    <td>${b.count}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </article>
</div>
<button onclick="BookOut()">一键出库</button>
<div class=book>
   <h1>详细信息出库</h1>
    <div class=text>
        <table>
            <tr id="title">
                <th>ISBN</th>
                <th>数量</th>

            </tr>
            <tr>
                <td><input type=text id=ISBN></td>
                <td><input type=text id=number></td>
            </tr>
        </table>
    </div>
    <div class=count></div>
</div>

<div class=button>
    <button id=Out>出库</button>
    <input type=reset value="重置">
</div>
</body>
</html>
<jsp:include page="../template/footer.jsp" />
