<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.entity.Goods"%>
<%@ page import="com.dao.GoodsDao"%>
<html>
<head>
    <title>商品管理</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css"/>
</head>
<body>
	<div class="header">
		<h1>电子商城</h1>   
		<div class="login">
		<form action="GoodsServlet" method="post">
			<p id="user">未登录</p>
			<a href="Login.jsp" name="login">切换账号</a>
			<a href="register.jsp" name="register">注册</a>
			<a href="RegisterLoginServlet?action=logout">注销</a>
			<br>
			<button class="button"  type="button" onclick="window.location.href='ShowServlet?action=showgoods'" >商城</button>
			<button class="button"  type="button" onclick="cart()" >购物车</button><br>
			<button class="button"  type="button" onclick="buylog()">购买记录</button>
			<button class="button"  type="button" onclick="browlog()">访问记录</button>
		</form>
		</div>	
	</div>
	<table class = "class" >
		<tr>
			<td><a href = "ShowServlet?action=showgoods">全部</a></td>
			<td><a href = "ShowServlet?action=showgoods_class&class=sport">运动</a></td>
			<td><a href = "ShowServlet?action=showgoods_class&class=life">生活用品</a></td>
			<td><a href = "ShowServlet?action=showgoods_class&class=food">食物</a></td>
			<td><a href = "ShowServlet?action=showgoods_class&class=electronic">电子产品</a></td>
			<td><a href = "ShowServlet?action=showgoods_class&class=cloth">衣服鞋子</a></td>
		</tr>
	</table>
	<br>
	<div class="goods">
        <%
            List<Goods> list=( List<Goods> )request.getAttribute("goods");
            if(list!=null){
                for(Goods p:list){
        %>
		<div class ="good"> 
			<img src="img/<%=p.Getimg()%>"> 
			<div class="name_price">
				<a href="ShowServlet?action=purchase&id=<%=p.Getid()%>"><%=p.Getname()%></a>
				<p>￥<%=p.Getprice()%></p>
			</div>
		</div>
        <%
                }
            }
        %>
	</div>
</body>
<script>
    window.onload=function()
    {
        var username ='<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
		if(username!="未登录")
	        user.innerHTML=username;
		else
			user.innerHTML="未登录";
    }
</script>
<script>
	function cart()
	{
		var user=document.getElementById("user");
		if(user.innerHTML=="未登录")
			alert("请先登录！");
		else
			location.href="ShowServlet?action=showcart";
	}	
	function buylog()
	{
		var user=document.getElementById("user");
		if(user.innerHTML=="未登录")
			alert("请先登录！");
		else
			location.href="ShowServlet?action=buylog";
	}
	function browlog()
	{
		var user=document.getElementById("user");
		if(user.innerHTML=="未登录")
			alert("请先登录！");
		else
			location.href="ShowServlet?action=browlog";
	}
</script>