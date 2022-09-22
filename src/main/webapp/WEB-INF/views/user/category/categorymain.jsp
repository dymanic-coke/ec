<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="com.spring.ec.user.vo.StoreVO, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <!-- Bootstrap CSS,js -->
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
   crossorigin="anonymous"></script>
 
 
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
   crossorigin="anonymous">
 <link href="css/styles.css" rel="stylesheet" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<title>카테고리 상세</title>
<style>
body {
height: 100vh;
}
[_nghost-ewg-c62] .map_container[_ngcontent-ewg-c62] .sidebar[_ngcontent-ewg-c62] {
    position: relative;
    z-index: 210;
    box-sizing: border-box;
}

[_nghost-ewg-c33] {
    display: flex;
    flex-direction: column;
    width: 63px;
    height: 100vh;
    left:0px;
    background-color: #fff;
    border-right: 1px solid #CCCCCC;
    
    position:absolute;
    top: 0;
    z-index:1;
}

[_nghost-ewg-c33] .logo_box[_ngcontent-ewg-c33] {
    display: flex;
    flex: 0 0 auto;
    justify-content: center;
    align-items: center;
    height: 76px;
    border-bottom: 1px solid rgba(0,0,0,.15);
    box-sizing: border-box;
}

[_nghost-ewg-c33] .logo_box[_ngcontent-ewg-c33] .link_logo[_ngcontent-ewg-c33] {
    display: inline-block;
    vertical-align: top;
    padding: 10px;
    font-size: 1px;
    line-height: 1px;
}


[_nghost-ewg-c33] .scroll_area[_ngcontent-ewg-c33] {
    margin-bottom: auto;
}

perfect-scrollbar {
    display: block;
    height: 100%;
    max-height: 100%;
    max-width: 100%;
    overflow: hidden;
    position: relative;
    width: 100%;
}

perfect-scrollbar>.ps {
    display: block;
    height: 100%;
    max-height: 100%;
    max-width: 100%;
    position: static;
    width: 100%;
}

.ps {
    overflow: hidden!important;
    overflow-anchor: none;
    touch-action: auto;
}

[_nghost-ewg-c33] .list_navbar[_ngcontent-ewg-c33] {
    width: 63px;
    padding:0;
}

li, ol, ul {
    list-style: none;
}

li {
    display: list-item;
    text-align: -webkit-match-parent;
}



[_nghost-ewg-c33] .list_navbar[_ngcontent-ewg-c33] .link_navbar.active[_ngcontent-ewg-c33] {
    color: #fff;
}

[_nghost-ewg-c33] .list_navbar[_ngcontent-ewg-c33] .link_navbar[_ngcontent-ewg-c33] {
    display: block;
    position: relative;
    z-index: 10;
    padding: 13px 1px 11px 0;
    color: #333;
    text-align: center;
}

[_nghost-ewg-c33] .list_navbar[_ngcontent-ewg-c33] .navbar_text[_ngcontent-ewg-c33] {
    display: block;
    color:black;
    min-height: 13px;
    margin-top: 3px;
    font-size: 12px;
    line-height: 15px;
    letter-spacing: -.44px;
}

[_nghost-ewg-c33] .list_navbar[_ngcontent-ewg-c33] .link_navbar[_ngcontent-ewg-c33] {
    display: block;
    position: relative;
    z-index: 10;
    padding: 13px 1px 11px 0;
    color: #333;
    text-align: center;
}
[_nghost-ewg-c33] .list_navbar[_ngcontent-ewg-c33] .link_navbar.active[_ngcontent-ewg-c33] {
    color: #fff;
}

/* 검색 결과 */
#searchlist {
	margin-left:62px;
	padding-left : 10px;
	width: 330px;
	height: 100vh;
	background-color:white;
	border-left: 1px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
	position:absolute;
	z-index: 1;
	top:0;
	overflow-x:hidden;
	overflow-y:auto; 

}



[_nghost-ewg-c74] {
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 100;
    width: 340px;
    margin: auto;
    padding: 15px 25px;
}

[_nghost-ewg-c74] .search_wrap[_ngcontent-ewg-c74], .static[_nghost-ewg-c74] {
    position: relative;
}

[_nghost-ewg-c74] .search_box[_ngcontent-ewg-c74] {
    position: relative;
    z-index: 110;
    height: 42px;
    padding-left: 45px;
    border: 2px solid #19ce60;
    border-radius: 5px;
}

[_nghost-ewg-c74] .button_search[_ngcontent-ewg-c74] {
    overflow: hidden;
    display: inline-block;
    width: 25px;
    height: 24px;
    font-size: 0;
    color: transparent;
    vertical-align: top;
    background-position: 0 -112px;
    position: absolute;
    top: 9px;
    left: 15px;
}

