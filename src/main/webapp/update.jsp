<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.entity.Goods"%>
<%@ page import="com.dao.GoodsDao"%>
<html>
<head>
    <title>增加销售</title>
    <style type="text/css">
        body{
            background-position: center;
            background:url(img/background.jpg);
            background-repeat: no-repeat;
            background-size: cover;
        }
        .button{
			width: 120px;
			height: 30px;
			color:white;
			background-color:cornflowerblue;
			border-radius: 3px;
			border-width: 0;
			margin: 10px;
			outline: none;
			font-family: KaiTi;
			font-size: 17px;
			text-align: center;
		}
    </style>
</head>
<body>
<%
    Goods goods=(Goods)request.getAttribute("information");
%>
<div style="text-align:center;margin-top: 200px">
<form action="GoodsServlet?action=update" id="form" method="post">
    <h2>修改商品信息</h2>
    <table style="margin-left:39%;border:2px solid black;width:22%">
        <tr style = "height:50px;text-align:center">
            <td>商品id: </td>
            <td><input name="id" type="text" size="20" readonly = "readonly" value="<%=goods.Getid()%>"></td>
        </tr>
        <tr style = "height:50px;text-align:center">
            <td>商品名称：</td>
            <td><input name="name" type="text" size="20" value="<%=goods.Getname()%>"></td>
        </tr>
        <tr style = "height:50px;text-align:center">
            <td>库存:</td>
            <td><input name="number" type="text" size="20" value="<%=goods.Getnumber()%>"></td>
        </tr>
		<tr style = "height:50px;text-align:center">
            <td>价格￥:</td>
            <td><input name="price" type="text" size="20" value="<%=goods.Getprice()%>"></td>
        </tr>
    </table>
    <br>
    <input type="button"  onclick = "Submit()" class="button" value="确认"/>
    <input type="reset" class="button" value="重置"/>
</form>
<input class = "button" type = "button" onclick="window.location.href='ShowsServlet?action=manage_goods'" value = "返回">	
</div>
</body>
</html>
<script>
    function Submit()
    {
        var name=document.getElementsByName("name")[0].value;
        var number=document.getElementsByName("number")[0].value;
        var price=document.getElementsByName("price")[0].value;
		if(name == "" || number==""||price=="")
			alert("请输入完整信息");
		else
		{
            var form=document.getElementById("form");
            form.submit();
        }
    }
</script>