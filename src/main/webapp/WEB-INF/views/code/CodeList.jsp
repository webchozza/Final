<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<script>
function viewTags(str) {
	var sep = str.split(",");
	$("#tags").append("<i class='icon fa-tags'></i>");
	for (i=0; i<sep.length; i++) {
		if(i==(sep.length-1)){
			$("#tags").append('<a href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>');
		}else{
			$("#tags").append('<a href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>, ');
		}
	}
}

function urlencode(str) {
    str = (str + '').toString();
    return encodeURIComponent(str)
        .replace(/!/g, '%21')
        .replace(/'/g, '%27')
        .replace(/\(/g, '%28')
        .replace(/\)/g, '%29')
        .replace(/\*/g, '%2A')
        .replace(/%20/g, '+');
}

function codelist(line1){
	$("#codearea").load("/dokky/bcodelist.do",{line: line1, ap: "AjaxCode"},function(data){
		history.pushState({data:data},'Code',"/dokky/bfreelist.do");
	});
}
	
	

	$(window).on('popstate', function(event) {
	  var data = event.originalEvent.state;
	  if(data == null){
		  location.href="/dokky/main.do";
	  }else{
	    $('#mainarea').html(data.data);
	  }
	});
</script>
<title>DOKKY</title>
<style>
input[name=ss] {
	display: none;
}
</style>
</head>
<body>
<div id="codearea">
	<h4>Open Source</h4>

	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
							  <a href="/dokky/bcodewrite.do" type="submit" class="button special"><i class="icon fa-pencil">글쓰기</i></a>
				<table>
					<colgroup>
						<col width="15%" />
						<col width="40%" />
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
						<col width="10%" />
					</colgroup>

					<!-- 최신순 댓글순 조회순 추천순 스크랩순 -->
					<thead>
						<tr>
							<td colspan="7">
							<a href='javascript:;' onclick='codelist("1")'>최신순</a>
							<a href='javascript:;' onclick='codelist("2")'>댓글순</a>
							<a href='javascript:;' onclick='codelist("3")'>조회순</a> 
							<a href='javascript:;' onclick='codelist("4")'>추천순</a>
							<a href='javascript:;' onclick='codelist("5")'>스크랩순</a>
							</td>
						</tr>
					</thead>
					<!-- 최신순 댓글순 조회순 추천순 스크랩순 끝 -->

					<tbody>
						<tr>
							<td width="10%" align="center" style="min-width:60px;">번호</td>
							<td width="15%" align="center" style="min-width:60px;">제목</td>
							<td width="10%" align="center" style="min-width:60px;">글쓴이</td>
							<td width="10%" align="center" style="min-width:60px;">날짜</td>
							<td width="10%" align="center" style="min-width:60px;">조회</td>
							<td width="10%" align="center" style="min-width:60px;">추천</td>
						</tr>

						<!-- 여기서부터 forEach var들어가야되 -->
						<c:forEach var="board" items="${list}">
						<c:url var="detailURL" value="bcodedetail.do">
								<c:param name="board_id" value="${board.board_id}" />
								<c:param name="session_id" value="${sessionScope.member_id}" />
							</c:url>
						 <c:if test="${board.board_id ne null}">
							<tr>
								<!-- 1 -->
								<td width="10" align="center">${board.board_id}</td>
								<!-- 2 -->
								<td width="10" align="center"><a href="${detailURL}">${board.board_title}</a></td>
								<!-- 3 -->
								<td width="10" align="center"><a href="/dokky/MemberPage.do?member_id=${board.member_id }&session_id=${sessionScope.member_id}">${board.board_nickname}</a></td>
								<!-- 4 -->
								<td width="10" align="center"><fmt:formatDate
										value="${board.board_date}" pattern="yyyy.MM.dd" /></td>
								<!-- 5 -->
								<td width="10" align="center">${board.board_hit}
								</td>
								<!-- 6 -->
								<td width="10" align="center">${board.board_like}</td>
							</tr>
							</c:if>
						</c:forEach>


					</tbody>

					<!-- 여기까지 forEach -->
				</table>
			</div>
			<br>
			<div class="paging" align="center">${pagingHtml}</div>
			<br>
			<!-- Search 박스 -->
			<div align="center">
				<section id="search" class="alt" style="min-width: 300px; max-width: 600px;">
					<form method="post" action="/dokky/bcodelist.do">
						<input type="text" name="search" id="search" placeholder="Search"
							width="100px" /> <input type="submit" id="search" value="검색" />
					</form>
				</section>
			</div>
		
		</div>
	</section>
</div>
</body>
</html>