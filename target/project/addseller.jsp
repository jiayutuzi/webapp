<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div style="text-align:center;margin-top: 200px">
<form action="ManagerServlet?action=addseller" method="post">
    <h2>增加销售账户</h2>
    <table style="margin-left:39%;border:2px solid black;width:22%">
        <tr style = "height:50px">
            <td>用户名:</td>
            <td><input name="name" type="text" size="20"></td>
        </tr>
        <tr style = "height:50px">
            <td>密码：</td>
            <td><input name="password" type="password" size="20"></td>
        </tr>
        <tr style = "height:50px">
            <td>邮箱:</td>
            <td><input name="email" type="text" size="20"></td>
        </tr>
    </table>
    <br>
    <input type="submit"  class="button" value="添加"/>
    <input type="reset" class="button" value="重置"/>
</form>
<input class = "button" type = "button" onclick="window.location.href='ManagerServlet?action=sellers'" value = "返回">	
</div>
</body>
</html>
<script>
    var exist = '<%=request.getParameter("exist")%>';
    if(exist == "true")
    {
        confirm("用户名已存在!");
        location.href="addseller.jsp";
    }
</script>
