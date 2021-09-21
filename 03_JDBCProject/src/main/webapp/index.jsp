<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.jdbc.member.model.vo.*" %>
<%
	Member m = (Member)session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC 연습 페이지</title>
</head>
<body>
	<%-- 지시자 태그 --%>
	<%@ include file="views/common/header.jsp" %>
	<h1>JDBC 연습 페이지</h1>
	<% if(m != null) { %>
	<h3><%= m.getUserName() %>님, 환영합니다!</h3>
	<% } %>
	
	<% if(m == null) { %>
	<h3>
		<a href="views/member/join.jsp">회원 가입</a>
	</h3>
	<% } else { %>
	<h3>
		<a href="views/member/memberUpdate.jsp">회원 정보 수정-공사중-</a>
	</h3>
	<% } %>
	
	<h3>
		<a href="views/member/login.jsp">로그인/로그아웃</a>
	</h3>
	
	<%@ include file="views/common/footer.jsp" %>
</body>
</html>