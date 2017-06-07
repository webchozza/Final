<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>DOKKY</title>
<jsp:include page="/WEB-INF/views/main/Src.jsp" />
</head>
<body>
	<div id="wrapper">
		<div id="main">
			<div class="inner">
				<tiles:insertAttribute name="header" />
				<div id="mainarea">
				<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
		<tiles:insertAttribute name="menu" />
	</div>
</body>
</html>

