<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1><font color = 'red'>${product.proName }</font></h1>
	<table>
		<tr>
			<td><img alt="商品详细图" src="${pageContext.request.contextPath }/servlet/ImgServlet?path=${product.imgUrl}"></td>
			<td>
				商品单价：${product.proPrice	 }<br><br><br>
				商品库存：${product.proNum	 }<br><br><br>
				商品种类：${product.category	 }<br><br><br>
				商品描述：${product.proDec	 }<br><br><br>
				<a href="${pageContext.request.contextPath}/servlet/AddCartServlet?id=${product.id}">
				<img alt="点击购买" src="${pageContext.request.contextPath }/img/buy.bmp" >
				</a>
			</td>
		</tr>
	</table>
</body>
</html>