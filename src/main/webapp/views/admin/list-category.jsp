<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>

<a href="<c:url value='/admin/category/add'/>">Add Category</a>
<br>
<hr>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>Category name</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr>
			<td>${STT.index + 1}</td>

			<!-- Hiển thị ảnh từ link hoặc server -->
			<c:choose>
				<c:when
					test="${cate.images != null && cate.images.startsWith('https')}">
					<c:url value="${cate.images}" var="imgUrl" />
				</c:when>
				<c:otherwise>
					<c:url value='/image?fname=${cate.images}' var="imgUrl" />
				</c:otherwise>
			</c:choose>

			<td><img height="150" width="200" src="${cate.images}"
				alt="Category Image" /></td>
			<td>${cate.categoryname}</td>

			<!-- Hiển thị trạng thái (có thể dùng số nguyên hoặc kiểu boolean) -->
			<td>${cate.status == 1 ? 'Active' : 'Inactive'}</td>

			<!-- Các nút hành động -->
			<td><a
				href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>">Sửa</a>
				<a
				href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>