[_nghost-ewg-c74] .button_clear[_ngcontent-ewg-c74]:after, [_nghost-ewg-c74] .button_clear[_ngcontent-ewg-c74]:hover:after, [_nghost-ewg-c74] .button_search[_ngcontent-ewg-c74] {
    background-image: url(https://ssl.pstatic.net/static/maps/v5/pc/20220531112111/search.png);
    background-size: 194px 190px;
}

[_nghost-ewg-c74] .input_box[_ngcontent-ewg-c74] {
    position: relative;
    z-index: 0;
    padding-right: 43px;
}
[_nghost-ewg-c74] .input_search[_ngcontent-ewg-c74] {
    position: relative;
    color: #000;
    font-weight: 700;
}

[_nghost-ewg-c74] .input_search[_ngcontent-ewg-c74], [_nghost-ewg-c74] .label_search[_ngcontent-ewg-c74] {
    display: block;
    width: 100%;
    font-size: 16px;
    height: 42px;
    letter-spacing: -.2px;
}

body.windows .base_map, body.windows .nm-tooltip, body.windows button, body.windows frequent-place-use-agreement-popup, body.windows input, body.windows label, body.windows layout, body.windows select, body.windows textarea {
    font-family: notosanskr,Malgun Gothic,맑은 고딕,Dotum,돋움,sans-serif;
}

[_nghost-ewg-c74] .button_clear[_ngcontent-ewg-c74] {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    z-index: 10;
    width: 43px;
    font-size: 1px;
    line-height: 1px;
    background: #fff;
    border-radius: 0 5px 5px 0;
}



/* 상세조회 시 배경 투명도 조절  */
.offcanvas-backdrop.show {
    opacity: 0;
}

/* 지도  */
.region{
	z-index: 1;
	position: absolute;
	top:20px;
	right: 0px;
}
#map{

}
.customoverlay {position:relative;bottom:70px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;}
.customoverlay:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.customoverlay a {display:block;text-decoration:none;color:#000;text-align:center;border-radius:6px;font-size:14px;font-weight:bold;overflow:hidden;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.customoverlay .title {display:block;text-align:center;background:#fff;margin-right:35px;padding:10px 15px;font-size:14px;font-weight:bold;}
.customoverlay:after {content:'';position:absolute;margin-left:-12px;left:50%;bottom:-12px;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
</style>


<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6f71f623090760bfbef2d5a7bb7bb54a&libraries=services,clusterer,drawing"></script>
</head>
<body>
<!--지도  -->
<div id="map" style="width:100%;height:969px;"></div>
<p>
 <!-- 지도타입 컨트롤 div 입니다 -->
    <div class="region">
        <button onclick="setSeoul()">서울</button>
        <button onclick="setDaejeon()">대전</button>
        <button onclick="setBusan()">부산</button>
    </div>
</p>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.5666805, 126.9784147), // 지도의 중심좌표
        level: 7 // 지도의 확대 레벨
    };
    
//지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

//대전으로 화면 이동
function setDaejeon() {            
    // 이동할 위도 경도 위치를 생성합니다 
    map.setCenter(new kakao.maps.LatLng(36.321655, 127.378953));
}
//서울로 화면 이동
function setSeoul() {            
    // 이동할 위도 경도 위치를 생성합니다 
    map.setCenter(new kakao.maps.LatLng(37.5666805, 126.9784147));
}
//부산으로 화면 이동
function setBusan() {            
    // 이동할 위도 경도 위치를 생성합니다 
    map.setCenter(new kakao.maps.LatLng(35.1795543, 129.0756416));
} 
// 버튼을 클릭하면 아래 배열의 좌표들이 모두 보이게 지도 범위를 재설정합니다
var geocoder = new kakao.maps.services.Geocoder();
var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markers_sprites.png', // 마커이미지의 주소입니다    
imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);


var list =[];

<c:forEach items="${storeList}" var="item">
list.push({addr:'${item.seller_addr}', name:'${item.seller_name}'});
</c:forEach>
list.forEach(function(sell, index){
	geocoder.addressSearch(sell.addr,function(result, status){
		if (status === daum.maps.services.Status.OK) {
		    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		    var imageSrc = 'https://map.pstatic.net/res/category/image/00023-00032.png', // 마커이미지의 주소입니다    
		    imageSize = new kakao.maps.Size(32, 35); // 마커이미지의 크기입니다
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
		 var marker = new kakao.maps.Marker({
			 map:map,
			 position: coords,
			 title: sell.name,
			 image: markerImage
		});
		 var content = '<div class="customoverlay">' +
		    '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
		    '    <span class="title">'+
		    sell.name +
		    '</span>' +
		    '  </a>' +
		    '</div>';

		// 커스텀 오버레이가 표시될 위치입니다 

		// 커스텀 오버레이를 생성합니다
		var customOverlay = new kakao.maps.CustomOverlay({
		    map: map,
		    position: coords,
		    content: content
		});
		};
	});
	});
