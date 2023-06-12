<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
                    rel="stylesheet">
		<title>Show Result</title>
		<style><%@include file="/WEB-INF/view/style.css"%></style>
		
	</head>
	<body>
		<form method="GET" action="/" enctype="multipart/form-data" id="dataGridForm">
		<br><br>
		<table id="gridTable">
			<thead>
			  <tr>
				    <th>Employee ID #1</th>
				    <th>Employee ID #2</th>
				    <th>Project ID</th>
				    <th>Days worked</th>
			  </tr>
			</thead>
			<tbody>
				<c:forEach items="${grid}" var="row">
					<tr>
						<td>${row[0]}</td><td>${row[1]}</td><td>${row[2]}</td><td>${row[3]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
			
			<br><br>
			<input type="submit" value="BACK TO FILE UPLOAD" class="buttons" id="back">
		</form>	
		
		<Script>	
		

		</Script>
	</body>
</html>