<%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/7
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%
    request.setAttribute("title", "登录");
%>
<jsp:include page="template/header.jsp" />
    <%
        String msg = (String)request.getAttribute("msg");
        if(null != msg){
    %>
        <div style="color: red">${requestScope.msg}</div>
    <%
        }
    %>
    <form action="LoginServlet" method="post">
        <label for="username">
            用户名：<input id="username" name="username" type="text">
        </label>
        <br>
        <label for="password">
            密码：<input id="password" name="password" type="password">
        </label>
        <br>
        <button type="submit">提交</button>
        <button type="reset">重置</button>
    </form>
<jsp:include page="template/footer.jsp" />