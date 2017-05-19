<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-2.2.1.js"></script>
</head>
<body>
<div id="check">
	<script>
	
	
	
		//기상청에서 rss파일을 xml로 파싱해서 날씨정보를 받아온다.
		// 파라미터로 지역 값을 받은 후 처리하는 함수
		function getQuerystring(key, default_) {
			if (default_ == null)
				default_ = "";
			key = key.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
			var regex = new RegExp("[\\?&]" + key + "=([^&#]*)");
			var qs = regex.exec(window.location.href);
			if (qs == null)
				return default_;
			else
				return qs[1];
		}

		var xx = getQuerystring('xx');
		var yy = getQuerystring('yy');

		// 기본값은 서울/경기.
		if (xx == '' && yy == '') {
			xx = "59";
			yy = "123";
		}

		function pass_go() {
			var frm = document.frm;
			if (frm.sel.value == "01") {
				var xx = "59";
				var yy = "123";
			} // 서울/경기(경기 안양 기준)
			if (frm.sel.value == "02") {
				var xx = "75";
				var yy = "115";
			} // 충북(충북 충주 기준)
			if (frm.sel.value == "03") {
				var xx = "59";
				var yy = "110";
			} // 충남(충남 아산 기준)
			if (frm.sel.value == "04") {
				var xx = "105";
				var yy = "94";
			} // 경북(경북 포항 기준)
			if (frm.sel.value == "05") {
				var xx = "80";
				var yy = "73";
			} // 경남(경남 사천 기준)
			if (frm.sel.value == "06") {
				var xx = "67";
				var yy = "80";
			} // 전북(전북 남원 기준)
			if (frm.sel.value == "07") {
				var xx = "74";
				var yy = "70";
			} // 전남(전남 광양 기준)
			if (frm.sel.value == "08") {
				var xx = "72";
				var yy = "133";
			} // 강원(강원 춘천 기준)
			if (frm.sel.value == "09") {
				var xx = "53";
				var yy = "38";
			} // 제주(제주 제주 기준)
			if (frm.sel.value == "10") {
				var xx = "127";
				var yy = "127";
			} // 울릉(경북 울릉 기준)

			frm.action = "url?xx=" + xx + "&yy=" + yy;
			frm.submit();
		}

		document.write("<form name='frm' method='post'>");
		document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name='sel'>");
		if (xx == "59" && yy == "123") {
			document.write("<option value='01' selected>서울/경기</option>");
		} else {
			document.write("<option value='01'>서울/경기</option>");
		}
		if (xx == "75" && yy == "115") {
			document.write("<option value='02' selected>충청북도</option>");
		} else {
			document.write("<option value='02'>충청북도</option>");
		}
		if (xx == "59" && yy == "110") {
			document.write("<option value='03' selected>충청남도</option>");
		} else {
			document.write("<option value='03'>충청남도</option>");
		}
		if (xx == "105" && yy == "94") {
			document.write("<option value='04' selected>경상북도</option>");
		} else {
			document.write("<option value='04'>경상북도</option>");
		}
		if (xx == "80" && yy == "73") {
			document.write("<option value='05' selected>경상남도</option>");
		} else {
			document.write("<option value='05'>경상남도</option>");
		}
		if (xx == "67" && yy == "80") {
			document.write("<option value='06' selected>전라북도</option>");
		} else {
			document.write("<option value='06'>전라북도</option>");
		}
		if (xx == "74" && yy == "70") {
			document.write("<option value='07' selected>전라남도</option>");
		} else {
			document.write("<option value='07'>전라남도</option>");
		}
		if (xx == "72" && yy == "133") {
			document.write("<option value='08' selected>강원도</option>");
		} else {
			document.write("<option value='08'>강원도</option>");
		}
		if (xx == "53" && yy == "38") {
			document.write("<option value='09' selected>제주도</option>");
		} else {
			document.write("<option value='09'>제주도</option>");
		}
		if (xx == "127" && yy == "127") {
			document.write("<option value='10' selected>울릉도</option>");
		} else {
			document.write("<option value='10'>울릉도</option>");
		}
		document.write("</select>");
		document
				.write("&nbsp;<input type='submit' value='확인' onclick='pass_go()'>");
		document.write("</form>");

		
		
		
		$.ajax({
			url:"http://www.kma.go.kr/wid/queryDFS.jsp?gridx=" + xx	+ "&gridy=" + yy + "",
			type:'get',
			dataType:"jsonp",
		    success: function(data){
		    	console.log(data);
		    	var myXml = data.responseText;
		    	var xmlDoc = $.parseXML(myXml);
		    	console.log(xmlDoc);
		// 파라미터로 받은 지역값에 따라 기상청 지역예보를 가져옴.
		var now = new Date();
		var hour = now.getHours();
		// 기상청 xml은 3시간 단위로 예보를 보여줌. 현재시간 기준으로 어떤 예보를 가져올 것인지 정함.
		if (hour == 24 || hour == 0 || hour == 1 || hour == 2) {
			var hourGubun = 3;
		} else if (hour == 3 || hour == 4 || hour == 5) {
			var hourGubun = 6;
		} else if (hour == 6 || hour == 7 || hour == 8) {
			var hourGubun = 9;
		} else if (hour == 9 || hour == 10 || hour == 11) {
			var hourGubun = 12;
		} else if (hour == 12 || hour == 13 || hour == 14) {
			var hourGubun = 15;
		} else if (hour == 15 || hour == 16 || hour == 17) {
			var hourGubun = 18;
		} else if (hour == 18 || hour == 19 || hour == 20) {
			var hourGubun = 21;
		} else if (hour == 21 || hour == 22 || hour == 23) {
			var hourGubun = 24;
		}
		
		document.write("<table border-right:'none' border-left:'none' border-top:'none' border-bottom:'none' style='#bfbfbf solid; font-size:13px' cellpadding='5' width='250px'>");
		
		var x = xmlDoc.getElementsByTagName("data");
		
		document.write("<tr>");
		
		for (i = 0; i < x.length; i++) {
			if (x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == hourGubun || x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun - 9) || x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun + 3)) {
				document.write("<td align='center'>");
				
				console.log(x[i].getElementsByTagName("day")[0]);
				if (x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "0" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun + 3)) {
					document.write("오늘")
				}
				if (x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "1" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == hourGubun) {
					document.write("내일")

				}
				if (x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "2" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun - 9)) {
					document.write("모레")
				}

				document.write("</td>");
			}
		}
		document.write("</tr>");
		document.write("<tr>");
		for (i = 0; i < x.length; i++) {
			if (x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == hourGubun || x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun - 9) || x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun + 3)) {
					console.log(x[i].getElementsByTagName("hour")[0]);

				document.write("<td align='center'>");
				console.log(x[i].getElementsByTagName("wfkor")[0]+"   "+"날씨");
				if (x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "0" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun + 3)) {
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "맑음") {
						document.write("<img src='/dokky/resources/images/sn.png' alt='맑음'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "구름 많음") {
						document
								.write("<img src='/dokky/resources/images/cb.png' alt='구름 많음'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "구름 조금") {
						document
								.write("<img src='/dokky/resources/images/cl.png' alt='구름 조금'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "흐리고 비"
							|| x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "비") {
						document
								.write("<img src='/dokky/resources/images/hr.png' alt='흐리고 비'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "눈/비") {
						document.write("<img src='/dokky/resources/images/sr.png' alt='눈/비'>")
					}

					document.write("<br>")
					
     				document.write("<font color='blue'>");
					document.write(x[i].getElementsByTagName("tmn")[0].childNodes[0].nodeValue);
					document.write("</font>/<font color='orange'>");
					document.write(x[i].getElementsByTagName("tmx")[0].childNodes[0].nodeValue);
					document.write("</font>");
				} 
				if(x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "1" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == hourGubun){
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "맑음") {
						document.write("<img src='/dokky/resources/images/sn.png' alt='맑음'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "구름 많음") {
						document
								.write("<img src='/dokky/resources/images/cb.png' alt='구름 많음'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "구름 조금") {
						document
								.write("<img src='/dokky/resources/images/cl.png' alt='구름 조금'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "흐리고 비"
							|| x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "비") {
						document
								.write("<img src='/dokky/resources/images/hr.png' alt='흐리고 비'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "눈/비") {
						document.write("<img src='/dokky/resources/images/sr.png' alt='눈/비'>")
					}

					document.write("<br>")
					
					document.write("<font color='blue'>");
					document.write(x[i].getElementsByTagName("tmn")[0].childNodes[0].nodeValue);
					document.write("</font>/<font color='orange'>");
					document.write(x[i].getElementsByTagName("tmx")[0].childNodes[0].nodeValue);
					document.write("</font>");
				}
				if(x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "2" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun - 9)){
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "맑음") {
						document.write("<img src='/dokky/resources/images/sn.png' alt='맑음'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "구름 많음") {
						document
								.write("<img src='/dokky/resources/images/cb.png' alt='구름 많음'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "구름 조금") {
						document
								.write("<img src='/dokky/resources/images/cl.png' alt='구름 조금'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "흐리고 비"
							|| x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "비") {
						document
								.write("<img src='/dokky/resources/images/hr.png' alt='흐리고 비'>")
					}
					if (x[i].getElementsByTagName("wfkor")[0].childNodes[0].nodeValue == "눈/비") {
						document.write("<img src='/dokky/resources/images/sr.png' alt='눈/비'>")
					}

					document.write("<br>")
					
					document.write("<font color='blue'>");
					document.write(x[i].getElementsByTagName("tmn")[0].childNodes[0].nodeValue);
					document.write("</font>/<font color='orange'>");
					document.write(x[i].getElementsByTagName("tmx")[0].childNodes[0].nodeValue);
					document.write("</font>");
				}
		document.write("</td>");
				}
			}
		
		document.write("</tr>");
		document.write("</table>");
		    },
		    error: function(data){
		    	console.log(data.status);
		    	console.log(data.readyState);
		    }
		});
	</script>
</div>
</body>
</html>