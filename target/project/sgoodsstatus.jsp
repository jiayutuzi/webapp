<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.entity.Goods"%>
<%@ page import="com.dao.GoodsDao"%>
<html>
<head>
    <title>销售状态</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css"/>
</head>
<body>
	<div class="header">
		<h1>销售状态</h1>   
		<div class="login">
		<form action="GoodsServlet" method="post">
			<p id="user">未登录</p>
			<a href="Login.jsp" name="login">切换账号</a>
			<a href="register.jsp" name="register">注册</a>
			<a href="LogoutServlet">注销</a>
			<br>
			<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=manage_goods'"value="商品管理"/>
			<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=status'" value="销售状况"/><br>
			<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=showbuylog'" value="客户日志"/>
		</form>
		</div>	
	</div>
	<table class = "class" >
		<tr>
			<td><a href = "ShowServlet?action=status">全部</a></td>
			<td><a href = "ShowServlet?action=status_class&class=sport">运动</a></td>
			<td><a href = "ShowServlet?action=status_class&class=life">生活用品</a></td>
			<td><a href = "ShowServlet?action=status_class&class=food">食物</a></td>
			<td><a href = "ShowServlet?action=status_class&class=electronic">电子产品</a></td>
			<td><a href = "ShowServlet?action=status_class&class=cloth">衣服鞋子</a></td>
		</tr>
	</table>
	<br>
		<table class="Status">
		<tr>
			<th>图片</th>
			<th>商品</th>
			<th>商品id</th>
			<th>类别</th>
			<th>库存</th>
			<th>已售</th>
		</tr>
        <%
            List<Goods> list=( List<Goods> )request.getAttribute("goods");
            if(list!=null){
                for(Goods p:list){
        %>
		<tr>
			<td><img src="img/<%=p.Getimg()%>"></td>
			<td><%=p.Getname()%></td>
			<td><%=p.Getid()%></td>
			<td><%=p.Getclass()%></td>
			<td><%=p.Getnumber()%></td>
			<td><%=p.Getsell()%></td>
		</tr>
        <%
                }
            }
        %>
	</table>
	<div style="float:right;margin-top:20px;margin-bottom:50px;text-align:right;font-size:30px;">
		销售业绩：￥<strong style="color:#ff6600"><%=request.getAttribute("price")%>元</strong>
	</div>
</body>
<script>
    window.onload=function()
    {
        var username ="当前用户："+'<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
	    user.innerHTML=username;
    }
</script>
