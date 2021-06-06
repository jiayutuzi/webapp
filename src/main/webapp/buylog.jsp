<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.entity.Log"%>
<html>
<head>
    <title>购买记录</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css"/>
</head>
<body>
	<div class="header">
	<h1>购买记录</h1>   
	<div class="login">
	<form action="GoodsServlet" method="post">
		<p id="user">未登录</p>
		<a href="Login.jsp" name="login">切换账号</a>
		<a href="register.jsp" name="register">注册</a>
		<a href="RegisterLoginServlet?action=logout">注销</a>
		<br>
		<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=showgoods'"value="返回商城"/>
		<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=showcart'" value="购物车"/><br>
		<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=buylog'" value="购买记录"/>
		<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=browlog'" value="浏览记录"/>         
	</form>
	</div>
    </div>
    <table class="log">
        <tr>
            <th>用户</th>
            <th>商品id</th>
            <th>商品名</th>
            <th>时间</th>
            <th>类别</th>
            <th>数量</th>
            <th>单价</th>
        </tr>
	<%
		List<Log> list=( List<Log> )request.getAttribute("buylog");
        if(list!=null){
				for(Log p:list){
	%>
    <tr>
        <td><%=p.Getuser()%></td>
        <td><%=p.Getid()%></td>
        <td><%=p.Getname()%></td>
        <td><%=p.Gettime()%></td>
        <td><%=p.Getclass()%></td>
        <td><%=p.Getnumber()%></td>
        <td><%=p.Getprice()%></td>
    </tr>
    <%}}%>
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
