<%@ page import="kr.or.ddit.util.model.PageVO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<style type="text/css">
.kyuseung {
	width: 200px;
	height: 200px;
}
</style>

<!-- 다음 주소 API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- jquery ui api -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- jquery ui css -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>
	$(function() {
		//생일 input datepicker 적용
		$("#birth").datepicker({
			yearRanges : "1990:2018",
			showOn : "button",
			buttonImage : "profile/cal_icon.jpg",
			buttonImageOnly : true,
			buttonText : "Select date",
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd",
			monthNamesShort : [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 ]
		});
	});

	$(document).ready(function() {

		$('#profile').on("change", function(e) {
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);
			
			filesArr.forEach(function(f) {
				if(!f.type.match("image.*")){
					alert("이미지 확장자 파일만 가능합니다.");
					return;
				}
				sel_file = f;
				var reader = new FileReader();
				reader.onload = function(e) {
					$(".kyuseung").attr("src", e.target.result);
				}
				reader.readAsDataURL(f);
			});
		});
		
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


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<form action="/user/userUpdate" method="post" class="form-horizontal"
					role="form" enctype="multipart/form-data">

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
							<c:choose>
								<c:when test="${userVO.profile == null }" >
									<img src="/profile/no_image.jpg" class="kyuseung" />
								</c:when>
								<c:otherwise>
									<img src="${userVO.profile }" class="kyuseung" />
								</c:otherwise>
							</c:choose>
							<input type="file" id="profile" name="profilePic" accept="image/*">
							<input type="hidden" name="existingProfile" value="${userVO.profile }">

						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userId" name="userId"
								readonly="readonly" placeholder="사용자 아이디"
								value="${userVO.userId }">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자
							비밀번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="pass" name="pass"
								placeholder="사용자 비밀번호" value="${userVO.pass }">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name"
								placeholder="사용자 이름" value="${userVO.name }">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
							<!-- disable이면 값이 안간다, readonly가 적합 -->
							<input type="text" class="form-control" id="zipcd"
								readonly="readonly" name="zipcd" placeholder="우편번호"
								value="${userVO.zipcd }">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button id="addrSearchBtn" type="button" class="btn btn-default">우편번호
								검색</button>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr1" name="addr1"
								readonly="readonly" placeholder="주소"
								value="${userVO.addr1 }">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2"
								placeholder="상세주소" value="${userVO.addr2 }">
						</div>
					</div>


					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">생년월일</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="birth" name="birth"
								placeholder="생년월일"
								value='<fmt:formatDate value="${userVO.birth }" pattern="yyyy-MM-dd" />'>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">이메일</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="email" name="email"
								placeholder="이메일" value="${userVO.email }">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">연락처</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="tel" name="tel"
								placeholder="연락처" value="${userVO.tel }">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">수정</button>
							<button type="reset" class="btn btn-default" onclick="history.back()">취소</button>
						</div>
					</div>
				</form>

			</div>

