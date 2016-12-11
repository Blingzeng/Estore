<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeNum(id,num,oldnum){
		num=Math.ceil(num);
		if(isNaN(num)){
			alert("数量必须为正整数！")
			document.getElementById("buyNum").value=oldnum;
			return;
		}
		if(num<=0){
			alert("数量必须大于0！")
			document.getElementById("buyNum").value=oldnum;
			return;
		}
		window.location="${pageContext.request.contextPath }/servlet/ChangeNumServlet?id="+id+"&num="+num;
	}
	function c(){
		return confirm("确认要清空购物车？")
	}
</script>
</head>
<body>
	<div align="right" style="font-size: 40px;">
		<a href="${pageContext.request.contextPath }/servlet/ProListServlet">商城</a>
		<a href="${pageContext.request.contextPath }/servlet/ClearCartServlet" onclick="return c()">清空购物车</a>
		<a href="${pageContext.request.contextPath }/index.jsp">主页</a>
		<a href="${pageContext.request.contextPath }/Order.jsp"><img alt="下单" src="${pageContext.request.contextPath }/img/gotoorder.bmp"></a>
	</div>
	<c:if test="${empty sessionScope.cartmap }">
		<h2 align="center">购物车空空如也，快到<a href="${pageContext.request.contextPath }/servlet/ProListServlet">商城</a>购物吧</h2>
	</c:if>
	<c:if test="${not empty sessionScope.cartmap }">
		<table align="center" width="100%" border="1" style="text-align: center;">
		<tr>
			<th>商品图片</th>
			<th>商品名称</th>
			<th>商品单价</th>
			<th>商品类别</th>
			<th>购买数量</th>
			<th>商品库存</th>
			<th>支付金额</th>
			<th>删除</th>
		</tr>
		<c:set var="money" value="0"></c:set>
		<c:forEach items="${sessionScope.cartmap }" var="entry">
			<tr>
				<td><img alt="商品图片" src="${pageContext.request.contextPath }/servlet/ImgServlet?path=${entry.key.imgUrls}"></td>
				<td>${entry.key.proName }</td>
				<td>${entry.key.proPrice }</td>
				<td>${entry.key.category }</td>
				<td><input id="buyNum" type="text" value="${entry.value }"  size="1" onchange="changeNum('${entry.key.id }',this.value,${entry.value })" /></td>
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
				<td>
					<a href="${pageContext.request.contextPath }/servlet/DeleteCartServlet?id=${entry.key.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		
	</table><hr>
	<div style="text-align: right;">
		<h3><font color="red">总金额：${money }</font></h3>
	</div>
		
	</c:if>
	
</body>
</html>