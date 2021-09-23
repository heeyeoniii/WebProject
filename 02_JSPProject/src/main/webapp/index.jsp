<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 수업내용</title>
</head>
<body>
	<%@ include file="views/common/header.jsp" %>
	<h1>Hello JSP!</h1>
	
	<h3>JSP 설명 :</h3>
	<p>
		JSP(Java Server Page), <br>
		1997년도에 개발된 Servlet을 사용하던 <br>
		화면 구현 개발자들이 기존 Servlet에 <br>
		HTML, CSS 등의 화면 코드를 삽입하는데 <br>
		어려움을 느껴 자바 개발사에 항의하여<br>
		1998년에 만들어진 기술이다.<br>
		이 기술로 인해 Servlet 코드 중 화면 관련 코드를 <br>
		별도의 JSP로 쪼갬으로써 비즈니스(서비스)영역과 <br>
		화면 구현 영역을 보다 쉽게 분리할 수 있게 되었다.
	</p>
	
	<hr />
	
	<h3>수업 내용</h3>
	
	<ol>
		<li>
			<a href="views/01_page.jsp">JSP 태그</a>
		</li>
		<li>
			<a href="views/02_page.jsp">자바 라이브러리 사용하기</a>
		</li>
		<li>
			<a href="views/03_page.jsp">include 사용하기</a>
		</li>
		<li>
			<a href="views/04_myPage.jsp">마이 페이지(화면 모듈화)</a>
		</li>
		<li>
			<a href="views/05_servletAccess.jsp">서블릿 연결하기</a>
		</li>
	</ol>
	
	<%@ include file="views/common/footer.jsp" %>
</body>









</html>