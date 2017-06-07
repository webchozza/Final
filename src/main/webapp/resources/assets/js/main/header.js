$(window).on("beforeunload", function(){//메인페이지를 떠날 때 마이크가 켜져있다면 꺼준다

	if($("#micCheck").val()=="on"){
		$("#micCheck").val("off");
		$("#btn-mic").click();
	}
		
});

function micOn(){//이미지 바뀌게하는 js
	
		var micStatus = document.getElementById("micCheck").value;
	
		if (micStatus == "on") {
			$("#micImg").attr("class","icon fa-microphone-slash"); 
			$("#soundsearch").attr("placeholder","Search All")
			$("#micImg").html("음성 검색");
		  $("#micCheck").val("off");
		   }
		if (micStatus == "off") {
			$("#micImg").attr("class","icon fa-microphone");
			$("#soundsearch").attr("placeholder","음성 명령어: '검색', '다시'")
			$("#micImg").html("음성 검색 중...");
        $("#micCheck").val("on");
           }
		
		$("#btn-mic").click();
		
}

//HTML5 Speech Recognition API 사용 음성 인식 기능
$(function() {
	
	if (typeof webkitSpeechRecognition != 'function') {//음성인식이 가능하지 않다면
		return false;
	}

	var recognition = new webkitSpeechRecognition();
	var isRecognizing = false;
	var ignoreOnend = false;
	var finalTranscript = '';
	var audio = document.getElementById('audio');
	var $btnMic = $('#btn-mic');
	var $result = $('#result');

	var recheck = false;//'다시'라고 말할 경우 검색창 리셋하기 위한 조건

	recognition.continuous = true;//각 인식에 대해 연속 결과(true)를 반환할지 또는 단일 결과(false)만 반환할지 여부를 제어한다. 기본값(false)
	recognition.interimResults = true;//중간 결과를 반환할지 (true) 또는 반환하지 않을지 (false)를 조정. 중간 결과는 아직 최종적이지 않은 결과

	recognition.onstart = function() {//음성 인식 서비스가 현재 SpeechRecognition과 관련된 문법을 인식하기 위해 들어오는 오디오를 듣기 시작하면 발동됩니다.
		isRecognizing = true;

		$btnMic.attr('class', 'on');
	};

	recognition.onend = function() {
		isRecognizing = false;

		if (ignoreOnend) {
			return false;
		}

		// DO end process
		$btnMic.attr('class', 'off');
		if (!finalTranscript) {
			return false;
		}

	};

	//음성 인식 서비스가 결과를 반환하면 발동됩니다. 단어나 문구가 올바르게 인식되어 앱으로 다시 전달되었습니다.(event = 음성인식 이벤트(객체 안에 음성인식된 결과들도 포함되있음)
	//event객체안의 results객체인 SpeechRecognitionAlternative 인터페이스는 음성 인식 서비스에서 인식 한 단일 단어를 나타냅니다.(만약 3글자의 단어를 인식했다면 Alternative 객체가 3번 생성)
	//Web Speech API의 SpeechRecognitionResult 인터페이스는 단일 인식 일치를 나타내며 여러 개의 SpeechRecognitionAlternative 개체가 포함될 수 있습니다.
	//Web Speech API의 SpeechRecognitionResultList 인터페이스는 SpeechRecognitionResult 개체의 목록을 나타내거나, 결과가 연속 모드로 캡처되는 경우에는 연속된 결과를 하나로 하여 하나의 개체를 나타냅니다.
	//(크롬으로 실행시 개발자 도구를 확인하면 SpeechRecognitionResultList객체가 event.results의 객체로 나타남
	recognition.onresult = function(event) {

		if (typeof (event.results) == 'undefined') {//음성 인식 결과가 undefined라면
			recognition.onend = null;//음성 인식 서비스가 끊어지면 발동됩니다.
			recognition.stop();
			return;
		}

		if (recheck) {//recheck가 true라면 검색창 리셋
			finalTranscript = '';
			recheck = false;
		}
		
		for (var i = event.resultIndex; i < event.results.length; ++i) {
			if (event.results[i].isFinal) {//isFinal 속성은 결과가 최종인지 (true) 또는 그렇지 않은지 (false) 나타냄(최종이라면 = 음성인식이 끝났다면)
				finalTranscript += event.results[i][0].transcript;//transcript = 인식 된 단어의 사본이 포함 된 문자열을 반환합니다.
			} 
		}
		
		$('#soundsearch').val(linebreak(finalTranscript));
	
		finalTranscript = capitalize(finalTranscript);//문자열에서 공백으로 구분 된 모든 단어를 대문자로 변환합니다.

		fireCommand(finalTranscript);
	};
	
	function fireCommand(string) {
		if (string.indexOf("다시") != -1) {

			recheck = true;
			$('#soundsearch').val("");
			
		} else if (string.indexOf("검색") != -1){
			
			var str = string.split("검색");
			for( var i in str){
				if( i == 0 ){
					str = str[i];
					break;
				}
			}

			$("#soundsearch").val(str);
			$("#soundsubmit").click();
			recognition.stop();
			
			}
	}

	recognition.onerror = function(event) {

		if (event.error == 'not-allowed') {
			ignoreOnend = true;
		}

		$btnMic.attr('class', 'off');
	};

	/* 아래 코드의 /와 /g가운데에 정규표현식을 넣으세요.
	 * g는 global의 약자로, 정규표현식과 일치하는 모든 내용을 찾아오라는 옵션입니다.
	 * g가 있을 때와 없을 때 출력이 차이나는걸 확인 해 보세요.
	 */
	var two_line = /\n\n/g;//(=\n\n. 2줄 개행)
	var one_line = /\n/g;//(=\n. 개행문자)
	var first_char = /\S/;//공백 문자

	function linebreak(s) {
		return s.replace(two_line, '<p></p>').replace(one_line, '<br>');//메소드 체이닝
	}

	function capitalize(s) {
		return s.replace(first_char, function(m) {
			return m.toUpperCase();
		});
	}

	function start(event) {
		if (isRecognizing) {//isRecognizing값이  true라면 인식기능 작동하지 않음
			recognition.stop();
			return;
		}

		recognition.lang = "ko_KR";//음성인식 언어 = 한글
		recognition.start();//음성인식 시작
		ignoreOnend = false;

		finalTranscript = '';

	}
	
	$btnMic.click(start);//btnMic를 클릭하면 start함수 작동
	
});
	
function trim(trimString) {//trim() 메소드
    return trimString.replace( /\s*/g, "");
}