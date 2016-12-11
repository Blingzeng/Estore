<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Estore 电子商城</h1><hr>
	<c:if test="${sessionScope.user==null }">
	<h4>游客，欢迎你的到来</h4>
	<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
	<a href="${pageContext.request.contextPath }/regist.jsp">注册</a>
	</c:if>
	<c:if test="${sessionScope.user!=null }">
	<h4>${sessionScope.user.nickName },欢迎你的到来</h4>
	<a href="${pageContext.request.contextPath }/servlet/ProListServlet">商品列表</a>
	<a href="${pageContext.request.contextPath }/CartMap.jsp">我的购物车</a>
	<a href="${pageContext.request.contextPath }/addPro.jsp">添加商品</a>
	<a href="${pageContext.request.contextPath }/servlet/FindOrderServlet">查看订单</a>
	<a href="${pageContext.request.contextPath }/servlet/logoutServlet">注销</a>
	</c:if>
</body>
</html>