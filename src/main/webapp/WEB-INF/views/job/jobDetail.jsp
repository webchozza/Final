<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>DOKKY</title>
<script type="text/javascript">
function insertScrap(){
	
	var member_id = $("#session_id").val();
	var board_id = $("#board_id").val(); 
	
	$.ajax({
		url:"/dokky/ScrapInsert.do",
		type: "get",
		dataType: "json",
		data: {board_id: board_id, member_id: member_id},
		success: function(data){
				scrapcheck(data);
			}
			//여기에 스크랩 버튼 클릭 안되게 하는 로직 처리
			//팔로우처럼 체크값을 이용해 해당 글의 스크랩 버튼이 안눌리게 하는 함수를 작성하여 data를 인자로 넘긴다.(상세보기 컨트롤러에서도 체크값 넘겨야 함)
	});
}

function scrapcheck(checkValue){
//스크랩하지 않은 글이면 클릭 가능
var strA = '<a href="javascript:;" style="font-size: 30px" class="icon fa-bookmark" onclick="insertScrap()"></a>';
	strA += '<h2 style="color: #7f888f;">스크랩</h2>';
//스크랩한 글이면 클릭 불가능
var strDiv = '<div style="font-size: 30px; color: #f56a6a;" class="icon fa-bookmark"></div>';
	strDiv += '<h2 style="color: #f56a6a;">스크랩</h2>';
	
var strDivNo = '<div style="font-size: 30px; color: #7f888f;" class="icon fa-bookmark" onclick="return vali()"></div>';
	strDivNo += '<h2 style="color: #7f888f;">스크랩</h2>';
	
if(checkValue == -1){
	$("#scrapbutton").html(strDivNo);
} else if(checkValue > 0){
	$("#scrapbutton").html(strDiv);
} else {
	$("#scrapbutton").html(strA);
	}
}

//비로그인시 유효성 검사
function vali(){
	alert("로그인 후에 이용가능한 서비스입니다.");
	
	if(!confirm("로그인 화면으로 이동하시겠습니까?")){
		return false;
	}
	
	location.href="/dokky/loginform.do";
}

