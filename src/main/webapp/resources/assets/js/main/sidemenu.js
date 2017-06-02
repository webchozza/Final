setInterval(function(){
	var session_id = $("#session_id").val();
	
	if(session_id != null && session_id != ""){
	$.ajax({
		url: "/dokky/notiCount.do",
		type: "post",
		dataType: "json",
		data: {session_id : session_id},
		success: function(data){
			if(data == 0){
				$("#notiarea").html("");
			}else{
			var str = '<span class="fa fa-plus" style="color: #f56a6a;">&nbsp;'+data+'</span>';
			$("#notiarea").html("");
			$("#notiarea").html(str);
			}
		}
	});
	}
}, 3000);

setInterval(function(){
	var session_id = $("#session_id").val();
	
	if(session_id != null && session_id != ""){
	$.ajax({
		url: "/dokky/RecommendList.do",
		type: "post",
		dataType: "json",
		data: {session_id : session_id},
		success: function(data){
			if(data!=null){
				var str = '<b><p style="position:relative; bottom:35px; color:#398ECF; font-size:15px;">회원님이 관심가질만한 글</p></b>';
				    str+= '<div style="position:relative; bottom:60px;">';
			$.each(data,function(index, value){
				
				console.log(value);
				str += '<img src="resources/images/dot.jpg" style="width:10px; height:10px;">';
				str += '<a href="" style="padding:2px; color:#504747;">'+value.BOARD_TITLE+'</a><br>';
				});
				str += '</div>';
				$("#loginreco").html(str);
			}
			}
	});
	}
}, 600000);

function notipop(){
	
	var ch = $("#notich").val();
	var session_id = $("#session_id").val();
	
	if(ch == "N"){
		$.ajax({
			url: "/dokky/notilist.do",
			dataType: "json",
			cache: false,
			data: {session_id: session_id},
			success: function(data){
				
				$("#notibox").html("");
				$("#notibox").append('<div class="notibox"><div>');
				$(".notibox").addClass("nb");
				$("#notibutton").attr("class","icon fa-bell alramon");
				$("#notich").val("Y");
				
				var str = "<ul> \n";
				var session_id = $("#session_id").val();
				
				$.each(data, function(key,value){//
					
					var deletestr = "<a href='javascript:;' onclick='notidelete("+value.noti_id+")'>";
				    deletestr += "<img src=\"/dokky/resources/images/x.jpg\" style=\"width: 9px; height: 9px;\"/></a>";
						
					if(value.noti_kinds == "comment"){
					str += "<li>회원님의 글 <a href='"+value.noti_url+"&session_id="+session_id+"'>"+value.noti_subject;
					str += "</a>에 댓글이 등록되었습니다&nbsp;"+deletestr+"</li>\n"; 
					} else if(value.noti_kinds == "follow_NewBoard") {
					str += "<li><a href='/dokky/MemberPage.do?member_id="+value.sender_id+"&session_id="+session_id+"'>"+value.sender_name;
					str += "</a>님이 새로운 <a href='"+value.noti_url+"&session_id="+session_id+"'>글</a>을 작성했습니다&nbsp;"+deletestr+"</li>\n"; 
					} else if(value.noti_kinds == "follow_comment"){
					str += "<li><a href='/dokky/MemberPage.do?member_id="+value.sender_id+"&session_id="+session_id+"'>"+value.sender_name;
					str += "</a>님이 새로운 <a href='"+value.noti_url+"&session_id="+session_id+"'>댓글</a>을 등록했습니다&nbsp;"+deletestr+"</li>\n";
					}
				});
				str += "</ul>";
				
				$(".notibox").html(str);
			}
		});
	}
	
	if(ch == "Y"){
		$("#notibutton").attr("class","icon fa-bell alram");
		$("#notibox").html("");
		$("#notich").val("N");
	}
}

function notidelete(noti_id){
	
	$.ajax({
		url:"/dokky/notiDelete.do",
		type: "post",
		data: {noti_id: noti_id},
		success: function(){
			$("#notich").val("N");
			notipop();
		}
	});
}

function movememberlist(){

	if(window.innerWidth > 500){
		location.href="/dokky/MemberList.do?ap=web";
	}else if(window.innerWidth <= 500){
		location.href="/dokky/MemberList.do?ap=mobile";
	}
}