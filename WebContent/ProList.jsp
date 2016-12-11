<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>商品列表</h1><hr>
	<table width="100%" style="text-align:center;">
		<c:forEach items="${list }" var="pro">
			<tr>
				<td width="30%">
				<a href="${pageContext.request.contextPath }/servlet/ProInfoServlet?id=${pro.id}">
				<img src="${pageContext.request.contextPath }/servlet/ImgServlet?path=${pro.imgUrls}">
				</a>
				</td>
				<td width="50%">
						商品名称:${pro.proName }
						<br>
						商品单价：${pro.proPrice }
						<br>
						商品种类：${pro.category }
						<br>
						商品描述：${pro.proDec }
					</td>
					<td width="20%">
						<c:if test="${pro.proNum>0}">
							<font color="blue">有货</font>
						</c:if>
						<c:if test="${pro.proNum<=0}">
							<font color="red">缺货</font>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<hr>
					</td>
			</tr>
			
		</c:forEach>
	</table>
	
</body>
</html>