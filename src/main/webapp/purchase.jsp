<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.entity.Goods"%>
<%@ page import="com.dao.GoodsDao"%>
<%@ page import="com.entity.Log"%>
<%@ page import="com.dao.LogDao"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<html>
<head>
    <title>电子商城</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css"/>
	<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body onbeforeunload="return leave()">
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
<%
    Goods goods=(Goods)request.getAttribute("information");
%>
<form action="CartServlet?action=add" method="post" id="form">
	<div class="purchase">
		<div class="purchase_img">
			<img src="img/<%=goods.Getimg()%>">
		</div>
		<div class="purchase_name"> <%=goods.Getname()%></div><input type="hidden" name="name" value="<%=goods.Getname()%>">
		<div class="purchase_price"> 价格: <%=goods.Getprice()%></div><input type="hidden" name="price" value="<%=goods.Getprice()%>">
		<div class="purchase_provider"> 店家: <%=goods.Getprovider()%></div><input type="hidden" name="provider" value="<%=goods.Getprovider()%>">
		<div class="purchase_number"> 库存: <%=goods.Getnumber()%></div>
		<div class="purchase_id"> 商品id: <%=goods.Getid()%></div><input type="hidden" name="id" value="<%=goods.Getid()%>">
		<div class="purchase_choose"><button type="button" onclick="sub()">-</button><input id = "num" name = "number" value = "1"><button type="button" onclick="add()">+</button></div>
		<div class="purchase_button">
			<input class = "button_1" onclick="Submit()"  value = "加入购物车">
		</div>
	</div>
</form>
	<h1>推荐栏<h1>
	<div class="goods">
        <%
            List<Goods> clist=( List<Goods> )request.getAttribute("recommand");
            if(clist!=null){
                for(Goods p:clist){
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
<script type="text/javascript" language="javascript">
    window.onload=function()
    {
		var op = '<%=request.getParameter("op")%>';
		if(op=="add")
		{
			confirm("已加入购物车");
			location.href="ShowServlet?action=purchase&id=<%=goods.Getid()%>";
		}
		var username ='<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
		if(username!="未登录")
	        user.innerHTML= username;
		else
			user.innerHTML="未登录";
		user=document.getElementById("user");
		if(user.innerHTML!="未登录")
		{
			<%
				Date now=new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String id=dateFormat.format(now);
				HttpSession hs = request.getSession();
                hs.setAttribute("logid", id);
				long starttime = now.getTime();
				hs.setAttribute("starttime",starttime);
			%>
		}
	}   
	function sendMsg() {
		$.ajax({
			url: "AjaxServlet",
			type: "get",
			async: false,
			data: {
				"id": "<%=goods.Getid()%>"
			}
		});
		sleep(3000);
		console.log(":");
	}
	function send(){
		$.post("AjaxServlet",{
			goodsid: "<%=goods.Getid()%>"
		})
	}
	function leave()
	{
		var user=document.getElementById("user");
		if(user.innerHTML!="未登录")
		{
			send();
		}
		return undefined;
	}
</script>
<script>
	function add()
	{
		var input = document.getElementById("num");
        var num="<%=goods.Getnumber()%>";
        num=parseInt(num);
        if(input.value<num) 
    		input.value=parseInt(input.value)+1;
	}
	function sub()
	{
		var input = document.getElementById("num");
		var value=parseInt(input.value);
		if(value>0)
		{
			input.value=parseInt(input.value)-1;
		}
	}
	function cart()
	{
		var user=document.getElementById("user");
		if(user.innerHTML=="未登录")
			alert("请先登录！");
		else
			location.href="ShowServlet?action=showcart";
	}
	function Submit()
	{
		var user=document.getElementById("user");
		if(user.innerHTML=="未登录")
			alert("请先登录！");
		else
		{
			var form=document.getElementById("form");
			form.submit();
		}
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
</html>
