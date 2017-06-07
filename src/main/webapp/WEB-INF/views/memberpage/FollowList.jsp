<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<script src="resources/assets/js/memberpage/follow.js?v=3"></script>
<title>DOKKY</title>
<style>
input[name=ss] {
display:none;
}

a {
border-bottom-color: #FFFFFF;
	color: #f56a6a;
	text-decoration:none !important
}

#b{
  color: #3d4449;
  text-decoration:none !important
}

table {
	margin: 0 0 2em 0;
	width: 60%;
}
</style>
<script>
$(window).on('popstate', function(event) {
	  var data = event.originalEvent.state;
	  var member_id = ${sessionScope.member_id};
	  var mpch = $("#mpch").val();
	  
	  if(mpch == "on"){
		  $("#movearea").html(data.data);
	  }else{
		  $("#movearea").html("");
	  	  $("#mainarea").html("");
	      $("#mainarea").load("/dokky/MemberPage.do", {
			member_id : member_id,
			ap : "PushState",
		},function(){
			$("#mpch").val("on");
		});
	  }
	});
</script>
</head>
<body>
			<!-- 바디 -->
	<div class="table-wrapper">
	<c:if test="${myCount eq null}">
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="boardarea"><h3><a href="javascript:;" onclick="memberpage()">게시물</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">0</div>
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" onclick="scrap()">스크랩</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">0</div>
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3 style="color: #f56a6a;">팔로우</h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">0</div>
	</c:if>
	<c:if test="${myCount ne null}">
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="boardarea"><h3><a href="javascript:;" onclick="memberpage()">게시물</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">${myCount.MYBOARDCOUNT}</div>
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" onclick="scrap()">스크랩</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">${myCount.MYSCRAPCOUNT}</div>
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3 style="color: #f56a6a;">팔로우</h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">${myCount.MYFOLLOWCOUNT}</div>
	</c:if>
				<table>
					<tbody>
					<c:forEach var="follow" items="${list}">
					<tr>
							<td width="30%" align="center" style="min-width:200px;"><a href="/dokky/MemberPage.do?member_id=${follow.member_id}&session_id=${sessionScope.member_id}" id="b">${follow.member_name}</a></td>
							<td width="10%" style="padding-right: 30px;"><a class="icon fa-hand-paper-o">${follow.following_count}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<a class="icon fa-hand-o-up">${follow.follower_count}</a>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--ㅡㅡㅡㅡㅡ paging ㅡㅡㅡㅡㅡ-->
			<div><p id="cm" class="hc vc">${page}</p></div>
			<style>
			div { position:relative; } 
			#cm { position:absolute; } 
			.hc { width:10%; left:0; right:0; margin-left:27%; margin-right:73%; }
			.vc { height:3%; top: 0; bottom:0; margin-top:auto; margin-bottom:auto; }
			</style>
			<!--ㅡㅡㅡㅡㅡ paging ㅡㅡㅡㅡㅡ-->
			<br/><br/><br/>
			<form name="valueform">
<input type="hidden" id="member_id" value="${member_id}"/>
<input type="hidden" id="path" value="${path}"/>
</form>
</body>
</html>