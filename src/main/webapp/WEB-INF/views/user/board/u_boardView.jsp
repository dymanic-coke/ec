<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>글보기</title>
<style>
#write{
	margin-top: 5%;
	margin-left: 30%;
	margin-bottom: 10%;
	width: 40%; 
}
#tr_btn_modify {
	display: none;
}
#tr_file_upload{
	display: none;
}
#preview{
	width: 200px;
	height: 200px;
}

#writebtn{
	float: right;
}
#reply-btn{
	float: right;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function backToList(obj){
		obj.action="${contextPath}/user/u_board";
		obj.submit();
	}	
	function fn_modify_article(obj){
		obj.action="${contextPath}/user/modBoard.do";
		obj.submit();
	}
	
	function fn_remove_article(url,list_num){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var list_numInput = document.createElement("input");
		articleNoInput.setAttribute("type", "hidden");
		articleNoInput.setAttribute("name", "list_num");
		articleNoInput.setAttribute("value", list_Num);
		
		form.appendChild(list_numInput);
		document.body.appendChild(form);
		form.submit();
	}
	
	function fn_reply_form(url, parent_num){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var parent_numInput = document.createElement("input");
		parentNoInput.setAttribute("type", "hidden");
		parentNoInput.setAttribute("name", "parent_num");
		parentNoInput.setAttribute("value", parent_num);
		
		form.appendChild(parent_numInput);
		document.body.appendChild(form);
		form.submit();
	}
	
	function fn_mod_form(url, list_num){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var parent_numInput = document.createElement("input");
		parentNoInput.setAttribute("type", "hidden");
		parentNoInput.setAttribute("name", "list_num");
		parentNoInput.setAttribute("value", list_num);
		
		form.appendChild(parent_numInput);
		document.body.appendChild(form);
		form.submit();
	}
	
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
</head>
<body>
	<div id="write">
	<form name="frmBoard" method="post" action="${contextpath}"
		enctype="multipart/form-data">
		<input id="reply-btn" type="button" value="답글쓰기" onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${board.list_num})" />
		<br><br>
		<table border="0" align="center" class="table table-condensed">
			<tr>
				<td width="150" align="center" >
				작성자 아이디
				</td>
				<td>
				${board.user_id}
				<input type="hidden" value="${board.user_id}" name="user_id" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center">
				등록일자
				</td>
				<td>
					<fmt:formatDate value="${board.mod_date}"/>
					<input type="hidden" value="<fmt:formatDate value="${board.mod_date}"/>" disabled />
				</td>
			</tr>
			<tr>
				<td width="150" align="center"">
				제목
				</td>
				<td>
				${board.u_title}
				</td>
			</tr>
			<tr>
				<td width="150" height="300" align="center" style="vertical-align: middle;">
				내용
				</td>
				<td style="vertical-align: middle;">
				${board.u_content}
				</td>
			</tr>
<%-- 
			<c:if
				test="${not empty article.imageFileName && article.imageFileName !='null'}">
				<tr>
					<td width="150" align="center" bgcolor="#ff9933" rowspan="2">
						이미지</td>
					<td><input type="hidden" name="orignalFileName"
						value="${article.imageFileName}" /> <img
						src="${contextPath}/download.do?articleNo=${article.articleNo}&imageFileName=${article.imageFileName}"
						id="preview" /><br></td>
				</tr>
				<tr>
					<td><input type="file" name="imageFileName"
						id="i_imageFileName" disabled onchange="readURL(this)" /></td>
				</tr>
			</c:if>
--%>
		<c:choose>
			<c:when test="${not empty board.image_fileName && board.image_fileName !='null'}">
				<tr>
					<td width="150" align="center">
					이미지보기
					</td>
					<td>
					<input type="hidden" name="orignalFileName" value="${board.image_fileName}" />
					<img src="${contextPath}/download.do?list_num=${board.list_num}&image_fileName=${board.image_fileName}" id="preview" /><br>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="file" name="image_fileName" id="i_imageFileName" disabled onchange="readURL(this);" />
					</td>
				</tr>	
			</c:when>
			<c:otherwise>
				<tr id="tr_file_upload">
					<td>
						<input type="hidden" name="orignalFileName" value="${board.image_fileName}" />
					</td>
				</tr>
				<tr>
					<td width="150" align="center">이미지보기</td>
					<td>
						이미지 파일 없음
					</td>
				</tr>
			</c:otherwise>
		</c:choose>				
		</table>
		<div id="writebtn">
			<c:if test="${member.user_id==board.user_id}">
				<input type="button" value="수정하기" onClick="fn_reply_form('${contextPath}/user/u_board/modForm', ${board.list_num})" />
				<input type="button" value="삭제하기" onClick="fn_remove_article('${contextPath}/user/removeBoard', ${board.list_num})" />
			</c:if>
				<input type="button" value="리스트로 돌아가기" onClick="backToList(this.form)" />
				
		</div>
	</form>
	</div>
</body>
</html>