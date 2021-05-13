<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/7
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${sessionScope.user==null}">
        <%
            response.sendRedirect("../login.jsp");
        %>
    </c:when>
</c:choose>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="个人信息"/>
</jsp:include>

<h2 style="color: red">该页面仅供检测登录是否正常而用，尚无实际功能</h2>

<jsp:include page="../template/footer.jsp" />