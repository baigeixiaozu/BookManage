<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/5/13
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教材入库</title>
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
    <div class=oldBook>
        <div class=text>
            <table>
                <tr id="title">
                    <th colspan=2>书名</th>
                    <th colspan=2>库存</th>
                    <th colspan=2>新增数量</th>
                    <th colspan=2>新增后库存</th>
                </tr>
                <tr>
                    <td>JSP实用教程（第4版）</td>
                    <td>10</td>
                    <td><input type=text></td>
                    <td></td>
                </tr>
                <tr>
                    <td>计算机组成原理微课版</td>
                    <td>0</td>
                    <td><input type=text> </td>
                    <td></td>
                </tr>
                <tr>
                    <td>软件工程：实践者的研究方法（原书第8版）（本科教学版）</td>
                    <td>0</td>
                    <td><input type=text></td>
                    <td></td>
                </tr>
                <tr>
                    <td>web前端设计基础——html5、css3、java（二版）</td>
                    <td>0</td>
                    <td><input type=text></td>
                    <td></td>
                </tr>
                <tr>
                    <td>新目标大学英语系列教材：科技英语教程学生用书</td>
                    <td>0</td>
                    <td><input type=text></td>
                    <td></td>
                </tr>
                <tr>
                    <td>概率论与数理统计及其应用（第2版）</td>
                    <td>0</td>
                    <td><input type=text></td>
                    <td></td>
                </tr>
            </table>
        </div>
        <div class=count></div>
    </div>
    <div class=newBook>
        <table>
            <tr>
                <th>新增书名</th>
                <th>数量</th>
            </tr>
            <tr>
                <td><input type=text></td>
                <td><input type=text></td>
            </tr>
        </table>
    </div>
</div>
<div class=button>
    <input type=submit value="提交">
    <input type=reset value="重置">
</div>
</body>
</html>
