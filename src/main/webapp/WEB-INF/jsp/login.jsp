<%@ page isELIgnored="false"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'/>
    <title>lx</title>
    <link href="<%=request.getContextPath() %>/css/a.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath() %>/img/abc.ico" rel="shotcut icon"/>
</head>
<body>

<div id="content">
    <img src="<%=request.getContextPath() %>/img/jian.png" />
    <h1>lx</h1>
    <form method="get">
        <input  id="usename" type="text" value="用户名"/><br/>
        <input  id="password" type="password" value="........"/>
        <div class="bt clear">
            <input class="check fl" type="checkbox" value=""/>
            <span class="fl">记住密码</span>
            <em class="fl">忘记密码？</em>
            <input id="submit" type="submit" value="登录" />
        </div>
    </form>
</div>
<div id="footer">
    <p><span>lx</span></p>
</div>
</body>
</html>