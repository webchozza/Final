<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" href="/dokky/resources/assets/css/main.css" />
<script src="resources/assets/js/main/header.js?v=5"></script>
<script src="resources/assets/js/main/weather.js?v=3"></script>
<script src="https://code.jquery.com/jquery-2.2.1.js"></script>
<title>Header</title>
<style>
.fa-microphone{
text-align: right;
font-size: 30px;
}

.fa-microphone-slash{
text-align: right;
font-size: 30px;
}

button[name=bb]{
display:none;
}

input[name=ss] {
display:none;
}
</style>
<script>
$(document).ready(function(){
	if(window.innerWidth > 500){
		$("#logo").html('<div style="position:relative; left:150px; bottom:50px;"><a href="/dokky/main.do"><img src="/dokky/resources/images/logo3.png" width="180px; height:130px;"></a></div>');
	}else if(window.innerWidth <= 500){
		$("#logo").html('');
	}
});

$(window).resize(function(){
	if(window.innerWidth > 500){
		$("#logo").html('');
		$("#logo").html('<div style="position:relative; left:150px; bottom:50px;"><a href="/dokky/main.do"><img src="/dokky/resources/images/logo3.png" width="180px; height:130px;"></a></div>');
	}else if(window.innerWidth <= 500){
		$("#logo").html('');
	}
});
</script>
</head>
<body>
	<!-- 헤더 (윗부분)-->
	<header id="header">
		<div style="text-align:left; min-width:150px; max-width:30%;" id="weather"></div>
		<span id="logo"><div style="position:relative; left:150px; bottom:50px;"><a href="/dokky/main.do"><img src="/dokky/resources/images/logo3.png" width="180px; height:130px;"></a></div></span>
		<button name="bb" id="btn-mic" class="off"></button>
			<input type="hidden" id="micCheck" value="off"/>
			<span id="soundInput"></span>
			<a href="javascript:;" onclick="micOn()" id="micImg" class="icon fa-microphone-slash" style="position:relative; right:40px; top:80px; max-width:20%; min-width:200px; height:50%; font-size:2em;">음성 검색</a>
	</header>
</body>
</html>