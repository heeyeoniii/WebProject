<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// page 영역 객체 생성
	// page 영역 : 페이지 이동 없이 현재 페이지에 내용을 출력하는 객체
	// 단순 정보 페이지, 계산 산출 페이지
	pageContext.setAttribute("page", "Page 영역입니다!");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 영역 확인</title>
</head>
<body>
	<h1>EL 영역 확인하기</h1>
	<hr />
	
	<h3>page 영역 : ${pageScope.page}</h3>
	<h3>Request 영역 : ${requestScope.req}</h3>
	<h3>Session 영역 : ${sessionScope.ses}</h3>
	<h3>Application 영역 : ${applicationScope.app}</h3>
	
	<hr />
	
	<h1>scope 선언 없이 단순 호출하기</h1>
	<p>
		만약 scope 선언을 하지 않으면, <br>
		page &lt; request &lt; session &lt; applicaion 순으로 값을 찾아오게 된다.(가까운거 부터)
	</p>
	<!-- request 영역의 값을 가지고 온다. -->
	<h3>오늘의 점심 메뉴 : ${lunchMenu}</h3> 	
	<span style="color: red">
		* 객체의 이름이 같으면 직접 scope를 명시해야 한다.
	</span>
</body>
</html>