function recommendcheck(checkValue){
	//추천하지 않은 글이면 클릭 가능
	var strA = '<a href="bjobrecommend.do?board_id=${bjobDetail.board_id }&currentPage=${currentPage}&session_id=${sessionScope.member_id}" style="font-size: 30px" class="icon fa-thumbs-up"></a>';
		strA += '<h2 style="color: #7f888f;">${bjobDetail.board_like }</h2>';
	//추천한 글이면 클릭 불가능
	var strDiv = '<div style="font-size: 30px; color: #f56a6a;" class="icon fa-thumbs-up"></div>';
		strDiv += '<h2 style="color: #f56a6a;">${bjobDetail.board_like }</h2>';
		
	var strDivNo = '<div style="font-size: 30px; color: #7f888f;" class="icon fa-thumbs-up" onclick="return vali()"></div>';
		strDivNo += '<h2 style="color: #7f888f;">${bjobDetail.board_like }</h2>';
		
	if(checkValue == -1){
		$("#recommendbutton").html(strDivNo);
	} else if(checkValue > 0){
		$("#recommendbutton").html(strDiv);
	} else {
		$("#recommendbutton").html(strA);
	}
	}

	function gosubmit1() {
		if (frm.bcomment_content.value == ""
				|| frm.bcomment_content.value == null) {
			alert("댓글을 입력해주세요.");
			frm.bcomment_content.focus();
			return false;
		}
	}

	function deleteconfirm() {
		if (confirm("정말 삭제하시겠습니까?") == false) {
			return false;
		}
	}
	function gosubmit1_message() {

		var f = document.note;
		f.name_from.value;
		f.method = "post";
		f.action = "/dokky/messagewriteform.do";
		f.submit();
	}
	
	function viewTags(str) {
		var sep = str.split(",");
		$("#tags").append("<i class='icon fa-tags'></i>");
		for (i=0; i<sep.length; i++) {
			if(i==(sep.length-1)){
				$("#tags").append('<a class="tagcode" href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>');
			}else{
				$("#tags").append('<a class="tagcode" href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>&nbsp; ');
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

	$(document).ready(function() {
		//eongoo
		scrapcheck("${scrapCheck}");
		recommendcheck("${recommendCheck}");
		//jj
		viewTags("${bjobDetail.board_tag}");
	
		//eongoo
		var board_id = $("#board_id").val();
		var session_id = $("#session_id").val();
		
		$.ajax({
			url: "/dokky/SimilarBoard.do",
			type: "post",
			dataType: "json",
			data: {board_id : board_id},
			success: function(data){
				if(data!=null){
				var str = '';
				$.each(data,function(index, value){
					if(value.BGROUP_ID == "1"){
						var path = '/dokky/bcodedetail.do?board_id='+value.BOARD_ID+'&currentPage=1&session_id='+session_id;
					}else if(value.BGROUP_ID == "2"){
						var path = '/dokky/bjobdetail.do?board_id='+value.BOARD_ID+'&currentPage=1&session_id='+session_id;
					}else if(value.BGROUP_ID == "3"){
						var path = '/dokky/bcodedetail.do?board_id='+value.BOARD_ID+'&currentPage=1&session_id='+session_id;
					}else if(value.BGROUP_ID == "4"){
						var path = '/dokky/bqnadetail.do?board_id='+value.BOARD_ID+'&currentPage=1&session_id='+session_id;
					}
					
					str += '<b><a href="'+path+'" style="color:#504747; padding-right:20px; color:#597D9C;">'+value.BOARD_TITLE+'</a></b>';
				});
				$("#recodetail").html('<b style="font-size:15px; color:#398ECF;">비슷한 글을 찾으시나요?</b>');
				$("#recodetaila").html(str);
			}
			}
		});
	});
	
	

</script>

<style>
.fa-bookmark {
	color: #7f888f;
}
.fa-thumbs-up{
	color: #7f888f;
}

.tagcode {
	background: rgba(230, 235, 237, 0.25);
	border-radius: 0.375em;
	border: solid 1px rgba(210, 215, 217, 0.75);
	font-family: Malgun Gothic;
	font-size: 0.9em;
	margin: 0 0.25em;
	padding: 0.25em 0.65em;
	color: #7f888f;
	
}
</style>

</head>
<body>
	<h4>Job</h4>
	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<div class="table-wrapper">
					<table class="alt">
						<colgroup>
							<col width="85%" />
							<col width="15%" />
						</colgroup>
						<tbody>
							<tr>
								<td colspan="2"><strong>
									<a href="/dokky/MemberPage.do?member_id=${bjobDetail.member_id }&session_id=${sessionScope.member_id}">${bjobDetail.board_nickname }</a></strong>
									<a href="javascript:gosubmit1_message()"
									class="icon fa-envelope">쪽지</a> <br> <i><fmt:formatDate
											value="${bjobDetail.board_date }" pattern="yyyy.MM.dd hh:mm" />
								</i></td>
							</tr>
							<tr>
								<td><h2>${bjobDetail.board_title }</h2>
									<hr class="major" />
									<p>${bjobDetail.board_content }</p></td>
								<td><center>
										<div id="recommendbutton"></div>
										<div id="scrapbutton"></div>
									</center>
							</tr>
							<tr>
								<td colspan="2"><div id="tags"></div>
							</tr>
						</tbody>
					</table>
					<span id="recodetail" style="display:inline-block; position:relative; bottom:15px; left:10px;">
					<img src="resources/images/blueloading.gif" style="width:200px; height:100px;">
					</span><br>
					<span id="recodetaila" style="display:inline-block; position:relative; bottom:15px; left:10px; max-width:1000px; min-width:200px;"></span>
					<table class="alt">
						<colgroup>
							<col width="85%" />
							<col width="15%" />
						</colgroup>
						<tbody>
							<tr>
								<td colspan="2">댓글 ${bjobDetail.board_comment_count }&nbsp;&nbsp;&nbsp;

								
							</tr>
							<c:forEach var="clist" items="${bcjobList }">
								<tr>
									<td colspan="2"><strong><a
											href="MemberPage.do?member_id=${clist.member_id }">${clist.member_name }</a></strong>
										<i><fmt:formatDate value="${clist.bcomment_date }"
												pattern="yyyy.MM.dd hh:mm" /></i>&nbsp;&nbsp;&nbsp; <c:if
											test="${sessionScope.member_email ne null}">
											<c:if test="${sessionScope.member_id==clist.member_id}">

												<a
													href="bjobdeletecomment.do?bcomment_id=${clist.bcomment_id }&board_id=${bjobDetail.board_id}&currentPage=${currentPage}&session_id=${sessionScope.member_id}"
													class="icon fa-trash"
													style="font-size: 14px; color: #7f888f"
													onclick="return deleteconfirm()"></a>
											</c:if>
										</c:if>
										<p>${clist.bcomment_content }</p></td>
								</tr>
							</c:forEach>

							<c:choose>
								<c:when test="${sessionScope.member_email ne null}">
									<form name="frm" action="bjobwritecomment.do"
										onsubmit="return gosubmit1()">
										<tr>
											<td><textarea cols="20" rows="3" name="bcomment_content"
													placeholder="댓글을 입력해주세요."></textarea></td>
											<td valign="middle"><center>
													<input type="hidden" name="member_id"
														value="${sessionScope.member_id}"> <input
														type="hidden" name="board_id"
														value="${bjobDetail.board_id}"><input
														type="hidden" name=currentPage value="${currentPage}"><input
														type="submit" value="작성" class="button special">
														<input type="hidden" name="session_id" value="${sessionScope.member_id}">
												</center></td>
										</tr>
								</form>
								</c:when>
								<c:otherwise>
									<tr height="50px">
										<td valign="middle" colspan="2"><center>
												<a href="loginform.do">로그인</a>을 하시면 댓글을 등록할 수 있습니다.
											</center></td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2" align="right">
									<div align="right">
										<c:if test="${sessionScope.member_email ne null}">
											<c:if
												test="${sessionScope.member_id==bjobDetail.member_id }">
												<a
													href="bjobmodifyform.do?board_id=${bjobDetail.board_id }&currentPage=${currentPage}"
													class="button">수정</a>
												<a
													href="bjobdelete.do?board_id=${bjobDetail.board_id }&currentPage=${currentPage}"
													class="button" onclick="return deleteconfirm()">삭제</a>
											</c:if>
										</c:if>
										<a href="bjoblist.do?board_id=${bjobDetail.board_id }&currentPage=${currentPage}" 
											class="button special">목록</a>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>

				</div>

			</div>
		</div>
	</section>
	<form name="note">
		<input type="hidden" name="name_from" id="name_from"
			value="${bjobDetail.board_nickname}">
	</form>

	<form name="valueform">
		<input type="hidden" id="board_id" value="${bjobDetail.board_id}" />
		<input type="hidden" id="session_id" value="${sessionScope.member_id}" />
	</form>
</body>
</html>