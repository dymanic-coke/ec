<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="URL" value="${pageContext.request.requestURL}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
String viewName = (String)request.getAttribute("viewName");
%>
<!DOCTYPE html>
<html>
<head>
<style>
.content-wrapper>.content{
		padding: 0.5em;
	}
.pagination{
	float: right;
	padding-right: 30px;
}
.col-md-9{
	flex: 0 0 100%;
    max-width: 100%;
}
.search-btn{
	float: right;
}
</style>
<script type="text/javascript">
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
        <div class="col-sm-6">
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">메인</a></li>
              <li class="breadcrumb-item active">문의/리뷰관리</li>
              <li class="breadcrumb-item active">리뷰 관리</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-9">
            <div class="card card-primary">
              <div class="card-body p-0">
				<div class="btn-group search-btn">
                        <button type="button" class="btn btn-success" onClick="location.href='${contextPath}/seller/reviewManage?period=today'">오늘</button>
                        <button type="button" class="btn btn-success" onClick="location.href='${contextPath}/seller/reviewManage?period=yesterday'">어제</button>
                        <button type="button" class="btn btn-success" onClick="location.href='${contextPath}/seller/reviewManage?period=all'">전체</button>
                      </div>
              <div class="table-responsive mailbox-messages">
                <table class="table table-hover table-striped">
                <thead height="10" align="center" id="non-hover">
                <td>작성일</td>
				<td>작성자</td>
				<td>리뷰 내용</td>
				<td>별점</td>
				<td>리뷰 좋아요</td>
				<td>답글</td>
			</thead>
                  <c:choose>
				<c:when test="${empty reAnsList}">
					<tr height="10" class="table-primary">
						<td colspan="6">
							<p align="center">
								<b><span style="font-size: 9pt;">등록된 리뷰가 없습니다.</span></b>
							</p>
						</td>
					</tr>
				</c:when>
				<c:when test="${!empty reAnsList}">
					<c:forEach var="reAns" items="${reAnsList}" varStatus="reAnsNum">	
						<tr align="center">
							<td><fmt:formatDate value="${reAns.reg_date}" pattern="yyyy-MM-dd" /></td>
							<td>${reAns.user_nick}(${reAns.user_id})</td>
							<td>${reAns.content}</td>
							<td>
								<div style="CLEAR: both;	PADDING-RIGHT: 0px;	PADDING-LEFT: 0px;	
									BACKGROUND: url(image/icon_star2.gif) 0px 0px;	FLOAT: left;	PADDING-BOTTOM: 0px;
									MARGIN: 0px;	WIDTH: 90px;	PADDING-TOP: 0px;	HEIGHT: 18px;">
									<p style="WIDTH: ${reAns.rating_percent}%; PADDING-RIGHT:0px;	PADDING-LEFT:0px;
										BACKGROUND: url(image/icon_star.gif) 0px 0px;	PADDING-BOTTOM: 0px;
										MARGIN: 0px;	PADDING-TOP: 0px;	HEIGHT: 18px;"></p>
								</div>
								<div>
									<b style="margin-left:5px;font-size:small; color:#8f8f8f;">${reAns.rating}</b>
							</div>
							</td>
							<td>${reAns.liked}</td>
							<td>
								<p>
									<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
								    	답글
									</a>
									</p>
								<div class="collapse" id="collapseExample">
									<div class="card card-body">
								    	Some placeholder content for the collapse component. This panel is hidden by default but revealed when the user activates the relevant trigger.
									</div>
							  	</div>
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>                 
                </table>
                <!-- /.table -->
                <br>
		<nav class="paging">
  			<ul class="pagination pg-darkgrey">
  			<c:if test="${paging.prev == 'false'}">
    			<li class="page-item">
      				<a class="page-link" aria-label="Previous" href="#">
        				<span aria-hidden="true">&laquo;</span>
        				<span class="sr-only">Previous</span>
      				</a>
    			</li>
    		</c:if>
    		<c:if test="${paging.prev == 'true'}">
    			<li class="page-item">
      				<a class="page-link" aria-label="Previous" href="${contextPath}<%=viewName%>?page=${paging.startPage -1}">
        				<span aria-hidden="true">&laquo;</span>
        				<span class="sr-only">Previous</span>
      				</a>
    			</li>
    		</c:if>
    		<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
    			<li <c:out value="${paging.nowPage == i ? 'class= page-item active' : 'class=page-item'}"/>>
    			<a class="page-link" href="${contextPath}<%=viewName%>?page=${i}">${i}</a>
    			</li>
    		</c:forEach>
    		<c:if test="${paging.next == 'false'}">
    			<li class="page-item">
      				<a class="page-link" aria-label="Next"  href="">
        				<span aria-hidden="true">&raquo;</span>
        				<span class="sr-only">Next</span>
      				</a>
    			</li>
    		</c:if>
    		<c:if test="${paging.next == 'true'}">
    			<li class="page-item">
      				<a class="page-link" aria-label="Next"  href="${contextPath}<%=viewName%>?page=${paging.endPage + 1}">
        				<span aria-hidden="true">&raquo;</span>
        				<span class="sr-only">Next</span>
      				</a>
    			</li>
    		</c:if>
  			</ul>
		</nav>
              </div>
              <!-- /.mail-box-messages -->
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
</body>
</html>