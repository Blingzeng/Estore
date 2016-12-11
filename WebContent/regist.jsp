<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function upurl(img){
		img.src="${pageContext.request.contextPath }/servlet/valificationServlet?time="+new Date().getTime();
	}
	function checkData(){
		var flag = true;
		//不为空校验
		flag = checkNull("userName","用户名")&&flag;
		flag = checkNull("password","密码")&&flag;
		flag = checkNull("password_again","确认密码")&&flag;
		flag = checkNull("nickName","昵称")&&flag;
		flag = checkNull("email","邮箱")&&flag;
		flag = checkNull("valification","验证码")&&flag;
		//密码不一致校验
		var pas = document.getElementsByName("password")[0].value;
		var pas_again = document.getElementsByName("password_again")[0].value;
		if(pas!=pas_again){
			document.getElementById("password_again_msg").innerHTML="<font color='red'>密码不一致</font>";
			flag=false;
		}
		
		//邮箱格式校验
		var email = document.getElementsByName("email")[0].value;
		if(!/^\w+@\w+(\.[a-zA-Z0-9]+)+$/.test(email)){
			document.getElementById("email_msg").innerHTML="<font color='red'>邮箱格式不正确</font>";
			flag=false;
		}
		
		return flag;
	}
	function checkNull(name,msg){
		document.getElementById(name+"_msg").innerHTML="";
		 var tempName =document.getElementsByName(name)[0].value;
		 if(tempName==""){
			 document.getElementById(name+"_msg").innerHTML="<font color='red'>"+msg+"不能为空</font>";
			 return false;
		 }
		 return true;
	}
	
</script>
</head>
<body>
<h1>欢迎注册</h1>
<h4><font color='red'>${msg  }</font></h4>
<form action="${pageContext.request.contextPath }/servlet/registServlet" method="post" onsubmit="return checkData();">
	<table>
		<tr>
			<td>用户名</td>
			<td><input type="text" name="userName" value=${userName } /></td>
			<td id="userName_msg"></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="password" name="password" /></td>
			<td id="password_msg"></td>
		</tr>
		<tr>
			<td>确认密码</td>
			<td><input type="password" name="password_again"/></td>
			<td id="password_again_msg"></td>
		</tr>
		<tr>
			<td>昵称</td>
			<td><input type="text" name="nickName" value=${nickName } /></td>
			<td id="nickName_msg"></td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input type="text" name="email" value=${email } /></td>
			<td id="email_msg"></td>
		</tr>
		<tr>
			<td>验证码</td>
			<td><input type="text" name="valification"/></td>
			<td id="valification_msg"></td>
		</tr>
		<tr>
			<td><input type="submit" value="提交"/></td>
			<td><img alt="验证码" src="${pageContext.request.contextPath }/servlet/valificationServlet" style="cursor: pointer;"
			     onclick="upurl(this)"/></td>
		</tr>
	</table>

</form>
</body>
</html>