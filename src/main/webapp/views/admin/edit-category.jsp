<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>

<form action="<c:url value="/admin/category/update"/>" method="post"
	enctype="multipart/form-data">
	<input type="text" name="categoryId" value="${cate.categoryId}"
		hidden="hidden"> <label for="fname">Category name:</label><br>
	<input type="text" id="categoryname" name="categoryName"><br>
	<label for="lname">Title:</label><br> <input type="text"
		id="title" name="title"><br> <label for="lname">Upload
		images:</label><br> <input type="file" id="images" name="images"><br>
	<label for="html">Status</label><br> <input type="radio" id="ston"
		name="status" value="1"> <label for="css">Hoạt động</label><br>
	<input type="radio" id="stoff" name="status" value="0"> <label
		for="javascript">Khóa</label> <br> <br> <input type="submit"
		value="Insert">
</form>