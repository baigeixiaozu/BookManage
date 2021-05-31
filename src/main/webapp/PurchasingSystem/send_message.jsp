<%--
  Created by IntelliJ IDEA.
  User: Surface
  Date: 2021/5/31
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send info</title>
</head>
<body>
<form action="http://localhost:8080/BookManage_war_exploded/purchaseServlet" method="post">
内容<input type="text" name="content" value=""/>
</br>
收取方<input type="checkbox" name="receiver" value="管理员">管理员
<input type="checkbox" name="receiver" value="采购员">采购员
</br>
发送方<input type="checkbox" name="sender" value="管理员">管理员
<input type="checkbox" name="sender" value="采购员">采购员
</br>
<input type="submit" value="发送">
<input type="reset" value="重置">
</form>
</body>
</html>
