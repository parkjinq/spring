<%@ page import="kr.or.ddit.util.model.PageVO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
	.userClick {
		cursor : pointer;
	}
</style>
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
		
		var ev = "click";
		
		$(".prodClick").on(ev, function() {
			console.log("document.ready");
			var prod_id = $(this).children()[0].innerText;
			
			$("#prodId").val(prod_id);
			$("#frm").submit();
		});
	});
	
	function getProdList(page) {
		var pageSize = 10;
		console.log("page : " + page);
		
		$.ajax({
			type : "",
			url : "",
			data : ,
			success : function(data) {
				console.log(data);
			}
		});
	}
	
</script>


<form id="frm" action="/prod/prodDetail" method="get">
	<input type="hidden" id="prodId" name="prod_id">
</form>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="row">
					<div class="col-sm-8 blog-main">
						userPageList.jsp
						<h2 class="sub-header">사용자 페이징 리스트</h2>
						<div class="table-responsive">
							<table class="table table-striped table-hover">
								<tr>
									<th>제품아이디</th>
									<th>제품명</th>
									<th>제품그룹명</th>
									<th>제품등록일</th>
								</tr>
<%-- 								<tr id="<%=user.getUserId()%>" onclick="javascript:userDetail(<%=user.getUserId()%>)"> --%>
<%-- 								<tr onclick="javascript:userDetail('<%=user.getUserId()%>')"> --%>
								
								<c:forEach items="${pdList}" var="pd">
									<tr class="prodClick">
										<td>${pd.prod_id}</td>
										<td>${pd.prod_name }</td>
										<td>${pd.lprod_nm }</td>
										<td><fmt:formatDate value="${pd.prod_insdate }" pattern="yyyy/MM/dd"/></td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<a class="btn btn-default pull-right" href="/userForm">제품 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<li><a href="/prod/prodPageList?page=1&pageSize=10" aria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
								<li><a href="/prod/prodPageList?page=1&pageSize=10"><<</a></li>
								
								<%--페이지네이션 jstl화 --%>
								<c:set var="startIndex" value="0"></c:set>
								<c:set var="endIndex" value="0"></c:set>
								<c:set var="pagingNum" value="0"></c:set>
								<c:set var="userAllNum" value="${pageCnt }"></c:set>
								
								<c:choose>
									<c:when test="${pdCnt != 0}">
										<c:forEach begin="0" end="${(pageVO.page -1)/5 }" varStatus="status">
											<c:set var="startIndex" value="${(status.index * 5) + 1 }"></c:set>
											<c:set var="endIndex" value="${startIndex + 4 }"></c:set>
										</c:forEach>
										<c:if test="${endIndex > pdCnt}">
											<c:set var="endIndex" value="${pdCnt}"></c:set>
										</c:if>
										
									</c:when>
									<c:otherwise>
										<c:set var="startIndex" value="1"></c:set>
										<c:set var="endIndex" value="1"></c:set>
									</c:otherwise>
								</c:choose>
<%-- 								<% --%>
<!-- 									PageVO currentPage = (PageVO)request.getAttribute("pagination"); -->
									
<!-- 									int userAllNum = 0;					//페이지네이션 마지막 숫자(제일 큰수) -->
<!-- 									int startIndex = 0; 				//페이지네이션 시작넘버 1~5 > 1, 6~10 > 6 -->
<!-- 									int endIndex = 0;					//5단위 끝자리 1~5 > 5, 6~10 > 10 -->
<!-- 									int pagingNum = 0;					//상황에 맞게 startIndex를 이용하여 페이지 이동 버튼 값을 할당 -->
									
<!-- 									//가장 마지막 페이지 구하는 조건문 -->
<!-- // 									userAllNum = userList.size()%10 != 0 ? userList.size() / 10 + 1 : userList.size() / 10; -->
<!-- 									userAllNum = (Integer)request.getAttribute("pageCnt"); -->
									
<!-- 									//페이징 5개씩 나눌때 시작페이지와 끝페이지번호 정하는 조건문 -->
<!-- 									if(userAllNum != 0){ -->
<!-- 										for(int i = 0; i <= (currentPage.getPage()-1)/5; i++){ -->
<!-- 											startIndex = (5*i)+1; -->
<!-- 											endIndex = startIndex + 4; -->
<!-- 										} -->
<!-- 										//페이징에서 필요한 길이(5개중 2개만 필요하면 반복문이 딱 2번만 돌도록 값을 넣어주는 부분) -->
<!-- 										if(endIndex > userAllNum){ -->
<!-- 											endIndex = userAllNum; -->
<!-- 										} -->
<!-- 									} else { -->
<!-- 										//반환된 리스트가 없을 경우 -->
<!-- 										startIndex = 1; -->
<!-- 										endIndex = 1; -->
<!-- 									} -->
<!-- 									request.setAttribute("startIndex", startIndex); -->
<!-- 									request.setAttribute("endIndex", endIndex); -->
<!-- 									request.setAttribute("userAllNum", userAllNum); -->
<!-- 									%> -->
								<li><a href="/prod/prodPageList?page=${startIndex == 1 ? 1 : startIndex-5}&pageSize=10"><</a></li>
								
								<c:forEach begin="${startIndex }" end="${endIndex }" varStatus="status">
									<li><a href="/prod/prodPageList?page=${status.index }&pageSize=10">${status.index }</a></li>
								</c:forEach>

								<li><a href="/prod/prodPageList?page=${startIndex + 5 > pdCnt ? startIndex : startIndex + 5}&pageSize=10">></a></li>
								<li><a href="/prod/prodPageList?page=${pdCnt}&pageSize=10">>></a></li>
								<li><a href="/prod/prodPageList?page=${pdCnt}&pageSize=10"" aria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
