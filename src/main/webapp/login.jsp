<%@ page import="cn.bookmanage.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/7
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%
    User user = (User)request.getSession().getAttribute("user");
    if(user != null){
        // 已登录
        String redirect = request.getParameter("redirect");
        if(null == redirect)redirect = "user/info.jsp";
        response.sendRedirect(redirect);
    }
%>
<jsp:include page="template/header.jsp" >
    <jsp:param name="title" value="登录"/>
</jsp:include>
    <link rel="stylesheet" href="assets/css/login.css">
    <div id="login-area" name="login-area">
        <div class="left">
            <image src="assets/img/login/loginbanner.png"></image>
        </div>
        <div class="right">
            <form id="uinfo" onsubmit="return false">
                <label for="username">
                    用户名：
                    <div class="input-area">
                        <input id="username" name="username" type="text">
                    </div>
                </label>
                <br>
                <label for="password">
                    密码：
                    <div class="input-area">
                        <input id="password" name="password" type="password">
                    </div>
                </label>
                <br>
                <button onclick="login()" class="login-btn">登录</button>
                <!-- <button type="reset">重置</button> -->
            </form>
        </div>
    </div>
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