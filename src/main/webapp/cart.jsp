<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.entity.Cart"%>
<%@ page import="com.dao.CartDao"%>
<html>
<head>
    <title>购物车</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css"/>
</head>
<body>
	<div class="header">
	<h1>购物车</h1>   
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
	<table class="Cart">
		<tr>
			<th>图片</th>
			<th>商品</th>
			<th>数量</th>
			<th>小计</th>
			<th>操作</th>
		</tr>
        <%
            List<Cart> list=( List<Cart> )request.getAttribute("cart");
            String username=(String)request.getAttribute("username");
            if(list!=null){
                for(Cart p:list){
        %>
		<tr>
			<td><img src="img/<%=p.Getimg()%>"></td>
			<td><%=p.Getname()%></td>
			<td><%=p.Getnumber()%></td>
			<td><%=p.Getprice()%></td>
			<td><a href="javaScript:del(<%=p.Getid()%>);">删除</a></td>
		</tr>
        <%
                }
            }
        %>
	</table>
	<div style="float:right;margin-top:20px;text-align:right;">
		商品金额：￥<strong style="color:#ff6600"><%=request.getAttribute("price")%>元</strong>
		<br>
		<br>
		<button class="Cartbtn" onclick="empty('<%=username%>')">清空购物车</button>
		<button class="Cartbtn" onclick="purchase('<%=username%>')">结算</button>
	</div>
</body>
</html>
<script>
    window.onload=function()
    {
        var username ='<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
		if(username!=null)
	        user.innerHTML=username;
		else
			user.innerHTML="未登录";
        var op='<%=request.getParameter("op")%>';
        if(op=="purchase")
            alert("已结算");
    }
</script>
<script>
    function del(id) {
        if(confirm("确定删除该商品？")){
            location.href="CartServlet?action=del&id="+id;
        }
    }
    function empty(user)
    {
        if(confirm("确定清空购物车？")){
            location.href="CartServlet?action=empty&user="+user;
        }
    }
    function purchase(user)
    {
        if(confirm("确定结算？")){
            location.href="CartServlet?action=purchase&user="+user;
        }
    }
</script>