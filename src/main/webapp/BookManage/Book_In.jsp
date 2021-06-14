<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/5/13
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
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
            var press=$("#press").val();
            var ISBN=$("#ISBN").val();
            var price=$("#price").val();
            var number=$("#number").val();

            book={"name":name,"author":author,"publish":press,"isbn":ISBN,"price":price,"count":number};

            $.ajax({
                type:"post",
                url:"BookServlet",
                data:JSON.stringify(book),
                dataType:"json",
                contentType:"application/json",
            })
        })
    })
</script>

<head>
    <title>教材入库</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class=side></div>
<div class=Title>
    <div class=name></div>
    <div class=time></div>
</div>
<div class=navigation>
    <table></table>
</div>
<div class=book>
        <h1>图书基本信息录入</h1>
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
    <button id=next>下一步</button>
    <input type=reset value="重置">
</div>


</body>
</html>

