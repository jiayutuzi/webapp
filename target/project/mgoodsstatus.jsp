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
	<table class = "class" >
		<tr>
			<td><a href = "ShowServlet?action=allstatus">全部</a></td>
			<td><a href = "ShowServlet?action=allstatus_class&class=sport">运动</a></td>
			<td><a href = "ShowServlet?action=allstatus_class&class=life">生活用品</a></td>
			<td><a href = "ShowServlet?action=allstatus_class&class=food">食物</a></td>
			<td><a href = "ShowServlet?action=allstatus_class&class=electronic">电子产品</a></td>
			<td><a href = "ShowServlet?action=allstatus_class&class=cloth">衣服鞋子</a></td>
		</tr>
	</table>
	<br>
	<form action="ShowServlet?action=search" method="post">
		<table style="float:right;width:30%">
			<tr>
				<td><input type="text" size="21" placeholder = "输入查询用户名。。。" name="search"/></td>
				<td><input class="button" type="submit" name="search" value="查询"/></td>
			</tr>
		</table>
	</form>
	<table class="allStatus">
		<tr>
			<th>图片</th>
			<th>商品</th>
			<th>商品id</th>
			<th>类别</th>
			<th>销售者</th>
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
			<td><%=p.Getprovider()%></td>
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
        var username ='<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
	    user.innerHTML=username;
    }
</script>
