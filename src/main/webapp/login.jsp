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
    <div style="color: red">${requestScope.msg!=null?requestScope.msg:''}</div>
    <form id="uinfo" onsubmit="return false">
        <label for="username">
            用户名：<input id="username" name="username" type="text">
        </label>
        <br>
        <label for="password">
            密码：<input id="password" name="password" type="password">
        </label>
        <br>
        <button onclick="login()">提交</button>
        <button type="reset">重置</button>
    </form>
    <script>
        function login(){
            Swal.fire({
                title: '请稍等...',
                icon: 'info',
                html:
                    '登录中...',
                showCloseButton: false,
                showCancelButton: false,
                focusConfirm: false
            })
            // 发送 POST 请求
            httpPost('LoginServlet', {
                username: uinfo.username.value,
                password: uinfo.password.value
            })
                .then(function (response) {
                    console.log(response);

                    Swal.fire({
                        title: '登录成功！',
                        icon: 'success',
                        html: '准备跳转至' + response.msg,
                        showCloseButton: false,
                        showCancelButton: false,
                        focusConfirm: false
                    })

                    location.href = response.msg;
                })
                .catch(function (error) {
                    console.log("错误", error);

                    Swal.fire({
                        title: '登录失败！',
                        icon: 'error',
                        html: '<span style="color: red">' + error.msg + '</span>',
                        showCloseButton: false,
                        showCancelButton: false,
                        focusConfirm: false,
                        timer: 5000
                    })
                });
        }
    </script>
<jsp:include page="template/footer.jsp" />