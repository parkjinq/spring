<%@ page import="kr.or.ddit.util.model.PageVO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
	.userClick {
		cursor : pointer;
	}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
// 	function userDetail(userId) {
// // 		location.href = "/userDetail?userId=" + userId.id;
// 		location.href = "/userDetail?userId=" + userId;
// 	}
	$(document).ready(function() {
		console.log("document.ready");
		
		//tr에 select (class="userClick")
// 		$(".userClick").click(function() {
// 		});
		
		$("#userList").on("click", ".userClick", function() {
			console.log("document.ready");
			var userId = $(this).children()[1].innerHTML;
			
			$("#userId").val(userId);
			$("#frm").submit();
		});
	});
	
	//page인자를 받아서
	//해당페이지에 속하는 사용자 리스트 정보를 가져온다.
	function getUserList(page) {
		var pageSize = 10;
		console.log("page : " + page);
		
		//ajax call
		//사용자 리스트 데이터만 가져오기
		//html(as-is) > json(to-be) 데이터를 받는 형태로 변경
		$.ajax({
			type : "GET",
			url:"/user/userPageListAjax",
			data : "page=" + page + "&pageSize=" + pageSize,
			success : function(data) {
				//data.userList, data.pageCnt
				//data(사용자 json 데이터)를 바탕으로 사용자리스트를 변경
				//사용자 리스트를 갱신
				//1.기존 리스트 삭제
				//2.data를 이용하여 table 태스(tr)를 작성
				//3.기존 리스트 위치에 붙여넣기
				console.log(data);
				
				var html = "";
				$.each(data.userList, function(idx, user) {
					console.log(user);
					html += "<tr class='userClick'>";
					html += "<td>" + user.rnum + "</td>";
					html += "<td>" + user.userId + "</td>";
					html += "<td>" + user.name + "</td>";
					html += "<td>" + user.formattedBirth + "</td>";
					html += "</tr>";
				});
				
					
				
				$("#userList").html("");
				$("#userList").html(html);
				
				var pageHtml = "";
				pageHtml += "<tr>";
				pageHtml += "<div class='text-center'>";
				pageHtml += "<ul class='pagination'>";
				pageHtml += "<li><a href='javascript:getUserList(1);' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>";
				console.log(data.pageCnt);
				for(var idx = 1; idx <= data.pageCnt; idx++){
					pageHtml += "<li><a href='javascript:getUserList(" + idx + ");'>" + idx + "</a></li>";
				}
				pageHtml += "<li><a href='javascript:getUserList(${userAllNum });' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>";
				pageHtml += "</ul>";
				pageHtml += "</div>";
				pageHtml += "</tr>";
					
				$("#pageHtml").html("");
				$("#pageHtml").html(pageHtml);
			}
		});
		
	}
	
	function getUserListHtml(page) {
		var pageSize = 10;
		$.ajax({
			url : "/user/userPageListAjaxHtml",
			type : "get",
			data : "page=" + page + "&pageSize=" + pageSize,
			success : function(dt) {
				$("#userList").html(dt);
			}
		});
	}
	
	function getUserPagenationHtml(page) {
		var pageSize = 10;
		$.ajax({
			url : "/user/userPagenationAjaxHtml",
			type : "get",
			data : "page=" + page + "&pageSize=" + pageSize,
			success : function(pdt) {
				console.log(pdt);
				$("#pageHtml").html(pdt);
			}
		});
	}
		
// 		getUserList(1);
		getUserListHtml(1);			//userList 반환하는 메서드
		getUserPagenationHtml(1);	//해당페이지 페이지네이션 정보를 리턴하는 메서드
	
</script>

<form id="frm" action="/user/userDetail" method="get">
	<input type="hidden" id="userId" name="userId">
</form>


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="row">
					<div class="col-sm-8 blog-main">
						userPageList.jsp
						<h2 class="sub-header">사용자 페이징 리스트</h2>
						<div class="table-responsive">
							<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>번호</th>
									<th>아이디</th>
									<th>이름</th>
									<th>생년월일</th>
								</tr>
								</thead>
<%-- 								<tr id="<%=user.getUserId()%>" onclick="javascript:userDetail(<%=user.getUserId()%>)"> --%>
<%-- 								<tr onclick="javascript:userDetail('<%=user.getUserId()%>')"> --%>
								<tbody id="userList">
<%-- 								<c:forEach items="${userList}" var="user"> --%>
<!-- 									<tr class="userClick"> -->
<%-- 										<td>${user.rnum}</td> --%>
<%-- 										<td>${user.userId}</td> --%>
<%-- 										<td>${user.name}</td> --%>
<%-- 										<td><fmt:formatDate value="${user.birth }" pattern="yyyy-MM-dd"/></td> --%>
<!-- 									</tr> -->
<%-- 								</c:forEach> --%>
								</tbody>
							</table>
						</div>

						<a class="btn btn-default pull-right" href="/user/userForm">사용자 등록</a>
	
						
						<div id="pageHtml" >
						
						</div>
						
						
						
						
						
						

<!-- 						<div class="text-center"> -->
<!-- 							<ul class="pagination"> -->
<!-- 								<li><a href="javascript:getUserList(1);" aria-label="Previous"> <span -->
<!-- 										aria-hidden="true">&laquo;</span> -->
<!-- 								</a></li> -->
<!-- 								<li><a href="/user/userPageList?page=1&pageSize=10"><<</a></li> -->
								
<%-- 								페이지네이션 jstl화 --%>
<%-- 								<c:set var="startIndex" value="0"></c:set> --%>
<%-- 								<c:set var="endIndex" value="0"></c:set> --%>
<%-- 								<c:set var="pagingNum" value="0"></c:set> --%>
<%-- 								<c:set var="userAllNum" value="${pageCnt }"></c:set> --%>
								
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${pageCnt != 0}"> --%>
<%-- 										<c:forEach begin="0" end="${(pageVO.page -1)/5 }" varStatus="status"> --%>
<%-- 											<c:set var="startIndex" value="${(status.index * 5) + 1 }"></c:set> --%>
<%-- 											<c:set var="endIndex" value="${startIndex + 4 }"></c:set> --%>
<%-- 										</c:forEach> --%>
<%-- 										<c:if test="${endIndex > userAllNum }"> --%>
<%-- 											<c:set var="endIndex" value="${userAllNum }"></c:set> --%>
<%-- 										</c:if> --%>
										
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<%-- 										<c:set var="startIndex" value="1"></c:set> --%>
<%-- 										<c:set var="endIndex" value="1"></c:set> --%>
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose> --%>
<%-- 								<li><a href="/user/userPageList?page=${startIndex == 1 ? 1 : startIndex-5}&pageSize=10"><</a></li> --%>
								
<%-- 								<c:forEach begin="${startIndex }" end="${endIndex }" varStatus="status"> --%>
<%-- 									<li><a href="javascript:getUserList(${status.index});">${status.index }</a></li> --%>
<%-- 								</c:forEach> --%>

<%-- 								<li><a href="/user/userPageList?page=${startIndex + 5 > userAllNum ? startIndex : startIndex + 5}&pageSize=10">></a></li> --%>
<%-- 								<li><a href="/user/userPageList?page=${userAllNum }&pageSize=10">>></a></li> --%>
<%-- 								<li><a href="javascript:getUserList(${userAllNum });" aria-label="Next"> <span --%>
<!-- 										aria-hidden="true">&raquo;</span> -->
<!-- 								</a></li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
					</div>
				</div>
			</div>
