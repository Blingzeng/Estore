<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkNull(){
		if(document.getElementsByName("adderss")[0].value==""){
			alert("地址不能为空")
			return false;
		}
	}
	
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/servlet/OrderServlet" method="get">
		收货地址：<textarea rows="2" cols="40" name="adderss" ></textarea><br>
		<input type="submit" value="提交" onclick="return checkNull()">
		
	</form>
	
	<c:if test="${not empty sessionScope.cartmap }">
		<table align="center" width="100%" border="1" style="text-align: center;">
		<tr>
			
			<th>商品名称</th>
			<th>商品单价</th>
			<th>商品类别</th>
			<th>购买数量</th>
			<th>商品库存</th>
			<th>支付金额</th>
			
		</tr>
		<c:set var="money" value="0"></c:set>
		<c:forEach items="${sessionScope.cartmap }" var="entry">
			<tr>
				<td>${entry.key.proName }</td>
				<td>${entry.key.proPrice }</td>
				<td>${entry.key.category }</td>
				<td>${entry.value }</td>
				<td>
					<c:if test="${entry.value>entry.key.proNum }">
						<font color="red">缺货</font>
					</c:if>
					<c:if test="${entry.value<=entry.key.proNum }">
						<font color="blue">有货</font>
					</c:if>
				</td>
				<td>${entry.value*entry.key.proPrice }元</td>
					<c:set var="money" value="${money+entry.value*entry.key.proPrice }"></c:set>
			</tr>
		</c:forEach>
		
	</table><hr>
	<div style="text-align: right;">
		<h3><font color="red">总金额：${money }</font></h3>
	</div>
		
	</c:if>
</body>
</html>