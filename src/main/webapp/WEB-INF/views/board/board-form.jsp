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
textarea{
	width: 96%;
	height: 600px;
	margin-top: 30px;
	margin-bottom: 30px;
	border: none;
	resize: none;
}
</style>
</head>
<body>

	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
				<form action="/board/board-upload" enctype="multipart/form-data" method="post">
					<table>
							<tr>
								<td class="column1">Title :</td>
								<td class="column2" colspan="2"><input type="text" name="title" style="width: 100%;height: 100%;" required="required" maxlength="100"/></td>
								<td class="column4">ID : </td>
								<td class="column5"><input type="text" name="userId" style="height: 100%;" required="required" maxlength="15"/></td>
							</tr>
							<tr>
								<td class="column1" colspan="2"><input type="file" name="files" multiple/></td>
								<td class="column3"></td>
								<td class="column4">Password : </td>
								<td class="column5"><input type="password" name="password" style="height: 100%;" maxlength="4"/></td>
							</tr>
							<tr>
								<td class="column1" colspan="5"><textarea name="content" required="required" maxlength="2000"></textarea></td>
							</tr>
					</table>
				<div class="movementWrapper" style="display: flex;justify-content: space-between;">
					<button class="write-button" type="button" onclick="location.href='board/board-list'">Back</button>
					<button class="write-button" type="submit">Regist</button>
				</div>
				</form>
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