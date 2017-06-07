<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<title>DOKKY</title>
<style>
input[name=ss] {
display:none;
}
</style>
<script>
$(window).on('popstate', function(event) {
	  var data = event.originalEvent.state;
	  if(data == null){
		  location.href="/dokky/main.do";
	  }else{
		$('#movearea').before(data.header);
	    $('#mainarea').html(data.data);
	  }
	});
</script>
</head>
<body>
<div id="movearea">
	<h4>Q&A</h4>

	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<table>
					<colgroup>
						<col width="65%" />
						<col width="15%" />
						<col width="10%" />
						<col width="10%" />
					</colgroup>
					<thead>
						<tr>
							<div style="display:inline-block;">
							<a href="bqnalist.do?sort=&search=${search }&n=${n}" style="color: #000000; font-weight:bold">최신순</a> 
							<a href="bqnalist.do?sort=like&search=${search }&n=${n}"style="color: #000000; font-weight:bold">추천순</a> 
							<a href="bqnalist.do?sort=comment&search=${search }&n=${n}"style="color: #000000; font-weight:bold">댓글순</a>
							<a href="bqnalist.do?sort=comment&search=${search }&n=${n}"style="color: #000000; font-weight:bold">스크랩순</a> 
							<a href="bqnalist.do?sort=hit&search=${search }&n=${n}"style="color: #000000; font-weight:bold">조회순</a>
							</div>
							<c:if test="${sessionScope.member_email ne null}">
							<div style="display:inline-block; position:relative; left:60%;">
							<a href="bqnawriteform.do" class="button special"><i class="icon fa-pencil"> 새 글 쓰기</i></a>
							</div>
						</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${bqnalist}">
						<c:url var="detailURL" value="bqnadetail.do">
							<c:param name="board_id" value="${list.board_id}"/>
							<c:param name="currentPage" value="${currentPage}"/>
							<c:param name="session_id" value="${sessionScope.member_id}"/>
						</c:url>
						<tr>
							<td>
							<a href="${detailURL }">${list.board_title }</a>&nbsp;<a class="icon fa-commenting-o" style="color: #7f888f">&nbsp;${list.board_comment_count }</a>				
							<c:if test="${list.answerCheckNum gt 0}"><font color="#00B700">[답변채택완료]</font>
							<a class="icon fa-check-circle" style="font-size: 18px; color: #FF6C6C"></a>
							</c:if></td>
							<td><a class="icon fa-thumbs-up">${list.board_like }</a>&nbsp;&nbsp;&nbsp;
								<a class="icon fa-eye">${list.board_hit }</a></td>
							<td><a href="/dokky/MemberPage.do?member_id=${list.member_id }&session_id=${sessionScope.member_id}">${list.board_nickname }</a></td>
							<td><fmt:formatDate value="${list.board_date }" pattern="yyyy.MM.dd" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="paging" align="center">${pagingHtml}</div>
			<br>
			
			<div class="row uniform" align="center">
				<form method="post" action="bqnalist.do">
					<input type="hidden" name="sort" value="${sort}">

					<div class="select-wrapper" style="float: left; width: 140px">
						<select name="n" id="demo-category">
							<option value="1">제목</option>
							<option value="2">내용</option>
							<option value="3">작성자</option>
						</select>
					</div>

					<div style="overflow: hidden;">
						<section id="search" class="alt">

							<input type="text" name="search" id="search" placeholder="Search"
								width="250px" /> <input type="submit" name="ss" />

						</section>
					</div>
				</form>
			</div>
		</div>
	</section>
</div>
</body>
</html>