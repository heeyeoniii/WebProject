<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 연습 페이지</title>
</head>
<body>
	<h1>EL (Expression Language)</h1>
	<h3>
		&lt;%= %&gt;, request.getAttribute("XXX") 자바 코드들을<br>
		더욱 쉽게 사용할 수 있도록 만들어진 표현 기술
	</h3>
	
	<hr />
	
	<form action="testELResult.jsp" method="post">
		<h1>색상 선택 테스트</h1>
		<h3>
			이름 : <input type="text" name="memberName"/>			
		</h3>
		<h3>
			나이 : <input type="number" name="memberAge" />
		</h3>
		<h3>
			성별 : <input type="radio" name="gender" value="M" /> 남성 &nbsp;
				  <input type="radio" name="gender" value="F" /> 여성
		</h3>
		<h3>
			색상 선택 : <select name="color" >
					  	  <option value="red">빨강</option>
					  	  <option value="orange">주황</option>
					  	  <option value="yello">노랑</option>
					      <option value="green">초록</option>
					      <option value="blue">파랑</option>
					      <option value="purple">보라</option>	
					  </select>
		</h3>
		<button type="submit">전송하기</button>
		<button type="reset">작성 취소</button>
	</form>
</body>
</html>