<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>회원 정보 수정</title>
<link rel="stylesheet" href="assets/css/main.css" />
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!--비밀번호 확인  -->
<script>
 $(function(){
  $('#password').keyup(function(){
   $('font[name=check]').text('');
  }); //#password.keyup

  $('#chpass').keyup(function(){
   if($('#password').val()!=$('#chpass').val()){
    $('font[name=check]').text('');
    $('font[name=check]').html("비밀번호가 일치하지 않습니다.");
   }else{
    $('font[name=check]').text('');
    $('font[name=check]').html("비밀번호가 일치합니다.");
   }
  }); //#chpass.keyup
 });
 </script>
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
	function gosubmit1(){
	if(frm.name.value==""){
		alert("변경할 닉네임을 작성해주세요");
		return false;
	}
	
	if(frm.password.value != ""){
		if(frm.password.value.length <= 5){
			alert('비밀번호는 6자 이상이어야 합니다')
			frm.password.value= '';
			frm.password.focus();
			return false;
		}
		if(frm.chpass.value == ""){
			alert('비밀번호를 입력하세요');
			frm.chpass.focus();
			return false;
		}
		if(frm.password.value != frm.chpass.value){
			alert('비밀번호를 동일하게 입력하세요');
			frm.password.focus();
			return false;
		}
	}
}
	
function gosubmit2(){
	if(window.innerWidth > 500){
		location.href="/dokky/MemberList.do?ap=web";
	}else if(window.innerWidth <= 500){
		location.href="/dokky/MemberList.do?ap=mobile";
	}
}
</script>
<style>
#c{
text-align:left;
}
</style>
<script>
$(document).ready(function(){
	if(window.innerWidth > 500){
		$("#mobilearea").html('');
		$("#mobilearea").html('<input type="hidden" name="ap" value="web"/>');
	}else if(window.innerWidth <= 500){
		$("#mobilearea").html('');
	}
});

$(window).resize(function(){
	if(window.innerWidth > 500){
		$("#mobilearea").html('');
		$("#mobilearea").html('<input type="hidden" name="ap" value="mobile"/>');
	}else if(window.innerWidth <= 500){
		$("#mobilearea").html('');
	}
});
</script>
</head>

<body>
	<div id="wrapper">
		<div id="main" align="center"> 
		<div class="inner">
<!-- Join Form -->
					<br>
	<h3>회원 정보 수정</h3>
	<br>
		<form name="frm" action="/dokky/MemberModify.do" method="post" onsubmit="">
				<div class="6u 12u$(xsmall)" style="text-align:left;">			
				<input type="hidden" name="member_id" id="member_id" value="${member.member_id}"/>
				<span id="mobilearea">
				</span>
					<input type="text"  name="member_name" id="member_name" value="${member.member_name }" placeholder="닉네임"/>
					<br>
					<br>
				    <div>&nbsp;&nbsp;&nbsp;<b style="font-size:17px;">${member.member_email}</b></div>
					<br>
					<input type="password" style="width:500;" name="member_password" id="member_password" value="" placeholder="비밀번호" />
					<br>
					<input type="password" style="width:500;" name="chpass" id="chpass" value="" placeholder="비밀번호 확인" />
					<br>
					<ul class="actions">
						<input type="submit" value="수정" class="button special">
						<input type="button" value="취소" class="button special" onclick="javascript:gosubmit2()">
					</ul>
					
				</div>
								
		</form>
	
	</div>
</div> 
 	 </div> 
	</body>
</html>