<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加商品</title>
</head>
<body>
	<h1>添加商品</h1><hr>
	<form action="${pageContext.request.contextPath }/servlet/addProServlet" method="post" enctype="multipart/form-data">
	<table>											 
		<tr>
			<td>商品名称</td>
			<td><input type="text" name="proName"/></td>
		</tr>
		<tr>
			<td>商品价格</td>
			<td><input type="text" name="proPrice"/></td>
		</tr>
		<tr>
			<td>商品种类</td>
			<td>
				<select name="category">
					<option value="家用商品">家用商品</option>
					<option value="家用商品">床上用品</option>
					<option value="数码电器">数码电器</option>
					<option value="小型家具">小型家具</option>
					<option value="化妆品">化妆品</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>商品存量</td>
			<td><input type="text" name="proNum"/></td>
		</tr>
		<tr>
			<td>商品图片</td>
			<td><input type="file" name="proImg"/></td>
		</tr>
		<tr>
			<td>商品描述</td>
			<td>
				<textarea rows="5" cols="35" name="proDec"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="提交"/></td>
		</tr>
	</table>
	</form>
	
</body>
</html>