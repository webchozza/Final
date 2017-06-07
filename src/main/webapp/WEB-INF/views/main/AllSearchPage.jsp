<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<script src="resources/assets/js/main/allsearchlist.js?v=4"></script>
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
	  var mpch = $("#mpch").val();
	  
	  if(mpch == "on"){
	    $('#pagearea').html(data.data);
	  }else{
	  	  $("#mainarea").html("");
	      $("#mainarea").load("/dokky/AllSearchList.do", { ap : "AjaxSearch" });
	  }
});
</script>
</head>
<body>
	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<table>
					<colgroup>
						<col width="50%" />
						<col width="25%" />
						<col width="20%" />
						<col width="10%" />
					</colgroup>
					<thead>
						<tr>
							<td colspan="6">
							<a href="javascript:;" onclick='category("all")'>전체&nbsp;</a>
							<a href="javascript:;" onclick="category(0)">최신순</a> 
							<a href="javascript:;" onclick="category(1)">추천순</a> 
							<a href="javascript:;" onclick="category(2)">댓글순</a> 
							<a href="javascript:;" onclick="category(3)">스크랩순</a> 
							<a href="javascript:;" onclick="category(4)">조회순</a></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="board" items="${allSearchList}">
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
							<td scope="col"><a href="${detailurl}" id="b">${board.board_title}</a></td>
							<td scope="col"><a class="icon fa-comment">${board.board_comment_count}</a>&nbsp;&nbsp;&nbsp; 
							<a class="icon fa-thumbs-up">${board.board_like}</a>&nbsp;&nbsp;&nbsp; 
							<a class="icon fa-eye">${board.board_hit}</a></td>
							<td scope="col"><a href="/dokky/MemberPage.do?member_id=${board.member_id}&session_id=${sessionScope.member_id}" id="b">${board.board_nickname}</a></td>
							<td scope="col"><fmt:formatDate value="${board.board_date}" pattern="yyyy.MM.dd" /></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div><p id="cm" class="hc vc" align="center">${page}</p></div>
			<div align="center">
				<section id="search" class="alt" style="min-width: 200px; max-width:400px;">
					<form method="post" action="javascript:;" id="searchform">
					<input type="hidden" name="i" id="i" value="${i}"/>
						<input type="text" name="AllSearch" id="AllSearch" value="${AllSearch}" placeholder="Search All"
							width="100px" />
							<input type="submit" name="ss" onclick="sch()"/>
					</form>
				</section>
			</div>
		</div>
	</section>
<br/><br/><br/>
<form name="valueform">
<input type="hidden" id="member_id" value="${member_id}"/>
<input type="hidden" id="path" value="${path}"/>
<input type="hidden" id="session_id" value="${sessionScope.member_id}"/>
</form>
</body>
</html>