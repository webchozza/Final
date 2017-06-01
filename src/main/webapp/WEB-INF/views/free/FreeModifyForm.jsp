<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<html>
<head>
<title>ModifyForm</title>
<link rel="stylesheet" href="assets/css/main.css" />
<script type="text/javascript">
   function gosubmit1() {
      if (frm.board_title.value == "" || frm.board_title.value == null) {
         alert("제목을 입력해주세요");
         frm.board_title.focus();
         return false;
      }
      if (frm.board_content.value == "" || frm.board_content.value == null) {
         alert("글내용을 입력해주세요");
         frm.board_content.focus();
         return false;
      }
   }
   
   $(function(){
       //전역변수선언
       var editor_object = [];
        
       nhn.husky.EZCreator.createInIFrame({
           oAppRef: editor_object,
           elPlaceHolder: "smarteditor",
           sSkinURI: "${pageContext.request.contextPath}/resources/editor/SmartEditor2Skin.html",
           htParams : {
               // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
               bUseToolbar : true,            
               // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
               bUseVerticalResizer : true,    
               // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
               bUseModeChanger : true,
           }
       });
        
       //전송버튼 클릭이벤트
       $("#savebutton").click(function(){
           //id가 smarteditor인 textarea에 에디터에서 대입
           editor_object.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
            
           // 이부분에 에디터 validation 검증
            
           //폼 submit
           $("#frm").submit();
       })
   })
</script>
</head>
<div id="wrapper">
   <div id="main" align="center">
      <div class="inner">
         <h3>Community</h3>
         <form name="frm" action="bfreemodify.do"
            onsubmit="return gosubmit1()">

            <div class="6u 12u$(xsmall)" style="width:90%;">
               <input type="text" name="board_title" id="subject"
                  value="${boardDTO.board_title }" style="width: 90%;"
                  placeholder="제목" />
            </div>
            <div class="12u$" style="width:90%;">
               <textarea name="board_content" id="smarteditor" rows="10" cols="100" style="width:100%; min-width:200px; height:400px;">${boardDTO.board_content }</textarea>
            </div>
            <div class="6u 12u$(xsmall)" style="width: 90%;">
				<input name="board_tag" type="text" id="message" style="width: 90%;" placeholder="태그" value="${boardDTO.board_tag}">
            </div>
            <div class="12u$">
               <ul class="actions" style="float: inherit;">
               <input type="hidden" name="session_id" value="${sessionScope.member_id}">
                  <input type="hidden" name="currentPage" value="${currentPage }">
                  <input type="hidden" name="board_id" value="${boardDTO.board_id}">
                  <input type="submit" style="width: 300pt;" value="수정"
                     class="button special" id="savebutton">
               </ul>
            </div>
         </form>
      </div>
   </div>
</div>
</html>