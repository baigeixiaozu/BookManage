<%--
  Created by IntelliJ IDEA.
  User: Surface
  Date: 2021/6/3
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <style>
    @import url("https://fonts.googleapis.com/css2?family=Raleway:wght@100&display=swap");

    h1 {
      text-align: center;
      color: white;
      text-transform: uppercase;
      padding: 1px;
      font-family: "Raleway", cursive;
      font-weight: 100;
      position: relative;
      width: 100%;
      background: linear-gradient(to right, black, #eee, black);
      white-space: nowrap;
    }
    h1::before {
      content: "";
      position: absolute;
      left: 50%;
      top: -50px;
      width: 600px;
      margin-left: -300px;
      margin-top: -220px;
      height: 600px;
      background: radial-gradient(ellipse closest-side, #444, black);
      z-index: -1;
    }
    h1 a {
      background: black;
      display: block;
      padding: 20px;
      text-decoration: none;
      letter-spacing: 30px;
      color: white;
      animation: comein 1.5s 1 ease-in-out forwards;
    }

    @keyframes comein {
      0% {
        letter-spacing: 80px;
        color: rgba(255, 255, 255, 0);
      }
      70% {
        letter-spacing: 20px;
      }
      100% {
        letter-spacing: 25px;
        color: rgba(255, 255, 255, 1);
      }
    }

    body {
      background: black;
      display: grid;
      place-items: center;
      height: 100vh;
      margin: 0;
      overflow: hidden;
    }



    Resources
  </style>
</head>
<body>
<h1><a href="#0">access denied!</a></h1>
</body>
</html>