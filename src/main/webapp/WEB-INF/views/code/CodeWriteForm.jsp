<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>WriteForm</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./resources/editor2/js/HuskyEZCreator.js" charset="utf-8"></script>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />

</head>
<script type="text/javascript">
  $(function(){
      //전역변수
      var obj = [];              
      //스마트에디터 프레임생성
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: obj,
          elPlaceHolder: "smarteditor",
          sSkinURI: "./resources/editor2/SmartEditor2Skin.html",
          htParams : {
              // 툴바 사용 여부
              bUseToolbar : true,            
              // 입력창 크기 조절바 사용 여부
              bUseVerticalResizer : true,    
              // 모드 탭(Editor | HTML | TEXT) 사용 여부
              bUseModeChanger : true,
          }
      });
      //전송버튼
      $("#bcodeinsert").click(function(){
          //id가 smarteditor인 textarea에 에디터에서 대입
          obj.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
          //폼 submit
          $("#frm").submit();
      });
  });
</script>
<body>

<body>
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
				<h3>Open Source</h3>
				<form name="frm" action="/dokky/bcodeinsert.do" method="post" enctype="multipart/form-data">
				
				   <c:if test="${updateform.board_id ne null}">
				    <input type="hidden" name="board_id" id="board_id" value="${updateform.board_id}">
				   </c:if>
				   
    				<input type="hidden" id="member_id" name="member_id" value="${sessionScope.member_id}">
				    <input type="hidden" id="board_nickname" name="board_nickname" value="${sessionScope.member_name}">

					<!-- BGROUP_ID -->
					<input type="hidden" name="bgroup_id" id="bgroup_id" value="1" />

					<!-- BOARD_TITLE -->
					<div class="6u 12u$(xsmall)" style="width: 90%;">
						<input type="text" name="board_title" id="board_title" value="${updateform.board_title}" style="width: 90%;" placeholder="[글제목] <c:out value="${updateform.board_title}"/>" />
					</div>
					<br />
					
					<c:if test="${updateform.board_id ne null}">
					 <div class="6u 12u$(xsmall)" style="width: 90%;">
					 <strong><font style="color:#f56a6a">업로드 파일 : ${file.bfile_src}<a href="/dokky/bcodeuploaddelete.do?uploaddelete_id=<c:out value="${updateform.board_id}"/>" class="button special small">삭제</a></font> <!-- 여기에 업로드파일 --></strong>
					</div>
					</c:if>
					<br />
					
					<!-- BOARD_CONTENT -->
					<div class="12u$">
      					<textarea name="board_content" id="smarteditor"  rows="10" cols="100" style="width:100%; min-width:200px; height:400px;">${updateform.board_content}</textarea>
     				</div>
					
					<!-- TAG -->
					 <div class="6u 12u$(xsmall)" style="width: 90%;">
					 	<input type="text" name="board_tag" id="message" style="width: 90%" placeholder="[태그]" value="${updateform.board_tag}">
					 </div>
					
					<!-- FILE -->
					<div class="12u$">
					  <input type="file" name="file" id="file" style="width:60%;">
					  <br/>
					  <input type="submit" id="bcodeinsert" style="width: 60%;" value="글쓰기" class="button special">
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>