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
								<th class="column5">views</th>
								<th class="column6">likes</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${boardList}" var="list" varStatus="status">
							<tr>
								<td class="column1">${list.bdIdx}</td>
								<td class="column2">${list.userId}</td>
								<td class="column3"><a href="board/board-detail?bdIdx=${list.bdIdx}">${list.title}</a></td>
								<td class="column4">${list.regDate}</td>
								<td class="column5">${list.viewCount}</td>
								<td class="column6">${list.recCount}</td>
							</tr>
							</c:forEach>
							<tr>
								<td class="column1">2017-09-28 05:57</td>
								<td class="column2">200397</td>
								<td class="column3">Samsung S8 Black</td>
								<td class="column4">$756.00</td>
								<td class="column5">1</td>
								<td class="column6">$756.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-26 05:57</td>
								<td class="column2">200396</td>
								<td class="column3">Game Console Controller</td>
								<td class="column4">$22.00</td>
								<td class="column5">2</td>
								<td class="column6">$44.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-25 23:06</td>
								<td class="column2">200392</td>
								<td class="column3">USB 3.0 Cable</td>
								<td class="column4">$10.00</td>
								<td class="column5">3</td>
								<td class="column6">$30.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-24 05:57</td>
								<td class="column2">200391</td>
								<td class="column3">Smartwatch 4.0 LTE Wifi</td>
								<td class="column4">$199.00</td>
								<td class="column5">6</td>
								<td class="column6">$1494.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-23 05:57</td>
								<td class="column2">200390</td>
								<td class="column3">Camera C430W 4k</td>
								<td class="column4">$699.00</td>
								<td class="column5">1</td>
								<td class="column6">$699.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-22 05:57</td>
								<td class="column2">200389</td>
								<td class="column3">Macbook Pro Retina 2017</td>
								<td class="column4">$2199.00</td>
								<td class="column5">1</td>
								<td class="column6">$2199.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-21 05:57</td>
								<td class="column2">200388</td>
								<td class="column3">Game Console Controller</td>
								<td class="column4">$999.00</td>
								<td class="column5">1</td>
								<td class="column6">$999.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-19 05:57</td>
								<td class="column2">200387</td>
								<td class="column3">iPhone X 64Gb Grey</td>
								<td class="column4">$999.00</td>
								<td class="column5">1</td>
								<td class="column6">$999.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-18 05:57</td>
								<td class="column2">200386</td>
								<td class="column3">iPhone X 64Gb Grey</td>
								<td class="column4">$999.00</td>
								<td class="column5">1</td>
								<td class="column6">$999.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-22 05:57</td>
								<td class="column2">200389</td>
								<td class="column3">Macbook Pro Retina 2017</td>
								<td class="column4">$2199.00</td>
								<td class="column5">1</td>
								<td class="column6">$2199.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-21 05:57</td>
								<td class="column2">200388</td>
								<td class="column3">Game Console Controller</td>
								<td class="column4">$999.00</td>
								<td class="column5">1</td>
								<td class="column6">$999.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-19 05:57</td>
								<td class="column2">200387</td>
								<td class="column3">iPhone X 64Gb Grey</td>
								<td class="column4">$999.00</td>
								<td class="column5">1</td>
								<td class="column6">$999.00</td>
							</tr>
							<tr>
								<td class="column1">2017-09-18 05:57</td>
								<td class="column2">200386</td>
								<td class="column3">iPhone X 64Gb Grey</td>
								<td class="column4">$999.00</td>
								<td class="column5">1</td>
								<td class="column6">$999.00</td>
							</tr>

						</tbody>
					</table>
				</div>
				<div class="movementWrapper" style="display: flex;justify-content: space-between;">
					<ul class="pagination">
						<li>
							<a class="btn btn-primary" href="#" aria-label="Previous"> <span aria-hidden="true" style="color: white">&laquo;</span></a>
						</li>
						<li>
							<a class="btn btn-primary" href="#" style="color: white">1</a>
						</li>
						<li>
							<a class="btn btn-primary" href="#" style="color: white">2</a>
						</li>
						<li>
							<a class="btn btn-primary" href="#" style="color: white">3</a>
						</li>
						<li>
							<a class="btn btn-primary" href="#" aria-label="Next"> <span aria-hidden="true"	style="color: white">&raquo;</span></a>
						</li>
					</ul>
					<button class="write-button" type="button" onclick="location.href='board/board-form'">Write</button>
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