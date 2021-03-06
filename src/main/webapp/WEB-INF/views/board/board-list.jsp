<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>Insert title here</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="${contextPath}/resources/img/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/bootstrap/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/fonts/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/util.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/main.css">
<style type="text/css">
.movementWrapper{
	font-family: OpenSans-Regular;
	font-size: 18px;
	font-weight: unset;
	margin-top: 10px;
}
.write-button {
	background-color: #36304a;
	color: white;
	padding: 16px 16px 16px 16px;
	line-height: 1.2;
	border-radius: 10px;
}
.pagination{
	position: relative;
	left: 42%;
}
.btn{
	background-color: #36304a;
	padding: 16px 16px 16px 16px;
	border-radius: 10px;
	border: none;
	margin-right: 6px;
}
.column1{
	width: 120px;
}
.column6{
	width: 80px;
}
tr>th, tr>td{
	text-align: center !important;
}
</style>
</head>
<body>

	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
					<table>
						<thead>
							<tr class="table100-head">
								<th class="column1">No</th>
								<th class="column2">ID</th>
								<th class="column3">Title</th>
								<th class="column4">Date</th>
								<th class="column5">Views</th>
								<th class="column6">Likes</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${boardList.board}" var="list" varStatus="status">
							<tr>
								<td class="column1">${list.bdIdx}</td>
								<td class="column2">${list.userId}</td>
								<td class="column3"><a href="board/board-detail?bdIdx=${list.bdIdx}">${list.title}</a></td>
								<td class="column4">${list.regDate}</td>
								<td class="column5">${list.viewCount}</td>
								<td class="column6">${list.recCount}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="movementWrapper" style="display: flex;justify-content: space-between;">
					<ul class="pagination">
						<li>
							<a class="btn btn-primary" href="${boardList.paging.url}?page=${boardList.paging.prev}" aria-label="Previous"> <span aria-hidden="true" style="color: white">&laquo;</span></a>
						</li>
						<li>
							<c:forEach begin="${boardList.paging.blockStart}" end="${boardList.paging.blockEnd}" var="p">
								<a class="btn btn-primary" href="${boardList.paging.url}?page=${p}" style="color: white">${p}</a>
							</c:forEach>
						</li>
						<li>
							<a class="btn btn-primary" href="${boardList.paging.url}?page=${boardList.paging.next}" aria-label="Next"> <span aria-hidden="true"	style="color: white">&raquo;</span></a>
						</li>
					</ul>
					<button class="write-button btn btn-primary" type="button" onclick="location.href='board/board-form'">Write</button>
				</div>
			</div>
		</div>
	</div>




	<!--===============================================================================================-->
	<script src="${contextPath}/resources/js/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="${contextPath}/resources/js/bootstrap/popper.js"></script>
	<script src="${contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="${contextPath}/resources/js/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>