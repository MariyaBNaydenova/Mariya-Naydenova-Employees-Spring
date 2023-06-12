<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Employees File Upload</title>
	<style><%@include file="/WEB-INF/view/style.css"%></style>
	</head>
	<body>
		<form method="POST" action="/fileUpload" enctype="multipart/form-data">
		<br><br>
			<p>Select file for upload: </p>
			<br>
			<input type="file" name="file" class="buttons">
			<br><br>
			<input type="submit" value="SUBMIT" class="buttons" id="submitFile">
		</form>	
	</body>
</html>