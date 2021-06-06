<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.entity.User"%>
<%@ page import="com.dao.UserDao"%>
<html>
<head>
    <title>人员管理</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css"/>
</head>
<body>
	<div class="header">
	<h1>人员管理</h1>   
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
    <input class = "button" type = "button" onclick="window.location.href='addseller.jsp'" value = "添加">	
	<table class="table">
		<tr>
			<td>用户名</td>
			<td>密码</td>
			<td>邮箱</td>
			<td>操作</td>
		</tr>
		<%
			List<User> list=(List<User>)request.getAttribute("sellers");
			if(list!=null){
				for(User seller:list){
		%>
		<tr>
			<td><%=seller.Getname()%></td>
			<td><%=seller.Getpassword()%></td>
			<td><%=seller.Getemail()%></td>
			<td>	
				<input class = "button_1" type = "button" onclick="delSeller('<%=seller.Getname()%>');" value = "删除账号">	
				<input class = "button_1" type = "button" onclick="window.location.href='resetpw.jsp?name=<%=seller.Getname()%>'" value = "重置密码">	
			</td>
		</tr>
		<%
				}
			}
		%>
	</table>
</body>
</html>
<script> 
    window.onload=function()
    {
        var username ='<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
        user.innerHTML=username;
        var add='<%=request.getParameter("add")%>';
        if(add=="true")
            alert("添加成功");
		var update='<%=request.getParameter("update")%>';
        if(update=="true")
            alert("重置成功");
		var del='<%=request.getParameter("del")%>';
		if(del=="true")
            alert("删除成功");
    }
</script>
<script>
    function delSeller(name) {
        if(confirm("确定删除该账户？")){
            location.href="ManagerServlet?action=delSeller&name="+name;
        }
    }
</script>
