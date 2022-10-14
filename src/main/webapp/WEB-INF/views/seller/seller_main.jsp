<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://uicdn.toast.com/calendar/latest/toastui-calendar.min.css" />
<script src="https://uicdn.toast.com/calendar/latest/toastui-calendar.min.js"></script>
<style type="text/css">
	.content-wrapper>.content{
		padding: 1em 0.5em;
	}
	.col-lg-3{
		flex: 0 0 33.3%;
		max-width: 33.3%;
	}
	.main-header-block{
		display: block;
	}
	.main-header-margin{
		margin: 0 auto;
	}
	.toastui-calendar-template-monthDayName{
		text-align: center;
	}
	.toastui-calendar-day-name-item{
		font-size: 20px !important;
	}
	.toastui-calendar-grid-cell-date{
		font-size: 18px;
	}
	.bg-gradient-success {
    background: #80cebe linear-gradient(180deg,#9fdebd,#80cebe) repeat-x!important;
    color: #fff;
</style>
<script type="text/javascript">
	$(document).ready(function() {
		const Calendar = tui.Calendar;
		const container = document.getElementById('calendar');
		const options = {
		  defaultView: 'month',
		  usageStatistics: false,
		  isReadOnly: true,
		  theme:{
			  common:{
				  backgroundColor: '#80cebe linear-gradient(180deg,#9fdebd,#80cebe) repeat-x!important'
			  }
		  }
		  
		};
		const calendar = new Calendar(container, options);
		<c:forEach items="${bookList}" var="item">
	    <fmt:parseDate var="startDate" value="${item.reserv_date}"  pattern="yyyy-MM-dd"/>
	    <fmt:parseDate var="startTime" value="${item.reserv_time}"  pattern="HH:mm"/>
	    
	    var y = '<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd HH:mm:ss" />';
	   	var m = '<fmt:formatDate value="${startTime}" pattern="HH:mm" />';
		
		calendar.createEvents([			
			  {
				id: 'event1',
				calendarId: 'cal2',
				title: '',
				category: 'time',
				allDay: true,
				start: '<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd" />',
				end: '<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd" />',
				  },			  
			]);
		  </c:forEach>
		calendar.setOptions({
			  template: {
			    time(event) {
			      const { start, end, title } = event;

			      return `<span style="color: blue;">예약 있음</span>`;
			    },
			  }
			});
		var ticksStyle = {
			    fontColor: '#495057',
			    fontStyle: 'bold'
			  }

		var mode = 'index'
		var intersect = true
		var $visitorsChart = $('#visitors-chart')
		  // eslint-disable-next-line no-unused-vars
		  var visitorsChart = new Chart($visitorsChart, {
		    data: {
		      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		      datasets: [{
		        type: 'line',
		        data: [33, 24, 30, 55, 90, 53, 13, 74, 47, 10, 30, 50],
		        backgroundColor: 'transparent',
		        borderColor: 'red',
		        pointBorderColor: 'red',
		        pointBackgroundColor: 'red',
		        fill: false
		      },
		      {
		        type: 'line',
		        data: [44, 42, 24, 34, 43, 83, 84, 46, 64, 9, 10, 20],
		        backgroundColor: 'tansparent',
		        borderColor: 'blue',
		        pointBorderColor: 'blue',
		        pointBackgroundColor: 'blue',
		        fill: false
		      },
		      {
			        type: 'line',
			        data: [22, 10, 30, 80, 130, 20, 10, 40, 45, 65, 35, 50],
			        backgroundColor: 'tansparent',
			        borderColor: 'green',
			        pointBorderColor: 'green',
			        pointBackgroundColor: 'green',
			        fill: false
			      }]
		    },
		    options: {
		      maintainAspectRatio: false,
		      tooltips: {
		        mode: mode,
		        intersect: intersect
		      },
		      hover: {
		        mode: mode,
		        intersect: intersect
		      },
		      legend: {
		        display: false
		      },
		      scales: {
		        yAxes: [{
		          // display: false,
		          gridLines: {
		            display: true,
		            lineWidth: '4px',
		            color: 'rgba(0, 0, 0, .2)',
		            zeroLineColor: 'transparent'
		          },
		          ticks: $.extend({
		            beginAtZero: true,
		          }, ticksStyle)
		        }],
		        xAxes: [{
		          display: true,
		          gridLines: {
		            display: false
		          },
		          ticks: ticksStyle
		        }]
		      }
		    }
		  })

	  });
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-info">
              <div class="inner">
                <h3>150</h3>
                <p>금일 예약 건 수</p>
              </div>
              <div class="icon">
                <i class="ion ion-calendar"></i>
              </div>
              <a href="#" class="small-box-footer">확인&nbsp;<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-success">
              <div class="inner">
                <h3>53</h3>

                <p>금일 찜 수</p>
              </div>
              <div class="icon">
                <i class="ion ion-ios-heart"></i>
              </div>
              <a href="#" class="small-box-footer">확인&nbsp;<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-warning">
              <div class="inner">
                <h3>44</h3>

                <p>금일 리뷰 수</p>
              </div>
              <div class="icon">
                <i class="ion ion-android-contact"></i>
              </div>
              <a href="#" class="small-box-footer">확인&nbsp;<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
        </div>
        <!-- /.row -->
        <!-- Main row -->
        <div class="row">
          <!-- Left col -->
          <section class="col-lg-7 connectedSortable">
            <!-- Custom tabs (Charts with tabs)-->
            <div class="card">
              <div class="card-header border-0">
                <div class="d-flex justify-content-between">
                  <h3 class="card-title">Online Store Visitors</h3>
                  <a href="javascript:void(0);">View Report</a>
                </div>
              </div>
              <div class="card-body">
                <div class="d-flex">
                  <p class="d-flex flex-column">
                    <span class="text-bold text-lg">820</span>
                    <span>Visitors Over Time</span>
                  </p>
                  <p class="ml-auto d-flex flex-column text-right">
                    <span class="text-success">
                      <i class="fas fa-arrow-up"></i> 12.5%
                    </span>
                    <span class="text-muted">Since last week</span>
                  </p>
                </div>
                <!-- /.d-flex -->

                <div class="position-relative mb-4">
                  <canvas id="visitors-chart" height="200"></canvas>
                </div>

                <div class="d-flex flex-row justify-content-end">
                  <span class="mr-2">
                    <i class="fas fa-square text-primary"></i> This Week
                  </span>

                  <span>
                    <i class="fas fa-square text-gray"></i> Last Week
                  </span>
                </div>
              </div>
            </div>
            <!-- /.card -->
            <!-- TO DO List -->
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">공지사항</h3>

                <div class="card-tools">
                  <ul class="pagination pagination-sm float-right">
                    <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                  </ul>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body p-0">
                <table class="table">
                  <thead>
                    <tr align="center">
                      <th style="width: 100px">번호</th>
                      <th>공지 제목</th>
                      <th>작성일</th>
                      <th style="width: 100px">더 보기</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr align="center">
                      <td>1</td>
                      <td>공지사항이다 이놈들아~</td>
                      <td>2022-10-10</td>
                      <td>
                      <a href="#" class="text-muted">
                      <i class="fas fa-search"></i>
                      </a>
                      </td>
                    </tr>
                    <tr align="center">
                      <td>2</td>
                      <td>공지사항이다 이놈들아~</td>
                      <td>2022-10-10</td>
                      <td>
                      <a href="#" class="text-muted">
                      <i class="fas fa-search"></i>
                      </a>
                      </td>
                    </tr>
                    <tr align="center">
                      <td>3</td>
                      <td>공지사항이다 이놈들아~</td>
                      <td>2022-10-10</td>
                      <td>
                      <a href="#" class="text-muted">
                      <i class="fas fa-search"></i>
                      </a>
                      </td>
                    </tr>
                    <tr align="center">
                      <td>4</td>
                      <td>공지사항이다 이놈들아~</td>
                      <td>2022-10-10</td>
                      <td>
                      <a href="#" class="text-muted">
                      <i class="fas fa-search"></i>
                      </a>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
          </section>
          <!-- /.Left col -->
          <!-- right col (We are only adding the ID to make the widgets sortable)-->
          <section class="col-lg-5 connectedSortable">
            <!-- Calendar -->
            <div class="card bg-gradient-success">
              <div class="card-header border-0">

                <h3 class="card-title">
                  <i class="far fa-calendar-alt"></i>
                  예약 현황
                </h3>
                <!-- tools card -->
                <div class="card-tools">
                  <!-- button with a dropdown -->
                  <div class="btn-group">
                    <a href="${contextPath}/seller/bookingStatus" class="btn btn-sm btn-tool">
                      <i class="fas fa-bars"></i>
                    </a>
                  </div>
                </div>
                <!-- /. tools -->
              </div>
              <!-- /.card-header -->
              <div class="card-body pt-0">
                <!--The calendar -->
                <div id="calendar" style="height: 600px;"></div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </section>
          <!-- right col -->
        </div>
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
</body>
</html>