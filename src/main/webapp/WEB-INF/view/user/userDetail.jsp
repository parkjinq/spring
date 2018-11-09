<%@ page import="kr.or.ddit.util.model.PageVO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">
	.kyuseung {
		width: 200px;
		height: 200px;
	}
</style>

<script type="text/javascript">
	$(document).ready(function() {

		var ev = "click";
		
		$(".updateClick").on(ev, function() {
			console.log("document.ready");
			$("#uu").submit();
		});
	});
</script>

</head>

<form id="uu" action="/user/userUpdate" method="get">
	<input type="hidden" id="userId" name="userId" value="${userVO.userId}">
</form>


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<form class="form-horizontal" role="form" action="/userPageList" method="get">
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
						
						<%--jstl --%>
						<c:choose>
							<c:when test="${userVO.profile != null }">
								<img src="${userVO.profile }" class="kyuseung"/>
								<img src="/fileDownloadServlet?userid=${userVO.userId}" class="kyuseung"/>
							</c:when>
							<c:otherwise>
								<img src="<c:url>/profile/no_image.jpg</c:url>" class="kyuseung"/>
							</c:otherwise>
						</c:choose>

						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
								<label class="control-label" id="userId" >${userVO.userId }</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">이름</label>
						<div class="col-sm-10">
							<label class="control-label" >${userVO.name }</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">주소</label>
						<div class="col-sm-10">
								<label class="control-label" >${userVO.addr1}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
								<label class="control-label" >${userVO.addr2}</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
								<label class="control-label" >${userVO.zipcd}</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">생년월일</label>
						<div class="col-sm-10">
								<label class="control-label" ><fmt:formatDate value="${userVO.birth }" pattern="yyyy-MM-dd"/></label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">이메일</label>
						<div class="col-sm-10">
								<label class="control-label" >${userVO.email}</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">연락처</label>
						<div class="col-sm-10">
								<label class="control-label" >${userVO.tel}</label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="hidden" name="page" value="1">
							<input type="hidden" name="pageSize" value="10">
							<input type="button" class="btn btn-default updateClick" value="정보수정" >
							<button type="submit" class="btn btn-default">목록보기</button>
						</div>
					</div>
				</form>

			</div>

