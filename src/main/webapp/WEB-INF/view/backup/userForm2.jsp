<%@page import="kr.or.ddit.util.model.PageVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>
<style type="text/css">
	.kyuseung {
		width: 200px;
		height: 200px;
	}
</style>

<%@ include file="/WEB-INF/view/common/basicLib.jsp"%>

<!-- 다음 주소 API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- jquery ui api -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- jquery ui css -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>

	$(function() {
		//생일 input datepicker 적용
		$("#birth").datepicker({
			yearRanges : "1990:2018",
			showOn : "button",
			buttonImage : "profile/cal_icon.jpg",
			buttonImageOnly : true,
			buttonText : "Select date",
			changeMonth: true,
			changeYear: true,
			dateFormat: "yy-mm-dd",
			monthNamesShort : [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ]
		});
	});

	$(document).ready(function() {
		//개발과정에서 사용한 기본값 설정
		$("#userId").val("testId");
		$("#pass").val("pass1234");
		$("#name").val("testUserId");
		$("#addr1").val("대전 중구 중앙로 76");
		$("#addr2").val("영민빌딩 2층 203호");
		$("#zipcd").val("34940");
		$("#birth").val("1991-12-23");
		$("#email").val("parkjinq@gmail.com");
		$("#tel").val("01045480041");
		
		$('#addrSearchBtn').click(function() {
			//주소 검색버튼 클릭이벤트가 발생했을때 실행
			new daum.Postcode({
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
					// 예제를 참고하여 다양한 활용법을 확인해 보세요.
					console.log(data);
					//주소 : roadAddress
					//상세주소 : ""
					//우편번호 : zoneCode

					var roadAddress = data.roadAddress;
					var zoncode = data.zonecode;

					//주소 input value설정 : data.roadAddress
					//우편번호 input value설정 : data.zonecode
					$('#zipcd').val(zoncode);
					$('#addr1').val(roadAddress);
					// 					document.getElementById('zipcd').value = zoncode;
					// 					document.getElementById('addr1').value = roadAddress;

				}
			}).open();
		});
	});
</script>

</head>

<body>

	<%@ include file="/WEB-INF/view/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@ include file="/WEB-INF/view/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<form action="/user/userForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<img src="/profile/no_image.jpg" class="kyuseung"/>
							<input type="file" id="profile" name="profilePic">
						
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userId" name="userId"
								placeholder="사용자 아이디" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 비밀번호</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="pass" name="pass"
								placeholder="사용자 비밀번호" >
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name"
								placeholder="사용자 이름" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
						<!-- disable이면 값이 안간다, readonly가 적합 -->
							<input type="text" class="form-control" id="zipcd" readonly="readonly" 
								name="zipcd" placeholder="우편번호">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button id="addrSearchBtn" type="button" class="btn btn-default" >우편번호 검색</button>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr1" name="addr1" readonly="readonly"
								placeholder="주소">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2"
								name="addr2" placeholder="상세주소">
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">생년월일</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="birth"
								name="birth" placeholder="생년월일">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">이메일</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="email"
								name="email" placeholder="이메일">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">연락처</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="tel"
								name="tel" placeholder="연락처">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">등록</button>
							<button type="reset" class="btn btn-default">취소</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>

