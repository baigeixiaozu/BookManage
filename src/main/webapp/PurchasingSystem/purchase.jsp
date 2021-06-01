<%--
  Created by IntelliJ IDEA.
  User: Surface
  Date: 2021/6/1
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/BookManage_war_exploded/orderconfirm" method="post">
  JSP实用教程（第4版）<input type="text" name="jsp" value=""/>
  </br>
  计算机组成原理微课版<input type="text" name="cs" value=""/>
  </br>
  软件工程：实践者的研究方法（原书第8版）（本科教学版）<input type="text" name="se" value=""/>
  </br>
  web前端设计基础——html5、css3、java(二版)<input type="text" name="web" value=""/>
  </br>
  新目标大学英语系列教材：科技英语教程学生用书<input type="text" name="english" value=""/>
  </br>
  概率论与数理统计及其应用（第2版）<input type="text" name="po" value=""/>
  </br>
  </br>
  <input type="submit" value="发送">
  <input type="reset" value="重置">
</form>
</body>
</html>
