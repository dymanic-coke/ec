<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, java.io.*"
    isELIgnored="false"
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<c:choose>
	<c:when test="${result=='loginFailed' }">
	<script>
	window.onload=function(){
		alert("아이디나 비밀번호가 틀립니다. 다시로그인하세요!")
	}
	</script>
	</c:when>
	</c:choose>
<meta charset="UTF-8">
<title>로그인창</title>
</head>
<body>
<form method="get" action="${contextPath}/user/login.do">
		<h1 align="center">회원</h1>
		<table>
		<tr>
		<td><input type="radio" name="select" value="user" />사용자</td>
		<td></td>
		<td><input type="radio" name="select" value="seller" />판매자</td>
		</tr>
		
		<tr height="2">
		<td colspan="3"><input type="text" name="id" size="20" placeholder="아이디"><br><br></td>
		</tr>      
        <tr height="2">
        <td colspan="3"> <input type="password" name="pwd" size="20" placeholder="비밀번호"><br></td>
        </tr>
        
        <tr>
        <td><input type="checkbox">ID 저장<br><br></td>
        <td></td>
        <td></td>
        </tr>
    
         <tr height="2">
        <td colspan="3"><input type="submit" name="login" size="20" value="로그인"><br><br><br></td>
        </tr>   
 
            <tr>
            <td><a href="find_pwd.jsp"></a>비밀번호 찾기</td>
            <td><td>
            <td><a href="find_id.jsp"></a>아이디찾기</td>
            <td><td>
            <td><a href="userForm.jsp"></a>회원가입</td>
        	</tr>
      </table>
   </form>
</body>
</html>