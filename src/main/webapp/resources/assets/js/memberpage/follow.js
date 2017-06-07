function paging(path, i, search, n) {
	
	var member_id = $("#member_id").val();
	
	$('#area').load(path, {
		member_id : member_id,
		currentPage : i,
		n : n,
		search : search,
		ap : 'AjaxPaging'
	},function(data){
		history.pushState({data:data},'follow',"/dokky/MemberPage.do?member_id="+member_id);
	});
}

function sch() {
	var form = document.getElementById('searchform');
	var path = $("#path").val();
	var member_id = $("#member_id").val();
	var i = form.i.value;
	var search = form.search.value;
	
	$('#area').load(path, {
		member_id: member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxSearch'
	},function(data){
				history.pushState({data:data},'follow',"/dokky/MemberPage.do?member_id="+member_id);
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