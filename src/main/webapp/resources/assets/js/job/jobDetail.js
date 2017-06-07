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
	
var strDivNo = '<div style="font-size: 30px; color: #7f888f;" class="icon fa-bookmark"></div>';
	strDivNo += '<h2 style="color: #7f888f;">스크랩</h2>';
	
if(checkValue == -1){
	$("#scrapbutton").html(strDivNo);
} else if(checkValue > 0){
	$("#scrapbutton").html(strDiv);
} else {
	$("#scrapbutton").html(strA);
}
}