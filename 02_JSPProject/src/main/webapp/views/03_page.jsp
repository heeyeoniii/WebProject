<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 사용하기</title>
</head>
<body>
	<%@ include file="common/header.jsp" %>
	<h1>include 사용하기</h1>
	<!-- <h3>오늘 날짜 : </h3> -->
	<%@ include file="02_page.jsp" %>
	
</body>
</html>