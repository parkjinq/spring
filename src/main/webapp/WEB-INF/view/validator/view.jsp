<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view.jsp</title>

</head>
<body>
	<h1>view.jsp(spring validator)</h1>
	<form action="/validator/validate" method="post">
		ID : <input type="text" name="userId" value="${param.userId}"><form:errors path="userVO.userId"></form:errors><br />
		name : <input type="text" name="name" value="${param.name}"><form:errors path="userVO.name"></form:errors><br />
		password : <input type="password" name="pass" value="${param.pass}"><form:errors path="userVO.pass"></form:errors><br />
		<button type="submit">전송</button>
	</form>
	
	<h1>view.jsp(jsr303)</h1>
	<form action="/validator/validateJsr" method="post">
		ID : <input type="text" name="userId" value="${param.userId}"><form:errors path="userVOJsr303.userId"></form:errors><br />
		name : <input type="text" name="name" value="${param.name}"><form:errors path="userVOJsr303.name"></form:errors><br />
		password : <input type="password" name="pass" value="${param.pass}"><form:errors path="userVOJsr303.pass"></form:errors><br />
		<button type="submit">전송</button>
	</form>
	
</body>
</html>