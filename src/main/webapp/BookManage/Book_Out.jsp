<%@ page import="cn.bookmanage.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/6/16
  Time: 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<c:if test="${sessionScope.user==null}">
    <jsp:forward page="../login.jsp" />
</c:if>
<c:if test="${sessionScope.user.level!=10&&sessionScope.user.level!=6}">
    <jsp:forward page="../error/403.jsp" />
</c:if>

<html>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="管理系统"/>
</jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js"></script>
<script>
    var book={};

    $(document).ready(function (){
        $("#Out").click(function(){
            var name=$("#name").val();
            var isbn=$("#ISBN").val();
            var count=$("#number").val();

            book={"name":name,"author":"","isbn":isbn,"count":count};

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
<div class=book>
    <h1>图书出库</h1>
    <div class=text>
        <table>
            <tr id="title">
                <th>书名</th>
                <th>ISBN</th>
                <th>数量</th>

            </tr>
            <tr>
                <td><input type=text id=name></td>
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
