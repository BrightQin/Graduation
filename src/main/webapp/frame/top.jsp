<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style type="text/css">
        .div1 {
            margin: auto;
            font-size: 14px;
            color: white
        }
    </style>
</head>
<body bgcolor="#0099FF">
<div class="div1">
    欢迎您: ${sessionScope.username}
</div>
</body>
</html>