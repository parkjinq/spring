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


<!-- <script type="text/javascript"> -->
<!-- 	$(document).ready(function() { -->

<!-- 		var ev = "click"; -->
		
<!-- 		$(".updateClick").on(ev, function() { -->
<!-- 			console.log("document.ready"); -->
<!-- 			$("#uu").submit(); -->
<!-- 		}); -->
<!-- 	}); -->
<!-- </script> -->


<!-- <form id="uu" action="/userUpdate" method="get"> -->
<%-- 	<input type="hidden" id="prodId" name="prod_id" value="${prodVO.prod_id}"> --%>
<!-- </form> -->


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<form class="form-horizontal" role="form" action="/prodPageList" method="get">

					<div class="form-group">
						<label for="prod_id" class="col-sm-2 control-label">제품아이디</label>
						<div class="col-sm-10">
								<label class="control-label" id="prod_id" >${prodVO.prod_id }</label>
						</div>
					</div>

					<div class="form-group">
						<label for="prod_name" class="col-sm-2 control-label">제품명</label>
						<div class="col-sm-10">
							<label class="control-label" >${prodVO.prod_name }</label>
						</div>
					</div>

					<div class="form-group">
						<label for="lprod_nm" class="col-sm-2 control-label">제품그룹명</label>
						<div class="col-sm-10">
								<label class="control-label" >${prodVO.lprod_nm}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="buyer_name" class="col-sm-2 control-label">바이어 이름</label>
						<div class="col-sm-10">
								<label class="control-label" >${prodVO.buyer_name}</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="buyer_email" class="col-sm-2 control-label">바이어 이메일</label>
						<div class="col-sm-10">
								<label class="control-label" >${prodVO.buyer_email}</label>
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

