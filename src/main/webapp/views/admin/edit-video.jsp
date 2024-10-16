<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>


<form action="<c:url value="/admin/video/update"/>" method="post"
	enctype="multipart/form-data">
	<input type="text" name="videoId" value="${video.videoId}"
		hidden="hidden"> <label for="lname">Title:</label><br> <input
		type="text" id="title" name="title"><br> <label
		for="video">Choose a video file:</label> <input type="file"
		name="video" id="video"> <br> <br>
	<label for="html">Status</label><br> <input type="radio" id="ston"
		name="status" value="1"> <label for="css">Hoạt động</label><br>
	<input type="radio" id="stoff" name="status" value="0"> <label
		for="javascript">Khóa</label> <br> <br>
	<button type="submit">Upload Video</button>
</form>