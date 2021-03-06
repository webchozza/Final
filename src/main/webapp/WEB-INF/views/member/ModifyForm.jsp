<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>JoinForm</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!--비밀번호 확인  -->
<script>
	$(function() {
		$('#member_pw').keyup(function() {
			$('font[name=check]').text('');
		}); //#password.keyup

		$('#chpass').keyup(function() {
			if ($('#member_pw').val() != $('#chpass').val()) {
				$('font[name=check]').text('');
				$('font[name=check]').html("비밀번호가 일치하지 않습니다.");
			} else {
				$('font[name=check]').text('');
				$('font[name=check]').html("비밀번호가 일치합니다.");
			}
		}); //#chpass.keyup
	});
</script>
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
	function gosubmit1() {

		if (frm.member_name.value == "") {
			alert("이름을 입력해 주세요");
			return false;
		}
		if (frm.member_pw.value == "") {
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		if (frm.chpass.value == "") {
			alert("비밀번호를 확인해주세요");
			return false;
		}
		if (frm.member_pw.value != frm.chpass.value) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
	
		var f = document.frm;
		f.method = "get";
		f.member_email.value = f.member_email.value;
		f.member_name.value = f.member_name.value;
		f.member_pw.value = f.member_pw.value;
		f.action = "/dokky/modifyMember.do";
		f.submit();
	}
	function gosubmit2() {
		var f = document.frm;
		f.method = "post";
		f.action = "/dokky/main.do";
		f.submit();
	}
	function gosubmit3() {
		var prmName = $('#member_name').val();

		if ($("#member_name").val() == '') {
			alert('이름을 입력해주세요.');
			return;
		}

		$.ajax({
			type : 'GET',
			data : {member_name : prmName},
			dataType : 'text',
			url : '/dokky/checkname.do',
			success : function(rData, textStatus, xhr) {
				var chkRst = rData;
				if (chkRst == "true") {
					alert("등록 가능 합니다.");
					$("#nameChk").val('true');
				} else {
					alert("중복 되어 있습니다.");
					$("#nameChk").val('false');
				}
			}
		});
	}
	
	function gosubmit4() {
		if (confirm("정말 탈퇴하시겠습니까?") == true){  
			var f = document.frm;
			f.member_email.value = f.member_email.value;
			f.method = "POST";
			f.action = "/dokky/deleteMember.do";
			f.submit();
		}else{   
		    return;
		}
		}
</script>
</head>

<body>
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
				<!-- Modify Form -->

				<h3>회원정보수정</h3>
				<form name="frm">

					<div class="row uniform" style="float: inherit;">
						<div class="6u 12u$(xsmall)" style="float: inherit;">
							<input type="hidden" id="nameChk" value="false" /> <input
								type="hidden" id="emailChk" value="false" /> <input type="text"
								name="member_name" id="member_name" value="${member.member_name}" placeholder="닉네임" />
							<ul class="actions" style="float: left;">
								<li><input type="button" value="중복확인"
									class="button special" onclick="javascript:gosubmit3()"></li>
							</ul>
							<br /> <input type="text" name="member_email" id="member_email"
								value="${member.member_email}" readonly>
							<br/>
							<input type="password" style="width: 500;" name="member_pw"
								id="member_pw" value="" placeholder="비밀번호" /> <br /> <input
								type="password" style="width: 500;" name="chpass" id="chpass"
								value="" placeholder="비밀번호 확인" /> 
								<font name="check" size="2" style="float: left" color="pink"></font> <br />
							<ul class="actions">
								<input type="button" value="수정" class="button special"
									onclick="javascript:gosubmit1()">
								<input type="button" value="메인" class="button special"
									onclick="javascript:gosubmit2()">
								<input type="button" value="탈퇴" class="button special"
									onclick="javascript:gosubmit4()">		
							</ul>

						</div>

					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>