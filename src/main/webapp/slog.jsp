<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.entity.Log"%>
<html>
<head>
    <title>客户日志</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css"/>
</head>
<body>
	<div class="header">
	<h1>客户日志</h1>   
	<div class="login">
	<form action="GoodsServlet" method="post">
		<p id="user">未登录</p>
		<a href="Login.jsp" name="login">切换账号</a>
		<a href="register.jsp" name="register">注册</a>
		<a href="RegisterLoginServlet?action=logout">注销</a>
		<br>
		<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=manage_goods'"value="商品管理"/>
		<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=status'" value="销售状况"/><br>
		<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=showbuylog'" value="客户日志"/>
	</form>
	</div>
    </div>
	<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=showbrowlog'" value="浏览记录"/>
	<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=showbuylog'" value="购买记录"/>
	<%
		List<Log> list=( List<Log> )request.getAttribute("log");
		String op = request.getParameter("op");
	%>
	<%
		if(op.equals("buy"))
		{	
	%>
	<h1 style="text-align:center;">用户购买记录</h1>
	<%}else{%>
	<h1 style="text-align:center;">用户浏览记录</h1><%}%>
	<table class="log">
		<%
			if(op.equals("buy"))
		{	
		%>
			<tr>
				<th>用户</th>
				<th>商品id</th>
				<th>商品名</th>
				<th>时间</th>
				<th>类别</th>
				<th>数量</th>
				<th>单价</th>
			</tr>
		<%}else{%>
			<tr>
				<th>用户</th>
				<th>商品id</th>
				<th>商品名</th>
				<th>类别</th>
				<th>时间</th>
				<th>浏览时长(秒)</th>
			</tr>
		<%}%>
		<%
		if(op.equals("buy")){
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
		<%
			}}}else{
			if(list!=null){
				for(Log p:list){
		%>
		<tr>
			<td><%=p.Getuser()%></td>
			<td><%=p.Getid()%></td>
			<td><%=p.Getname()%></td>
			<td><%=p.Getclass()%></td>
			<td><%=p.Gettime()%></td>
			<td><%=p.Getnumber()%></td>
		</tr>
		<%
			}}}
		%>
	</table>

</body>
</html>
<script>
    window.onload=function()
    {
        var username ="当前用户："+'<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
	    user.innerHTML=username;
    }
</script>
