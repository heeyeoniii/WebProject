<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 결과 페이지</title>
</head>
<body>
	<h1>EL 사용 결과</h1>
	
	<%-- 기존 방식
	<div style="border: 5px solid <%= request.getParameter("color") %>;">
		<h1>이름 : <%= request.getParameter("memberName") %></h1>
		<h1>나이 : <%= request.getParameter("memberAge") %></h1>
		<h1>성별 : <%= request.getParameter("gender") %></h1>
		<h1>색상 : <%= request.getParameter("color") %></h1>
	</div>
	--%>
	
	<div style="border: 5px solid ${param.color}"> 
		<h1>이름 : ${param.memberName}</h1>
		<h1>나이 : ${param.memberAge}</h1>
		<h1>성별 : ${param.gender}</h1>
		<h1>색상 : ${param.color}</h1>	
	</div>
</body>
</html>