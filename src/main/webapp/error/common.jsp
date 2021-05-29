<%--
  通用错误模板
  Created by IntelliJ IDEA.
  User: jiyec
  Date: 2021/5/18
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../template/header.jsp">
    <jsp:param name="title" value="${param.title}"/>
</jsp:include>

<article>
    <style>
        .Code {
            top:0;
            text-align: center;
        }
        #Code> :first-child {
            position: relative;
            /*top:130px;*/
            font-family: cursive;
            font-size: 100px;
            font-weight: bold;
            line-height: 100px;
            letter-spacing: 5px;
            color: #fff;
        }
        #Code> :first-child {
            cursor: pointer;
            text-shadow: 0px 0px 2px #686868, 0px 1px 1px #ddd, 0px 2px 1px #d6d6d6, 0px 3px 1px #ccc, 0px 4px 1px #c5c5c5, 0px 5px 1px #c1c1c1, 0px 6px 1px #bbb, 0px 7px 1px #777, 0px 8px 3px rgba(100, 100, 100, 0.4), 0px 9px 5px rgba(100, 100, 100, 0.1), 0px 10px 7px rgba(100, 100, 100, 0.15), 0px 11px 9px rgba(100, 100, 100, 0.2), 0px 12px 11px rgba(100, 100, 100, 0.25), 0px 13px 15px rgba(100, 100, 100, 0.3);
            -webkit-transition: all .1s linear;
            transition: all .1s linear;
        }
        #Code> :first-child:hover {
            text-shadow: 0px 0px 2px #686868, 0px 1px 1px #fff, 0px 2px 1px #fff, 0px 3px 1px #fff, 0px 4px 1px #fff, 0px 5px 1px #fff, 0px 6px 1px #fff, 0px 7px 1px #777, 0px 8px 3px #fff, 0px 9px 5px #fff, 0px 10px 7px #fff, 0px 11px 9px #fff, 0px 12px 11px #fff, 0px 13px 15px #fff;
            -webkit-transition: all .1s linear;
            transition: all .1s linear;
        }
        #Code> :not(:first-child) {
            text-align: center;
            color: #666;
            font-family: cursive;
            font-size: 20px;
            text-shadow: 0 1px 0 #fff;
            letter-spacing: 1px;
            line-height: 2em;
        }
    </style>

    <div class="Code">
        <div id="Code">
            <span>${param.title}</span>
            <br>
            <p>
                ${param.msg}
            </p>
            <p>
                <span id="countdown">3</span>秒后跳转至首页
            </p>
        </div>
    </div>
    <br />
    <script>
        let countdown = 0;
        let target = 3;
        setInterval(()=>{
            document.getElementById("countdown").innerText = (target - countdown).toString();
            if(countdown++ === target)
                location.href = "index.jsp";
        }, 1000);
    </script>
</article>

<jsp:include page="../template/footer.jsp" />