function setCenter(addr) {            
    
    geocoder.addressSearch(addr,function(result, status){
		if (status === daum.maps.services.Status.OK) {
		    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		   
		    map.setCenter(coords);
		}
    // 지도 중심을 이동 시킵니다
    });   
}
	/* for(var i =0; i<${storeList.size()}; i++){
		geocoder.addressSearch(,function(result, status){
			if (status === daum.maps.services.Status.OK) {
				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				}
			 var marker = new kakao.maps.Marker({
				map: map,
				position: coords,
				title: '${storeList[0]}',
				clickable: true
			});
			var iwContent = '<div style="padding:5px;></div>',
				iwRemoveable = true;
			
			var infowindow = new kakao.maps.InfoWindow({
				content: iwContent,
				removable: iwRemoveable
			});
			
			kakao.maps.event.addListener(marker, 'click', function(){
				infowindow.open(map, marker);
			}); 
		});
		}
} */
			/* var marker = new kakao.maps.Marker({
				map: map,
				position: geocoder.addressSearch(positions[0].addr,callback),
				clickable: true
			});
			var iwContent = '<div style="padding:5px;></div>',
				iwRemoveable = true;
			
			var infowindow = new kakao.maps.InfoWindow({
				content: iwContent,
				removable: iwRemoveable
			});
			
			kakao.maps.event.addListener(marker, 'click', function(){
				infowindow.open(map, marker);
			});	 */
</script>

<navbar _ngcontent-ewg-c62 _nghost-ewg-c33>
	<h1 _ngcontent-ewg-c33 class="logo_box">
		<a _ngcontent-ewg-c33 href="http://www.naver.com" class="link_logo">
		<img alt="메인로고" src="image/mainlogo.png">
		</a>
	</h1>
	<perfect-scrollbar _ngcontent-ewg-c33 class="scroll_area">
		<div style="position: static;" class="ps">
			<div class="ps-content">
				<div _mgcpmtemt-ewg-c33 class="scroll_box">
					<ul _ngcontent-ewg-c33 class="list_navbar">
						<li _ngcontent-ewg-c33 class="item_navbar">
						<a _ngcontent-ewg-c33 href="#" class="link_navbar home active"> 
							<span _ngcontent-ewg-c33 class="navbar_text">회원가입</span>
						</a>
						</li>
						<li _ngcontent-ewg-c33 class="item_navbar">
						<a _ngcontent-ewg-c33 href="#" class="link_navbar home active"> 
							<span _ngcontent-ewg-c33 class="navbar_text">로그인</span>
						</a>
						</li>
						<li _ngcontent-ewg-c33 class="item_navbar">
						<a _ngcontent-ewg-c33 href="#" class="link_navbar home active"> 
							<span _ngcontent-ewg-c33 class="navbar_text">찜</span>
						</a>
						</li>
						<li _ngcontent-ewg-c33 class="item_navbar">
						<a _ngcontent-ewg-c33 href="#" class="link_navbar home active"> 
							<span _ngcontent-ewg-c33 class="navbar_text">예약조회</span>
						</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
 	 </perfect-scrollbar>
</navbar>
<section id="searchlist">
<!--검색창  -->
    <form class="d-flex" >
      <input class="form-control me-2" type="search" placeholder="키워드, 지역 등을 입력" aria-label="Search" style="margin-top:21px;">
      <button class="btn" type="submit" style="margin-top:10px;margin-right: 10px; padding: 0px; font-size:25px;">🔍</button>
    </form>
    <hr style="margin-right: 10px;">
<!--검색 결과 리스트 -->

<c:choose>
		<c:when test="${empty storeList }">
			<b><span style="font-size:9pt;">검색 결과가 없습니다.</span></b>
		</c:when>
		<c:when test="${!empty storeList }">
			<c:forEach var="store" items="${storeList }" varStatus="storeNum">
					<div class="card" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample" onClick="setCenter('${store.seller_addr}');" style="width: 18rem;margin-top:10px;">
	 					<img src="https://ldb-phinf.pstatic.net/20220919_260/16635542007495NypM_JPEG/KakaoTalk_20220919_112238901.jpg" class="card-img-top" alt="..." width="286" height="180" style="object-fit:contain;">
	  				<div class="card-body">
	   				 <p class="card-text">
	    				<h1>${store.seller_name }</h1>
	    				${store.seller_addr }
	    			</p>
	  				</div>
  				</div>
  			</c:forEach>
		</c:when>
	</c:choose>

<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="offcanvasExampleLabel">Offcanvas</h5>
    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    <div>
      Some text as placeholder. In real life you can have the elements you have chosen. Like, text, images, lists, etc.
    </div>
    <div class="dropdown mt-3">
      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown">
        Dropdown button
      </button>
      <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
        <li><a class="dropdown-item" href="#">Action</a></li>
        <li><a class="dropdown-item" href="#">Another action</a></li>
        <li><a class="dropdown-item" href="#">Something else here</a></li>
      </ul>
    </div>
  </div>
 </section>
</div>




</body>
</html>