<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<script src="resources/assets/js/memberpage/myboard.js?v=4"></script>
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
<div id="movearea">
			<!-- 바디 -->
	<div class="table-wrapper">
	<c:if test="${myCount eq null}">
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="boardarea"><h3 style="color: #f56a6a;">게시물</h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">0</div>
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" onclick="scrap()">스크랩</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">0</div>
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" id="a" onclick="follow()">팔로우</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">0</div>
	</c:if>
	<c:if test="${myCount ne null}">
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="boardarea"><h3 style="color: #f56a6a;">게시물</h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">${myCount.MYBOARDCOUNT}</div>
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" onclick="scrap()">스크랩</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">${myCount.MYSCRAPCOUNT}</div>
	<div style="min-width:50px; max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" onclick="follow()">팔로우</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">${myCount.MYFOLLOWCOUNT}</div>
	</c:if>
				<table>
					<tbody>
					<c:forEach var="board" items="${list}">
					<c:if test="${board.bgroup_id eq 1}"><!-- qna -->
						<c:url var="detailurl" value="/bcodedetail.do">
							<c:param name="board_id" value="${board.board_id }"/>
							<c:param name="currentPage" value="1"/>
		    				<c:param name="session_id" value="${sessionScope.member_id}"/>
						</c:url>
					</c:if>
					<c:if test="${board.bgroup_id eq 2}">
						<c:url var="detailurl" value="/bfreedetail.do">
							<c:param name="board_id" value="${board.board_id }"/>
							<c:param name="currentPage" value="1"/>
		    				<c:param name="session_id" value="${sessionScope.member_id}"/>
						</c:url>
					</c:if>
					<c:if test="${board.bgroup_id eq 3}">
						<c:url var="detailurl" value="#"/><!-- 구인구직 -->
					</c:if>
					<c:if test="${board.bgroup_id eq 4}">
						<c:url var="detailurl" value="/bqnadetail.do">
							<c:param name="board_id" value="${board.board_id }"/>
							<c:param name="currentPage" value="1"/>
		    				<c:param name="session_id" value="${sessionScope.member_id}"/>
						</c:url>
					</c:if>
					<tr>
							<td width="30%"><a href="${detailurl}" id="b">${board.board_title}</a></td>
							<td width="10%"><a class="icon fa-comment">${board.board_comment_count}</a>&nbsp;&nbsp;&nbsp; <a
								class="icon fa-thumbs-up">${board.board_like}</a>&nbsp;&nbsp;&nbsp; <a
								class="icon fa-eye">${board.board_hit}</a></td>
							<td width="10%" align="center"><div id="b">${board.member_name}</div></td>
							<td width="5%" align="center"><fmt:formatDate value="${board.board_date}" pattern="yyyy.MM.dd" /></td>
							<td width="5%">
							<c:if test="${board.member_id eq sessionScope.member_id}">
							<a href="javascript:;" onclick='return deleteMyBoard("${board.board_id}")'><img src="/dokky/resources/images/x.jpg" style="width: 20%; height: 5%;"/></a>
							</c:if>
							</td>
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
			.hc { width:20%; left:0; right:0; margin-left:50%; margin-right:50%; }
			.vc { height:3%; top: 0; bottom:0; margin-top:auto; margin-bottom:auto; }
			</style>
			<!--ㅡㅡㅡㅡㅡ paging ㅡㅡㅡㅡㅡ-->
			</div>
			<br/><br/><br/>
</body>
</html>