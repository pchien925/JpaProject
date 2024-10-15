<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/commons/admin/header.jsp"%>
	<%@include file="/commons/admin/left.jsp"%>

	<sitemesh:write property="body" />

	<%@include file="/commons/admin/footer.jsp"%>

</body>
</html>