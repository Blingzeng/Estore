<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function con(){
		return confirm("确认删除");
	}
	
</script>
</head>
<body>
	<c:forEach items="${requestScope.list }" var="order">
		订单id：${order.id }<br>
		订单金额：${order.money }<br>
		收获地址：${order.adderss }<br>
		支付状态：
			<c:if test="${order.paystate==0 }">
				未支付  <a href="${pageContext.request.contextPath }/servlet/DeleteOrderServlet?id=${order.id}" onclick="return con();">删除订单</a>
			</c:if>
			<c:if test="${order.paystate==1 }">
				已支付
			</c:if>
		<br>
		下单时间：${order.ordertime }<br>
		用户名称：${order.userName }
		
		
			<table border="1">
				<tr>
					<th>商品名称</th>
					<th>商品种类</th>
					<th>商品单价</th>
					<th>购买数量</th>
				</tr>
				<c:forEach items="${order.items }" var="item">
					<tr>
						<td>${item.pro_name }</td>
						<td>${item.pro_category }</td>
						<td>${item.pro_price }</td>
						<td>${item.number }</td>
					</tr>
				</c:forEach>
			</table>
			<hr>
		</c:forEach>
		
	
</body>
</html>