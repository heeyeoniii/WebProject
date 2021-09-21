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
<title>로그인 화면</title>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
	<% if(m == null) { %>
	<h3>로그인</h3>
	
	<form action="/jdbc/login.do" method="post">
		ID : <input type="text" name="userId" /> <br />
		PW : <input type="password" name="userPwd" /> <br /><br />
		
		<input type="submit" value="로그인" />
	</form>
	
	<% } else { %>
	<h3>로그아웃</h3>
	
	<h4><%= m.getUserName() %>님, 환영합니다.</h4>
	<p>
		만약 로그아웃을 하시려면 다음 버튼을 클릭해주세요.
		<button type="button" onclick="logout();">로그아웃</button>
		
		<script>
			function logout() {
				location.href = '/jdbc/logout.do';
			}
		</script>
	</p>
	
	<% } %>
	<br /><br />
	<%@ include file="../common/footer.jsp" %>
</body>
</html>