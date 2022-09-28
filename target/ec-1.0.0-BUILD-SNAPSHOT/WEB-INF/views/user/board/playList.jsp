<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- 
<c:set var="articlesList" value="${articlesMap.articlesList}" />
<c:set var="totArticles" value="${articlesMap.totAricles}" />
<c:set var="section" value="${articlesMap.section}" />
<c:set var="pageNum" value="${articlesMap.pageNum}" />
--%>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<style>
#board {
	margin-top: 5%;
	margin-left: 25%;
	margin-bottom: 10%;
	width: 60%;
}
#non-hover {
	pointer-events: none;
	border-bottom: 1px solid;
}

.no-uline {
	text-decoration: none;
}

.sel-page {
	text-decoration: none;
	color: red;
}

.cls1 {
	text-decoration: none;
	
}

.cls2 {
	text-align: center;
	font-size: 30px;
}

#writeBoard{
	float: right;
}

#img-eatList{
	width: 100%;
	vertical-align: center;
}
#img-seeList{
	width: 100%;
	vertical-align: center;
}
</style>
<script>
	function fn_boardForm(isLogOn, boardForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = boardForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.(임시 작업중)")
			location.href = boardForm;
		}
	}
</script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>글목록 창</title>
</head>
<body>
	<section id="board">
		<div class="btn-group">
			<a href="${contextPath}/user/u_board" class="btn btn-primary">전체</a> 
			<a href="${contextPath}/user/u_board/eatpl" class="btn btn-primary">먹플리</a> 
			<a href="${contextPath}/user/u_board/seepl" class="btn btn-primary">볼플리</a>
		</div>
		<br><br>
		<table align="center" width="80%" class="table table-hover" >
			<thead height="10" align="center" id="non-hover">
				<td></td>
				<td>글번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
				<td>조회수</td>
			</thead>
			<c:choose>
				<c:when test="${empty boardsList}">
					<tr height="10" class="table-primary">
						<td colspan="4">
							<p align="center">
								<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
							</p>
						</td>
					</tr>
				</c:when>
				<c:when test="${!empty boardsList}">
					<c:forEach var="board" items="${boardsList}" varStatus="boardNum">
					<a class="cls1" href="${contextPath}/user/u_boardView.do?list_num=${board.list_num}">
						<tr align="center" class="table-primary" onClick="location.href='${contextPath}/user/u_board/u_boardView.do?list_num=${board.list_num}'">
							<c:choose>
								<c:when test="${board.category_code==1}">
								<td width="10%">
									<img id="img-eatList" alt="" src="${contextPath}/image/eat_icon.png">
								</td>
								</c:when>
								<c:otherwise>
									<td width="10%">
									<img id="img-seeList" alt="" src="${contextPath}/image/see_icon.png">
									</td>
								</c:otherwise>
							</c:choose>
							<td width="10%">${fn:length(boardsList)-boardNum.index}</td>
							<td width="10%">${board.user_id}</td>
							<td align="center" width="35%">${board.u_title}</td>
							<td width="15%"><fmt:formatDate value="${board.mod_date}" /></td>
							<td width="10%">${board.hits}</td>
						</tr>
						</a>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
		<button type="button" class="btn btn-light" id="writeBoard" onClick="fn_boardForm('${isLogOn}', '${contextPath}/user/u_board/boardForm', '${contextPath}/member/loginForm.do')">글쓰기</button>
	</section>
</body>
</html>