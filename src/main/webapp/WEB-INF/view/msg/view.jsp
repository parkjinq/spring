<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#lang').on('change', function() {
			location.href = 'http://localhost:8081/messageView?lang=' + this.value;
		});
		
		$('#lang').val('${param.lang}');
	});
</script>
</head>
<body>
	<h1>msg/view.jsp</h1>
	hello :
	<spring:message code="hello" />
	<br> visitor :
	<spring:message code="visitor" arguments="brown"/>
	<br>
	<form action="/messageView" method="post">
		<select name="lang" id="lang">
			<option value="ko">한국어</option>
			<option value="ja">日本語</option>
			<option value="en">English</option>
		</select> <input type="submit" value="전송"><br />
	</form>
	
	
</body>
</html>