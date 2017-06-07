function paging(path, i, search, n) {
	
	var member_id = $("#member_id").val();
	
	$('#movearea').load(path, {
		member_id : member_id,
		currentPage : i,
		n : n,
		search : search,
		ap : 'AjaxPaging'
	},function(data){
				history.pushState({data:data},'scrap',"/dokky/MemberPage.do?member_id="+member_id);
	});
}

function sch() {
	var form = document.getElementById('searchform');
	var path = $("#path").val();
	var member_id = $("#member_id").val();
	var i = form.i.value;
	var search = form.search.value;

	$('#movearea').load(path, {
		member_id: member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxSearch'
	},function(data){
				history.pushState({data:data},'scrap',"/dokky/MemberPage.do?member_id="+member_id);
	});
}

function follow(){
	
	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var member_id = $("#member_id").val();
	
	$("#movearea").load("/dokky/ListFollow.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxFollow'
	},function(data){
				history.pushState({data:data},'follow',"/dokky/MemberPage.do?member_id="+member_id);
	});
}

function memberpage(){
	
	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var member_id = $("#member_id").val();

	$('#movearea').load("/dokky/MemberPage.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxMemberPage'
	},function(data){
				history.pushState({data:data},'myboard',"/dokky/MemberPage.do?member_id="+member_id);
	});
}

function deletescrap(){
	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;

	if(!confirm("스크랩에서 지우시겠습니까?")){ return false; }
	
	var session_id = $("#session_id").val();
	var board_id = $("#board_id").val();
	
	$.ajax({
		url:"/dokky/ScrapDelete.do",
		type:"post",
		data: {board_id: board_id, member_id: session_id, currentPage : i, search : search, ap : "AjaxScrapDelete"},
		success: function(data){
			$("#movearea").html(data);
		}
	});
}


function scrap(){

	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var member_id = $("#member_id").val();

	$('#movearea').load("/dokky/ScrapList.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxScrap'
	},function(data){
				history.pushState({data:data},'scrap',"/dokky/MemberPage.do?member_id="+member_id);
	});
}