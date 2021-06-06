<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>重置密码</title>
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
<form action="ManagerServlet?action=resetpw" id="form" method="post">
    <h2>修改密码</h2>
    <table style="margin-left:39%;border:2px solid black;width:22%">
        <tr style = "height:50px;text-align:center">
            <td>用户名：</td>
            <td><input name="name" type="text" size="20" readonly = "readonly" value="<%=request.getParameter("name")%> "></td>
        </tr>
        <tr style = "height:50px;text-align:center">
            <td>新密码：</td>
            <td><input name="npassword" type="text" size="20"></td>
        </tr>
        <tr style = "height:50px;text-align:center">
            <td>确认密码:</td>
            <td><input name="cpassword" type="text" size="20"></td>
        </tr>
    </table>
    <br>
    <input type="submit"  class="button" value="确认"/>
    <input type="reset" class="button" value="重置"/>
</form>
<input class = "button" type = "button" onclick="window.location.href='ShowsServlet?action=manage_goods'" value = "返回">	
</div>
</body>
</html>
<script>
    var error = '<%=request.getParameter("error")%>';
    var name = '<%=request.getParameter("name")%>';
    if(error == "true")
    {
        confirm("两次输入的密码不符!");
        location.href="resetpw.jsp?name="+name;
    }
</script>
