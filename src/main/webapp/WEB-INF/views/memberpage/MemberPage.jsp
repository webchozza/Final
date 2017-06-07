<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${pageContext.request.contextPath}/resources/assets/js/memberpage/memberpage.js?v=5"></script>
<title>회원 정보 보기</title>
<style>
#b{
  color: #3d4449;
  text-decoration:none !important
}
</style>
<script>
function gosubmit1_message() {

	var f = document.note;
	f.name_from.value;
	f.method = "post";
	f.action = "/dokky/messagewriteform.do";
	f.submit();
}
//페이지가 로딩될 때 함수실행(팔로우한 사람인지 아닌지 구분하는 값을 인자로 넘겨줌)
$(document).ready(function(){
	followcheck("${followCheck}");
});

$(window).on('popstate', function(event) {
	  var data = event.originalEvent.state;
	  var session_id = $("#session_id").val();
	  var mpch = $("#mpch").val();
	  
	  if(mpch == "on"){
		  $("#movearea").html(data.data);
	  }else{
		  $("#movearea").html("");
	  	  $("#mainarea").html("");
	      $("#mainarea").load("/dokky/MemberPage.do", {
			member_id : session_id,
			ap : "PushState",
	      },function(){
				$("#mpch").val("on");
		});
	  }
	});
	
</script>
</head>
<body>
	<h4>회원 정보</h4><a href="javascript:gosubmit1_message()" class="icon fa-envelope">쪽지</a>
	<span class="image fit"><h2><b>${followCount.member_name}</b></h2></span>
	<div class="box alt" style="width:100%;">
		<div class="row 50% uniform" style="background-color: #f5f6f7; width:60%; display: inline-block;">
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center;"><h3 class="icon fa-hand-paper-o" style="color: #f56a6a;"><b>팔로잉</b></h3></span>
			</div>
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center;"><h3 class="icon fa-hand-o-up" style="color: #f56a6a;"><b>팔로워</b></h3></span>
			</div>
			<!-- Break -->
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center; font-size: 30px;">${followCount.following_count}</span>
			</div>
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center; font-size: 30px;">${followCount.follower_count}</span>
			</div>
		</div>
		<div class="row 100% uniform" style="background-color: #f5f6f7; width:30%; display: inline-block; max-height:30%; position:relative; left:10px; ">
		<div class="4u" style="width: 90%; height: 129px;">
		<div id="me"><span id="plusFollow" class="image fit fa fa-user-plus" style="width:110px; text-align: left; font-size: 25px; color: #f56a6a;"></span></div>
		</div>
		</div>
	</div>
	<hr style="width: 70%; align: left;">
						<div><section id="searchsection" class="alt" style="max-width:300px; min-width:230px;">
					<form action="javascript:;" method="post" id="searchform" >
					<input type="hidden" name="i" id="i" value="${i}"/>
						<input type="text" name="search" id="search" placeholder="Search" />
							<input type="submit" name="ss" onclick="sch()"/>
					</form>
				</section></div>
	<div id="movearea">
	</div>
			<form name="note">
		<input type="hidden" name="name_from" id="name_from"
			value="${followCount.member_name}">
	</form>
<form name="valueform" id="valueform">
<input type="hidden" id="member_id" value="${member_id}"/>
<input type="hidden" name="i" id="i" value="${i}"/>
<input type="hidden" id="path" value="${path}"/>
<input type="hidden" id="session_id" value="${sessionScope.member_id}"/>
<input type="hidden" id="mpch" value="${mpch}"/>
</form>
</body>
</html>