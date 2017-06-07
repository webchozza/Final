$(document).ready(function(){
	
	var form = document.getElementById("valueform");
	var i = form.i.value;
	var member_id = $("#member_id").val();

	$('#movearea').load("/dokky/MemberPage.do", {
		member_id : member_id,
		currentPage : i,
		ap : 'AjaxMemberPage'
	});
});

function followclick(addordelete){
	
	var session_id = $("#session_id").val();//팔로우 거는 사람
	var member_id = $("#member_id").val();//팔로우 당하는 사람
	
	if(addordelete == "add"){
	$.ajax({
		url:"/dokky/AddFollow.do",
		type:"post",
		dataType:"json",
		data: {follow_member_id : member_id, member_id : session_id},
		success: function(data){
			followcheck(data);
		}
	});}
	else if (addordelete == "delete"){
	$.ajax({
		url:"/dokky/DeleteFollow.do",
		type:"post",
		dataType:"json",
		data: {follow_member_id : member_id, member_id : session_id},
		success: function(data){
			followcheck(data);
		}
	});}
}

function followcheck(checkValue){

	if(checkValue == "me"){
		$("#me").html("");
	} else if(checkValue == null || checkValue == "0"){//멤버페이지를 불러올 때 팔로우여부 체크값을 보내서 조건을 준다음 plus의 값을 설정함
	$("#plusFollow").html("");
	$("#plusFollow").html('<a href="javascript:;" onclick=\'followclick("add")\'><h4 style="color: #f56a6a;">팔로우+</h4></a>');
	} else if(checkValue != null || checkValue == "1"){
	$("#plusFollow").html("");
	$("#plusFollow").html('<a href="javascript:;" onclick=\'followclick("delete")\'><h4 style="color: #f56a6a;">팔로우 해제</h4></a>');
	}
}