<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
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
<div style="text-align:center;margin-top:200px">
<form action="GoodsServlet?action=add" id="form" enctype="multipart/form-data" method="post">
    <h2>添加商品</h2>
    <table style="margin-left:39%;border:2px solid black;width:25%">
        <tr style = "height:50px;text-align:center" >
            <td>选择图片：</td>
            <td><input type="file" size="21" name="img" id="img"/></td>
        </tr>
        <tr style = "height:50px;text-align:center" >
            <td>商品名：</td>
            <td><input type="text" size="21" name="name"/></td>
        </tr>
        <tr style = "height:50px;text-align:center" >
            <td>价格：</td>
            <td><input type="text" size="21" name="price"/></td>
        </tr>
        <tr style = "height:50px;text-align:center">
            <td>数量：</td>
            <td><input type="text" name="number" size="21"/></td>
        </tr>
        <tr style = "height:50px;text-align:center">
            <td>种类：</td>
            <td>
                <input type="radio" name="class" size="21" checked = "checked" value = "sport"/> 运动
                <input type="radio" name="class" size="21" value = "life"/> 生活用品
                <input type="radio" name="class" size="21" value = "food"/> 食物 
				<br>
				<input type="radio" name="class" size="21" value = "electronic"/> 电子产品
				<input type="radio" name="class" size="21" value = "cloth"/> 衣服鞋子
            </td>
        </tr>
    </table>
    <br>
    <input type="button"  onclick = "Submit()" class="button" value="添加"/>
    <input type="reset" class="button" value="重置"/>
</form>
<input class = "button" type = "button" onclick="window.location.href='ShowServlet?action=manage_goods'" value = "返回">	
</div>
</body>
</html>
<script>
    function Submit()
    {
        var img = document.getElementById("img").value.length;
        var name=document.getElementsByName("name")[0].value;
        var number=document.getElementsByName("number")[0].value;
        var price=document.getElementsByName("price")[0].value;
		if(img=='0' || name == "" || number==""||price=="")
			alert("请输入完整信息");
		else
		{
            var form=document.getElementById("form");
            form.submit();
        }
    }
</script>
