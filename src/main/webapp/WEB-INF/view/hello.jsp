<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	hello.jsp
	<h2>rangers</h2>
	<c:forEach items="${rangers}" var="ranger">
		${ranger}<br />
	</c:forEach>
	
	<h2>/hello/request</h2>
	<form action="/hello/request" method="post">
		userId : <input type="text" name="userId" value="jin">
		password : <input type="password" name="pass" value="java">
		<button type="submit">전송</button>
	</form>
	
	<h2>/hello/vo</h2>
	<form action="vo" method="post">
		userId : <input type="text" name="userId" value="진">
		password : <input type="password" name="pass" value="자바">
		<button type="submit">전송</button>
	</form>
	
</body>
</html>