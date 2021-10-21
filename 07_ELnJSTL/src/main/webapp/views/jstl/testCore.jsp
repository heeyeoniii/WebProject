<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Core 라이브러리</title>
</head>
<body>
	<h1>Test Core 라이브러리</h1>
	<h3>c:set - 변수 선언(마치 자바 변수처럼)</h3>
	<c:set var="num1" value="100" />
	<c:set var="num2" value="200" />
	<c:set var="result" value= "${num1+num2}" scope="session" />
	
	<p>
		num1 : ${num1} <br>
		num2 : ${num2} <br>
		result : ${result} <br>
	</p>
	
	<hr />
	
	<c:set var="colorArr">
		red, orange, yellow, green, blue, purple
	</c:set>
	
	<p>
		문자열 확인 : ${colorArr}
	</p>
	
	<h3>c:remove - 생성한 객체 삭제</h3>
	<c:remove var="result" scope="session" />
	
	<p>
		result : ${result}
	</p>
	
	<h3>c:out - 화면 출력 태그</h3>
	일반 태그 : <c:out value="<h3>일반 출력</h3>" /> <br>
	태그로 출력 : <c:out value="<h2>태그로 출력</h2>" escapeXml="false" /> <br>
	전달받은 값 출력 : <c:out value="${ param.data }" default="받은 정보 없음" />
	
	<hr />
	
	<h2>JSTL - 조건문</h2>
	<h3>c:if - 조건문</h3>
	<p>* if 밖에 없음</p>
	
	<c:if test="${ num1 > num2 }" >
		<p>num1이 더 큽니다</p>
	</c:if>
	<c:if test="${ num1 < num2 }">
		<p>num2가 더 큽니다</p>
	</c:if>
	
	<h3>c:choose - 자바 if / switch</h3>
	<c:set var="memberType" value="A" />
	<c:choose>
		<c:when test="${ memberType eq 'A' }">
			A타입 회원님 반갑습니다.
		</c:when>
		<c:when test="${ memberType eq 'B' }">
			B타입 회원님 안녕하세요.
		</c:when>
		<c:otherwise>
			올바르지 않은 회원타입입니다.
		</c:otherwise>
	</c:choose>
	
	<hr />
	
	<h2>c:forEach - 자바 반복문</h2>
	<h3>일반 반복문</h3>
	<c:forEach var="i" begin="1" end="10" step="2">
		반복 확인 : ${ i } <br />
	</c:forEach>
	
	<h3>문자열 반복문</h3> <!-- 문자열을 쉼표 기준으로 나눠서 반복한다. -->
	<c:forEach var="color" items="${ colorArr }">
		<p style="background: ${ color }">반복 내용입니다!</p>		
	</c:forEach>
	
	<br />
	
	<h3>ArrayList와 c:forEach</h3>
	<%
		java.util.ArrayList<String> movies = new java.util.ArrayList<>();
		movies.add("해리포터");
		movies.add("아이언맨");
		movies.add("코코");
		movies.add("사운드오브뮤직");
	%>
	
	<ul>
		<c:forEach items="<%= movies %>" var="title" varStatus="stat">
			<li>
				번호 : ${ stat.index } / 순번 : ${ stat.count } / 제목 : ${ title }
			</li>
		</c:forEach>
	</ul>
	
	<hr />
	
	<h3>c:import - jsp:include의 역할</h3>
	<c:import url="import.jsp">
		<c:param name="test" value="코코" />
	</c:import>
	
</body>
</html>