<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>DOKKY</title>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/job/joblist.js?v=2"></script>
<style>
input[name=ss] {
	display: none;
}

a.tag {
	background: rgba(230, 235, 237, 0.25);
	border-radius: 0.375em;
	border: solid 1px rgba(210, 215, 217, 0.75);
	font-family: Malgun Gothic;
	font-size: 0.9em;
	margin: 0 0.25em;
	padding: 0.25em 0.65em;
	color: #3d4449
}
</style>
<script>
$(window).on('popstate', function(event) {
	  var data = event.originalEvent.state;
	  if(data == null){
		  location.href="/dokky/main.do";
	  }else{
	    $('#mainarea').html(data.data);
	  }
	});
</script>
</head>
<body>
<div id="movearea">
	<h4>Job</h4>

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
					<c:if test="${sessionScope.member_email ne null}">
								<a href="bjobwriteform.do" class="button special"><i class="icon fa-pencil"> 새 글 쓰기</i></a>
								</c:if>
						<tr>
							<td colspan="6">
							<a href="bjoblist.do?sort=&search=${search }&n=${n}">최신순</a> <a
								href="bjoblist.do?sort=like&search=${search }&n=${n}">추천순</a> <a
								href="bjoblist.do?sort=comment&search=${search }&n=${n}">댓글순</a> <a
								href="bjoblist.do?sort=scrap&search=${search }&n=${n}">스크랩순</a> <a
								href="bjoblist.do?sort=hit&search=${search }&n=${n}">조회순</a></td>
								</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${bjoblist }">
							<c:url var="detailURL" value="bjobdetail.do">
								<c:param name="board_id" value="${list.board_id }" />
								<c:param name="currentPage" value="${currentPage }" />
								<c:param name="session_id" value="${sessionScope.member_id}" />
							</c:url>
							<tr>
								<td scope="col"><a href="${detailURL }" style="font-size: 12px">${list.board_title }</a>

								</td>

								<td scope="col"><a class="icon fa-comment">${list.board_comment_count }</a>&nbsp;&nbsp;&nbsp;
									<a class="icon fa-thumbs-up">${list.board_like }</a>&nbsp;&nbsp;&nbsp;
									<a class="icon fa-eye">${list.board_hit }</a></td>
								<td scope="col"><a
									href="/dokky/MemberPage.do?member_id=${list.member_id }&session_id=${sessionScope.member_id}">${list.board_nickname }</a></td>
								<td scope="col"><fmt:formatDate value="${list.board_date }"
										pattern="yyyy.MM.dd" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="paging" align="center">${pagingHtml}</div>
			<br>

			<div class="row uniform" align="center">
				<form method="post" action="bjoblist.do">
					<input type="hidden" name="sort" value="${sort}">

					<div class="select-wrapper" style="float: left; max-width: 140px; min-width:80px;">
						<select name="n" id="demo-category">
							<option value="1">제목</option>
							<option value="2">내용</option>
							<option value="3">작성자</option>
						</select>
					</div>

					<div style="overflow: hidden; min-width:180px;">
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