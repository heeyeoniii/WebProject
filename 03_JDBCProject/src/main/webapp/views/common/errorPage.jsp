<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
	<h1>ERROR 발생</h1>	
	
	<%
		// getAttribute의 반환형 : Object
		String msg = (String)request.getAttribute("error-msg");
	%>
	
	<p style="color : darkred">
		<%= msg %>
	</p>
	
	<p>
		같은 에러를 계속 만나신다면, <br />
		관리자에게 문의하세요.
	</p>
</body>
</html>