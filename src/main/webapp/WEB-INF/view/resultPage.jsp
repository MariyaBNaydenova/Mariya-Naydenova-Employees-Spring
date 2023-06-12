<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Show Result</title>
		<style><%@include file="/WEB-INF/view/style.css"%></style>
		
	</head>
	<body>
		<form method="GET" action="/" enctype="multipart/form-data">
		<br><br>
			<p >${result}</p>
			<br><br><br>
			<input type="submit" value="BACK TO FILE UPLOAD" class="buttons" id="submitFile">
		</form>	
	</body>
</html>