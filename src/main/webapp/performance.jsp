<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.entity.Performance"%>
<%@ page import="com.dao.PerDao"%>
<html>
<head>
    <title>销售业绩</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css"/>
</head>
<body>
	<div class="header">
		<h1>销售业绩</h1>   
		<div class="login">
		<form action="GoodsServlet" method="post">
			<p id="user">未登录</p>
			<a href="Login.jsp" name="login">切换账号</a>
			<a href="register.jsp" name="register">注册</a>
		    <a href="RegisterLoginServlet?action=logout">注销</a>
			<br>
            <input class="button"  type="button" onclick="window.location.href='ManagerServlet?action=sellers'"value="人员管理"/>
			<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=allstatus'" value="销售状况"/><br>
            <input class="button"  type="button" onclick="window.location.href='ShowServlet?action=loginlog'" value="登录日志"/>
			<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=showperformance'" value="销售业绩"/>
			<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=showoplog'" value="操作日志"/> 
		</form>
		</div>	
	</div>
	<br>
		<table class="allStatus">
		<tr>
			<th>销售员/种类</th>
			<th>运动</th>
			<th>生活用品</th>
			<th>食物</th>
			<th>电子产品</th>
			<th>衣服鞋子</th>
			<th>总计</th>
		</tr>
        <%
            List<Performance> list=( List<Performance> )request.getAttribute("performance");
            if(list!=null){
                for(Performance p:list){
        %>
		<tr>
			<td><%=p.Getname()%></td>
			<td>￥<%=p.Getsport()%></td>
			<td>￥<%=p.Getlife()%></td>
			<td>￥<%=p.Getfood()%></td>
			<td>￥<%=p.Getelectronic()%></td>
			<td>￥<%=p.Getcloth()%></td>
			<td>￥<%=p.Getall()%></td>
		</tr>
        <%
                }
            }
        %>
	</table>
</body>
<script>
    window.onload=function()
    {
        var username ='<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
	    user.innerHTML=username;
    }
</script>
