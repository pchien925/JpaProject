<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>

<h2>List of Videos</h2>

<table border="1" width="100%">
	<thead>
		<tr>
			<th>Video ID</th>
			<th>Title</th>
			<th>Format</th>
			<th>Status</th>
			<th>Thumbnail</th>
			<th>Created At</th>
			<th>Video URL</th>
			<th>Category</th>
			<th>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="video" items="${listvideo}">
			<tr>
				<td>${video.videoId}</td>
				<td>${video.title}</td>
				<td>${video.format}</td>
				<td>${video.status == 1 ? 'Active' : 'Inactive'}</td>
				<td><img src="${video.thumbnailUrl}"
					alt="${video.title} Thumbnail" width="100" /></td>
				<td>${video.createdAt}</td>
				<td><video width="320" height="240" controls>
						<source src="${video.videoUrl }" type="video/mp4">
					</video></td>
				<td>${video.category.name}</td>
				<td><a
				href="<c:url value='/admin/video/edit?id=${video.videoId}'/>">Sửa</a>
				<a
				href="<c:url value='/admin/video/delete?id=${video.videoId}'/>">Xóa</a>
			</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
