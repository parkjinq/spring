<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>fileupload.jsp</h1>
	<form action="/mvc/fileupload" method="post" enctype="multipart/form-data">
		<input type="text" name="userId" value="sally"><br />
		<input type="file" name="file">
		<button type="submit">전송</button>
	</form>
</body>
</html>