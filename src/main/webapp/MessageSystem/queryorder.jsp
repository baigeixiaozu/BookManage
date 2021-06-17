<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page import="cn.bookmanage.entity.User" %>
<%@ page import="cn.bookmanage.entity.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.bookmanage.service.impl.MsgServiceImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Surface
  Date: 2021/5/27
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${sessionScope.user==null}">
    <jsp:forward page="../error/401.jsp" />
</c:if>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="我的消息"/>
</jsp:include>

<% response.setCharacterEncoding("UTF-8");%>
<% request.setCharacterEncoding("UTF-8");%>
<%
    User user=(User)request.getSession().getAttribute("user");
    List<Message> messages = new MsgServiceImpl().get(user.getLevel());
%>
<link rel="stylesheet" href="assets/css/github-markdown.css">
<article class="markdown-body">
    <table>
        <thead>
        <tr>
            <td>序号</td>
            <td>内容</td>
            <td>发送者</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="<%=messages%>" var="samp" varStatus="s">
            <tr>
                <td>${samp.id}</td>
                <td>${samp.content}</td>
                <td>${samp.senderName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</article>
</body>
</html>
<jsp:include page="../template/footer.jsp" />
