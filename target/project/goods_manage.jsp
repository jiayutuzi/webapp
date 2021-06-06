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
		<h1>商品管理</h1>   
		<div class="login">
		<form action="GoodsServlet" method="post">
			<p id="user">未登录</p>
			<a href="Login.jsp" name="login">切换账号</a>
			<a href="register.jsp" name="register">注册</a>
			<a href="RegisterLoginServlet?action=logout">注销</a>
			<br>
			<input class="button"  type="button" onclick="window.location.href='addgoods.jsp'"value="增加商品"/>
			<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=status'" value="销售状况"/><br>
			<input class="button"  type="button" onclick="window.location.href='ShowServlet?action=showbuylog'" value="客户日志"/>
		</form>
		</div>	
	</div>
	<table class = "class" >
		<tr>
			<td><a href = "ShowServlet?action=manage_goods">全部</a></td>
			<td><a href = "ShowServlet?action=manage_goods_class&class=sport">运动</a></td>
			<td><a href = "ShowServlet?action=manage_goods_class&class=life">生活用品</a></td>
			<td><a href = "ShowServlet?action=manage_goods_class&class=food">食物</a></td>
			<td><a href = "ShowServlet?action=manage_goods_class&class=electronic">电子产品</a></td>
			<td><a href = "ShowServlet?action=manage_goods_class&class=cloth">衣服鞋子</a></td>
		</tr>
	</table>
	<div class = "mygoods">
        <%
            List<Goods> list=( List<Goods> )request.getAttribute("goods");
            if(list!=null){
                for(Goods p:list){
        %>
    	<div class="mygood">
			<div class = "myimg">
				<img src="img/<%=p.Getimg()%>">
			</div>
			<div class="information">
				<p>商品名：<%=p.Getname()%></p>
				<p>库存：<%=p.Getnumber()%></p>
				<p>id：<%=p.Getid()%></p>
				<p>价格：￥<%=p.Getprice()%></p>
                <p>种类：<%=p.Getclass()%></p>
				<input class = "button_2" type = "button" onclick="update('<%=p.Getid()%>');" value = "修改">	
				<input class = "button_2" type = "button" onclick="delgoods('<%=p.Getid()%>');" value = "删除">	
			</div>
        </div>
            <%
                    }
                }
            %>
	</div>
</body>
</html>
<script>
    window.onload=function()
    {
        var username ='<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
        user.innerHTML=username;
        var op='<%=request.getParameter("op")%>';
        if(op=="delete")
            alert("已删除");
        if(op=="update")
            alert("已修改");
    }
</script>
<script>
    function delgoods(id) {
        if(confirm("确定删除该商品？")){
            location.href="GoodsServlet?action=delete&id="+id;
        }
    }
    function update(id)
    {
        location.href="GoodsServlet?action=gotoupdate&id="+id;
    }
</script>