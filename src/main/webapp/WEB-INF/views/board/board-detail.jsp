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
tr>th, tr>td{
	text-align: center !important;
}
.column1{
	width: 50px;
}
.column2{
	width: 360px;
}
.column3{
	width: 50px;
}
.column4{
	width: 110px;
}
.column5{
	width: 50px;
}
.column6{
	width: 150px;
}
</style>
</head>
<body>

	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
					<table>
							<tr>
								<td class="column1">Title :</td>
								<td class="column2">${datas.board.title}</td>
								<td class="column3">ID : </td>
								<td class="column4">${datas.board.userId}</td>
								<td class="column5">date :</td>
								<td class="column6">${datas.board.detailDate}</td>
							</tr>
							<tr>
								<td class="column1"></td>
								<td class="column2"></td>
								<td class="column3">Views :</td>
								<td class="column4">${datas.board.viewCount}</td>
								<td class="column5">Likes :</td>
								<td class="column6"><form action="/board/board-like" method="post">${datas.board.recCount}<input type="hidden" name="bdIdx" value="${datas.board.bdIdx}"> <button type="submit" class="fa fa-thumbs-up" aria-hidden="true"></button></form></td>
							</tr>
							<tr>
								<td class="column1" colspan="6"><textarea name="content" required="required" maxlength="2000" readonly="readonly">${datas.board.content}</textarea></td>
							</tr>
							<tr>
								<td class="column1" colspan="6" style="text-align: left !important;">Files :</td>
							</tr>
							<c:forEach items="${datas.files}" var="file">
							<tr>
								<td class="column1" colspan="6" style="text-align: left !important;"><a href="${file.downloadURL}">${file.originFileName} <i class="fa fa-floppy-o" aria-hidden="true"></i></a></td>
							</tr>
							</c:forEach>
					</table>
				<div class="movementWrapper" style="display: flex;justify-content: space-between;">
					<button class="write-button" type="button" onclick="location.href='board/board-list'">Back</button>
					<div>
						<button class="write-button" type="button" data-toggle="modal" data-target="#myModal${datas.board.bdIdx}">Delete</button>
						<button class="write-button" type="button" onclick="location.href='board/board-modify?bdIdx=${datas.board.bdIdx}'">Update</button>
					</div>	
						<div class="modal fade" id="myModal${datas.board.bdIdx}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						  <div class="modal-dialog" role="document">
						    <form action="/board/board-delete" method="post">
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							      </div>
							      <div class="modal-body" style="display: flex;">
							       <br>
							        <div style="text-align: left; display: flex;">Password : <input type="password" name="password"><input type="hidden" name="bdIdx" value="${datas.board.bdIdx}"></div>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #36304a;color: white;">Close</button>
							        <button type="submit" class="btn btn-primary" style="background-color: #36304a;">Delete</button>
							      </div>
							    </div>
						    </form>
						  </div>
						</div>
				</div>
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