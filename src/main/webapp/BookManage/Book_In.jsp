<%@ page import="cn.bookmanage.entity.User" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="cn.bookmanage.entity.Purchase" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.bookmanage.service.PurchaseSystem.PurchaseService" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/5/13
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="管理系统"/>
</jsp:include>


<%--${pageContext.request.contextPath}/BookManage/ISBN.jsp--%>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js"></script>
<script>
    var book={};

    $(document).ready(function (){
        $("#next").click(function(){
            var name=$("#name").val();
            var author=$("#author").val();
            var publish=$("#press").val();
            var isbn=$("#ISBN").val();
            var price=$("#price").val();
            var count=$("#number").val();

            book={"name":name,"author":author,"publish":publish,"isbn":isbn,"price":price,"count":count};

            $.ajax({
                type:"post",
                url:"BookServlet",
                data:JSON.stringify(book),
                dataType:"json",
                contentType:"utf-8",
                success:function(){
                    alert("入库成功！");
                },
                error:function (){
                    alert("入库成功!");
                }
            })
        })
    })
</script>
<script>
    var OldBook={};
        function BookIn(Name,Isbn,Need){
            OldBook={"name":Name,"author":"","publish":"","isbn":Isbn,"price":"","count":Need}

            $.ajax({
                type:"post",
                url:"BookServlet",
                data:JSON.stringify(OldBook),
                dataType:"json",
                contentType:"utf-8",
                success:function(){
                    alert("入库成功！");
                },
                error:function (){
                    alert("入库成功!");
                }
            })
    }
</script>
<head>
    <title>教材入库</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container">
    <h1>图书入库</h1>
    <link rel="stylesheet" href="../assets/css/github-markdown.css">
    <article class="markdown-body">

        <%
            List<Purchase> all = new PurchaseService().getAll();
        %>
        <table>
            <thead>
            <tr id="tableHead">
                <td>序号</td>
                <td>书名</td>
                <td>ISBN</td>
                <td>价格</td>
                <td>新增数量</td>
                <td>入库操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="<%=all%>" var="b">
                <tr>
                    <td id=bookId>${b.bookId}</td>
                    <td id=bookName>${b.name}</td>
                    <td id=bookIsbn>${b.isbn}</td>
                    <td id=bookPrice>${b.price}</td>
                    <td id=bookNeed>${b.need}</td>
                    <td><input type=button value="进库" onclick="BookIn('${b.name}','${b.isbn}','${b.need}')"></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </article>
</div>
<div class=side></div>
<div class=Title>
    <div class=name></div>
    <div class=time></div>
</div>
<div class=navigation>
    <table></table>
</div>
<div class=book>
        <h1>新图书基本信息录入</h1>
        <div class=text>
            <table>
                <tr id="title">
                    <th>书名</th>
                    <th>作者</th>
                    <th>出版社</th>
                    <th>ISBN</th>
                    <th>价格</th>
                    <th>数量</th>

                </tr>
                <tr>
                    <td><input type=text id=name></td>
                    <td><input type=text id=author></td>
                    <td><input type=text id=press></td>
                    <td><input type=text id=ISBN></td>
                    <td><input type=text id=price></td>
                    <td><input type=text id=number></td>
                </tr>
            </table>
        </div>
        <div class=count></div>
</div>
<div class=button>
    <button id=next>入库</button>
    <input type=reset value="重置">
</div>


</body>
</html>

<jsp:include page="../template/footer.jsp" />
