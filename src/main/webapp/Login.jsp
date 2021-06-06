<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
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
<form action="RegisterLoginServlet?action=login" method="post">
    <h2>用户登录</h2>
    <table style="margin-left:39%;border:2px solid black;width:22%">
        <tr style = "height:50px;text-align:center" >
            <td>用户名：</td>
            <td><input type="text" size="21" name="username"/></td>
        </tr>
        <tr style = "height:50px;text-align:center">
            <td>密码：</td>
            <td><input type="password" name="password" size="21"/></td>
        </tr>
        <tr style = "height:50px;text-align:center">
            <td>身份：</td>
            <td>
                <input type="radio" name="identity" size="21" value = "root"/> 管理员
                <input type="radio" name="identity" size="21" value = "seller"/> 销售员
                <input type="radio" name="identity" size="21" value = "consumer"/> 普通用户
            </td>
        </tr>
    </table>
    <br>
    <input type="submit"  class="button" value="登录"/>
    <input type="reset" class="button" value="重置"/>
</form>
<input class = "button" type = "button" onclick="window.location.href='register.jsp'" value = "注册">
<input class = "button" type = "button" onclick="window.location.href='ShowServlet?action=showgoods'" value = "商城">	
</div>
</body>
</html>
<script>
    var error = '<%=request.getParameter("error")%>';
    var register = '<%=request.getParameter("register")%>';
    if(error == 'yes')
    {
        confirm("用户名或密码错误！");
        location.href="Login.jsp";
    }
    if(register == "yes")
    {
        confirm("注册成功");
        location.href="Login.jsp";
    }
</script>
