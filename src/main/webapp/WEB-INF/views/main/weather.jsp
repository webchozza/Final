<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<body>
<div id="check">
<script src="jquery.xdomainajax.js"></script>
	<script>//���û���� rss������ xml�� �Ľ��ؼ� ���������� �޾ƿ´�.
		// �Ķ���ͷ� ���� ���� ���� �� ó���ϴ� �Լ�
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

		// �⺻���� ����/���.
		if (xx == '' && yy == '') {
			xx = "59";
			yy = "123";
		}

		function pass_go() {
			var frm = document.frm;
			if (frm.sel.value == "01") {
				var xx = "59";
				var yy = "123";
			} // ����/���(��� �Ⱦ� ����)
			if (frm.sel.value == "02") {
				var xx = "75";
				var yy = "115";
			} // ���(��� ���� ����)
			if (frm.sel.value == "03") {
				var xx = "59";
				var yy = "110";
			} // �泲(�泲 �ƻ� ����)
			if (frm.sel.value == "04") {
				var xx = "105";
				var yy = "94";
			} // ���(��� ���� ����)
			if (frm.sel.value == "05") {
				var xx = "80";
				var yy = "73";
			} // �泲(�泲 ��õ ����)
			if (frm.sel.value == "06") {
				var xx = "67";
				var yy = "80";
			} // ����(���� ���� ����)
			if (frm.sel.value == "07") {
				var xx = "74";
				var yy = "70";
			} // ����(���� ���� ����)
			if (frm.sel.value == "08") {
				var xx = "72";
				var yy = "133";
			} // ����(���� ��õ ����)
			if (frm.sel.value == "09") {
				var xx = "53";
				var yy = "38";
			} // ����(���� ���� ����)
			if (frm.sel.value == "10") {
				var xx = "127";
				var yy = "127";
			} // �︪(��� �︪ ����)

			frm.action = "Weather.action?xx=" + xx + "&yy=" + yy;
			frm.submit();
		}

		document.write("<form name='frm' method='post'>");
		document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name='sel'>");
		if (xx == "59" && yy == "123") {
			document.write("<option value='01' selected>����/���</option>");
		} else {
			document.write("<option value='01'>����/���</option>");
		}
		if (xx == "75" && yy == "115") {
			document.write("<option value='02' selected>��û�ϵ�</option>");
		} else {
			document.write("<option value='02'>��û�ϵ�</option>");
		}
		if (xx == "59" && yy == "110") {
			document.write("<option value='03' selected>��û����</option>");
		} else {
			document.write("<option value='03'>��û����</option>");
		}
		if (xx == "105" && yy == "94") {
			document.write("<option value='04' selected>���ϵ�</option>");
		} else {
			document.write("<option value='04'>���ϵ�</option>");
		}
		if (xx == "80" && yy == "73") {
			document.write("<option value='05' selected>��󳲵�</option>");
		} else {
			document.write("<option value='05'>��󳲵�</option>");
		}
		if (xx == "67" && yy == "80") {
			document.write("<option value='06' selected>����ϵ�</option>");
		} else {
			document.write("<option value='06'>����ϵ�</option>");
		}
		if (xx == "74" && yy == "70") {
			document.write("<option value='07' selected>���󳲵�</option>");
		} else {
			document.write("<option value='07'>���󳲵�</option>");
		}
		if (xx == "72" && yy == "133") {
			document.write("<option value='08' selected>������</option>");
		} else {
			document.write("<option value='08'>������</option>");
		}
		if (xx == "53" && yy == "38") {
			document.write("<option value='09' selected>���ֵ�</option>");
		} else {
			document.write("<option value='09'>���ֵ�</option>");
		}
		if (xx == "127" && yy == "127") {
			document.write("<option value='10' selected>�︪��</option>");
		} else {
			document.write("<option value='10'>�︪��</option>");
		}
		document.write("</select>");
		document
				.write("&nbsp;<input type='submit' value='Ȯ��' onclick='pass_go()'>");
		document.write("</form>");
	</script>

	<script type="text/javascript">
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		// �Ķ���ͷ� ���� �������� ���� ���û ���������� ������.
		xmlhttp.open("GET", "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=" + xx	+ "&gridy=" + yy + "", false);
		xmlhttp.send();
		
		
		
		xmlDoc = xmlhttp.responseXML;
		var now = new Date();
		var hour = now.getHours();
		// ���û xml�� 3�ð� ������ ������ ������. ����ð� �������� � ������ ������ ������ ����.
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
				
				
				if (x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "0" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun + 3)) {
					document.write("����")
				}
				if (x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "1" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == hourGubun) {
					document.write("����")

				}
				if (x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "2" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun - 9)) {
					document.write("��")
				}

				document.write("</td>");
			}
		}
		document.write("</tr>");
		document.write("<tr>");
		for (i = 0; i < x.length; i++) {
			if (x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == hourGubun || x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun - 9) || x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun + 3)) {

				document.write("<td align='center'>");
				
				if (x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "0" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun + 3)) {
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "����") {
						document.write("<img src='/khtour/img/sn.png' alt='����'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "���� ����") {
						document
								.write("<img src='/khtour/img/cb.png' alt='���� ����'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "���� ����") {
						document
								.write("<img src='/khtour/img/cl.png' alt='���� ����'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "�帮�� ��"
							|| x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "��") {
						document
								.write("<img src='/khtour/img/hr.png' alt='�帮�� ��'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "��/��") {
						document.write("<img src='/khtour/img/sr.png' alt='��/��'>")
					}

					document.write("<br>")
					
     				document.write("<font color='blue'>");
					document.write(x[i].getElementsByTagName("tmn")[0].childNodes[0].nodeValue);
					document.write("</font>/<font color='orange'>");
					document.write(x[i].getElementsByTagName("tmx")[0].childNodes[0].nodeValue);
					document.write("</font>");
				} 
				if(x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "1" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == hourGubun){
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "����") {
						document.write("<img src='/khtour/img/sn.png' alt='����'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "���� ����") {
						document
								.write("<img src='/khtour/img/cb.png' alt='���� ����'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "���� ����") {
						document
								.write("<img src='/khtour/img/cl.png' alt='���� ����'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "�帮�� ��"
							|| x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "��") {
						document
								.write("<img src='/khtour/img/hr.png' alt='�帮�� ��'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "��/��") {
						document.write("<img src='/khtour/img/sr.png' alt='��/��'>")
					}

					document.write("<br>")
					
					document.write("<font color='blue'>");
					document.write(x[i].getElementsByTagName("tmn")[0].childNodes[0].nodeValue);
					document.write("</font>/<font color='orange'>");
					document.write(x[i].getElementsByTagName("tmx")[0].childNodes[0].nodeValue);
					document.write("</font>");
				}
				if(x[i].getElementsByTagName("day")[0].childNodes[0].nodeValue == "2" && x[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue == (hourGubun - 9)){
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "����") {
						document.write("<img src='/khtour/img/sn.png' alt='����'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "���� ����") {
						document
								.write("<img src='/khtour/img/cb.png' alt='���� ����'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "���� ����") {
						document
								.write("<img src='/khtour/img/cl.png' alt='���� ����'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "�帮�� ��"
							|| x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "��") {
						document
								.write("<img src='/khtour/img/hr.png' alt='�帮�� ��'>")
					}
					if (x[i].getElementsByTagName("wfKor")[0].childNodes[0].nodeValue == "��/��") {
						document.write("<img src='/khtour/img/sr.png' alt='��/��'>")
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
	</script>
</div>
</body>
</html>