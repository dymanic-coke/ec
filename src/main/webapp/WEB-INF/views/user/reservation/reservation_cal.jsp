<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 날짜 css/js 링크 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap-responsive.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.js"></script>
<!-- 시간 css/js 링크 -->
<script src="${contextPath}/js/wickedpicker.js"></script>
<link rel="stylesheet" href="${contextPath}/css/wickedpicker.css">
<script type="text/javascript">
	$(document).ready(function() {
		//달력 메소드
		$('.datepicker').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true,
			startDate : '0d',
			endDate : '+30d',
			language : 'ko'
		});
		// 시간이 정해지고 선택 되는 메소드
		$('.cell').click(function() {
			$('.cell').removeClass('select');
			$(this).addClass('select');
		});
		//시간 픽업 메소드
		var options = {
			now : "12:00", //hh:mm 24 hour format only, defaults to current time 
			twentyFour : false, //Display 24 hour format, defaults to false 
			upArrow : 'wickedpicker__controls__control-up', //The up arrow class selector to use, for custom CSS 
			downArrow : 'wickedpicker__controls__control-down', //The down arrow class selector to use, for custom CSS 
			close : 'wickedpicker__close', //The close class selector to use, for custom CSS 
			hoverState : 'hover-state', //The hover state class to use, for custom CSS 
			title : 'Timepicker', //The Wickedpicker's title, 
			showSeconds : false, //Whether or not to show seconds, 
			secondsInterval : 1, //Change interval for seconds, defaults to 1 , 
			minutesInterval : 10, //Change interval for minutes, defaults to 1 
			beforeShow : null, //A function to be called before the Wickedpicker is shown 
			show : null, //A function to be called when the Wickedpicker is shown 
			clearable : false, //Make the picker's input clearable (has clickable "x") 
		};
		$('.timepicker').wickedpicker(options);
	});
</script>
<style type="text/css">
/* 달력CSS */
body {
	color: #000;
	overflow-x: hidden;
	background-repeat: no-repeat;
	padding: 0px !important;
	width: 70%;
	float: right;
}

#selectBox {
	height: 700px;
}

.container-fluid {
	padding-top: 120px !important;
	padding-bottom: 120px !important;
	padding-left: 0px !important;
	padding-right: 0px !important;
	height: auto;
}

.row {
	padding-left: 0px !important;
	padding-right: 0px !important;
	width: 100% !important;
}

.card {
	box-shadow: 0px 4px 8px 0px #7986CB;
}

input {
	padding: 10px 20px !important;
	border: 1px solid #000 !important;
	border-radius: 10px;
	box-sizing: border-box;
	background-color: #000 !important;
	color: #fff !important;
	font-size: 16px;
	letter-spacing: 1px;
	width: 180px;
	height: 55px;
	text-align: center;
}

input:focus {
	-moz-box-shadow: none !important;
	-webkit-box-shadow: none !important;
	box-shadow: none !important;
	border: 1px solid #512DA8;
	outline-width: 0;
}

::placeholder {
	color: #fff;
	opacity: 1;
}

:-ms-input-placeholder {
	color: #fff;
}

::-ms-input-placeholder {
	color: #fff;
}

button:focus {
	-moz-box-shadow: none !important;
	-webkit-box-shadow: none !important;
	box-shadow: none !important;
	outline-width: 0;
}

.datepicker {
	background-color: #000 !important;
	color: #fff !important;
	border: none;
	padding: 10px !important;
}

.datepicker-dropdown:after {
	border-bottom: 6px solid #000;
}

thead tr:nth-child(3) th {
	color: #fff !important;
	font-weight: bold;
	padding-top: 20px;
	padding-bottom: 10px;
}

.dow, .old-day, .day, .new-day {
	width: 40px !important;
	height: 40px !important;
	border-radius: 0px !important;
}

.old-day:hover, .day:hover, .new-day:hover, .month:hover, .year:hover,
	.decade:hover, .century:hover {
	border-radius: 6px !important;
	background-color: #eee;
	color: #000;
}

.active {
	border-radius: 6px !important;
	background-image: linear-gradient(#90CAF9, #64B5F6) !important;
	color: #000 !important;
}

.disabled {
	color: #616161 !important;
}

.prev, .next, .datepicker-switch {
	border-radius: 0 !important;
	padding: 20px 10px !important;
	text-transform: uppercase;
	font-size: 20px;
	color: #fff !important;
	opacity: 0.8;
}

.prev:hover, .next:hover, .datepicker-switch:hover {
	background-color: inherit !important;
	opacity: 1;
}

.cell {
	border: 1px solid #BDBDBD;
	margin: 2px;
	cursor: pointer;
}

.cell:hover {
	border: 1px solid #3D5AFE;
}

.cell.select {
	background-color: #3D5AFE;
	color: #fff;
}

.fa-calendar {
	color: #fff;
	font-size: 30px;
	padding-top: 8px;
	padding-left: 5px;
	cursor: pointer;
}
/* 시계CSS */
.para {
	color: white;
}

.container {
	margin-top: 80px;
	width: 800px;
}

.content {
	position: relative;
}

.reservation-tx {
	width: 35%;
	margin-top: 1rem;
	margin-left: 20px;
	margin-right: 20px;
	font-weight: bold;
	font-size: 20px;
	color: white;
	float: left;
}

.reserv-data {
	width: 100%;
	margin-top: 30px;
}

.card-header {
	width: 40%;
	height: 100%;
	float: right;
}

.row justify-content-center mx-0 {
	width: 100% !important;
}
#prod-img{
	width: 60%;
	height: 100%;
}
</style>
<title>예약</title>
</head>
<body>
	<div class="container-fluid px-0 px-sm-4 mx-auto">
		<div class="row justify-content-center mx-0">
			<div class="col-lg-10">
				<div class="card border-0">
					<form id="selectBox" autocomplete="off">
					<img id="prod-img" alt="" src="${contextPath}/image/rain.jpg">
						<div class="card-header bg-dark">
							<div class="mx-0 mb-0 row justify-content-sm-center justify-content-start px-1">
								<div class="reserv-data">
									<p class="reservation-tx">예약 일 :</p>
									<input type="text" id="dp1" class="datepicker" placeholder="예약날짜 선택" name="date" readonly>
										<span class="fa fa-calendar"></span>
								</div>
								<div class="reserv-data">
									<p class="reservation-tx">예약 시간 :</p>
									<input type="text" name="timepicker" class="timepicker" />
								</div>

							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>