<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
<script type="text/javascript">
	//如果记住用户名出现中文
	window.onload=function (){
		document.getElementsByName("userName")[0].value=decodeURI("${cookie.remname.value }")
	}
	
</script>
</head>
<body>
	<h1 align="center"> 登 陆 </h1>
	<h4 align="center"><font color="red">${ msg }</font></h4>
	<form action="${pageContext.request.contextPath }/servlet/loginServlet" method="post">
	<table align="center">
		<tr>
			<td>账号</td>
			<td><input type="text" name="userName"  /></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="checkbox" name="remname" value="true"
				<c:if test="${cookie.remname!=null }">checked=</c:if>
			/>记住用户名</td>
			
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="提交" /></td>
		</tr>
	</table>
	</form>
</body>
</html>