<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.entity.Loginlog"%>
<html>
<head>
    <title>登录日志</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css"/>
</head>
<body>
	<div class="header">
	<h1>登录日志</h1>   
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
	<table class="Cart">
		<tr>
			<th>身份</th>
			<th>用户名</th>
			<th>登录时间</th>
			<th>登出时间</th>
			<th>ip地址</th>
		</tr>
        <%
            List<Loginlog> list=( List<Loginlog> )request.getAttribute("loginlog");
            String username=(String)request.getAttribute("username");
            if(list!=null){
                for(Loginlog p:list){
        %>
		<tr>
			<td><%=p.Getidentity()%></td>
			<td><%=p.Getusername()%></td>
			<td><%=p.Getlogintime()%></td>
			<td><%=p.Getlogouttime()%></td>
			<td><%=p.Getip()%></td>
		</tr>
        <%
                }
            }
        %>
	</table>
</body>
</html>
<script>
    window.onload=function()
    {
        var username ='<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
        user.innerHTML=username;
    }
</script>