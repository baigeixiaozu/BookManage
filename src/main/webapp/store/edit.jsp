<%@ page import="cn.bookmanage.entity.Book" %>
<%@ page import="cn.bookmanage.dao.StoreDao" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="cn.bookmanage.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/6/17
  Time: 19:53
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
    <jsp:param name="title" value="编辑书籍信息"/>
</jsp:include>

<%
    String bookIdStr = request.getParameter("bookId");

    if(bookIdStr == null) {
        out.print("数据异常！");
        return;
    }
    int bookId = Integer.parseInt(bookIdStr);
    Book book = StoreDao.queryBook(bookId);
%>

<div id="book">
    <form id="info" onsubmit="return false">
        <h1>信息：</h1>
        <label>
            <input name="id" value="<%=bookId%>" hidden />
        </label>
        <label for="name">
            书名：<input id="name" name="name" type="text" value="<%=book.getName()%>">
        </label>
        <br>
        <label for="author">
            作者：<input id="author" name="author" type="text" value="<%=book.getAuthor()%>">
        </label>
        <br>
        <label for="publish">
            出版社：<input id="publish" name="publish" type="text" value="<%=book.getPublish()%>">
        </label>
        <br>
        <label for="isbn">
            ISBN：<input id="isbn" name="isbn" type="text" value="<%=book.getIsbn()%>">
        </label>
        <br>
        <label for="price">
            价格：<input id="price" name="price" type="text" value="<%=book.getPrice()%>">
        </label>
        <br>
        <button onclick="update()">提交</button>
    </form>
</div>

<script>
    function update() {
        httpPost("Api/Store/updateBookInfo", {
            id: info.id.value,
            name: info.name.value,
            author: info.author.value,
            isbn: info.isbn.value,
            publish: info.publish.value,
            price: info.price.value
        }).then(res => {
            console.log(res)
            if(res.code===2000)
                Swal.fire({
                    title: '修改成功！',
                    icon: 'success',
                    // html: '准备跳转至',
                    showCloseButton: false,
                    showCancelButton: false,
                    focusConfirm: false
                })
            else
                Swal.fire({
                    title: '修改失败！',
                    icon: 'error',
                    html: '<span style="color: red">' + res.msg + '</span>',
                    showCloseButton: false,
                    showCancelButton: false,
                    focusConfirm: false,
                    timer: 5000
                })
        }).catch(err=>{
            console.error(err);
            Swal.fire({
                title: '修改失败！',
                icon: 'error',
                html: '<span style="color: red">' + err.msg + '</span>',
                showCloseButton: false,
                showCancelButton: false,
                focusConfirm: false,
                timer: 5000
            })
        })
    }
</script>

<jsp:include page="../template/footer.jsp" />