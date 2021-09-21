<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.kh.jdbc.member.model.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공 화면</title>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
	<h1>로그인 성공!</h1>
	<%
		Member m = (Member)request.getAttribute("member");
	%>
	
	<div style="width : 400px; border : 3px solid blue;">
		<h3><%= m.getUserName() %>님, 환영합니다!</h3>
		<p>
			회원 상세 정보 : <br />
			<%= m.toString() %>
		</p>
	</div>
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>