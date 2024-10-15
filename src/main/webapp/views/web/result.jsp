
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${message}</h2>

	<!-- Hiển thị ảnh nếu có -->
	<c:if test="${not empty imageUrl}">
		<img src="${imageUrl}" alt="Uploaded Image" />
	</c:if>
</body>
</html>