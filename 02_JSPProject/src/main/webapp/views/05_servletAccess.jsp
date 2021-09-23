<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿 연결하기</title>
</head>
<body>
	<%@ include file="common/header.jsp" %>
	
	<div style="padding: 10px; background: mintcream;">
		<form action="/jsp/menuOrder.do" method="get">
			메뉴 선택 :
			<select name="menuSelect">
				<option value="돈까스">돈까스</option>
				<option value="피자">피자</option>
				<option value="제육볶음">제육볶음</option>
				<option value="카레">카레</option>
				<option value="산채비빔밥">산채비빔밥</option>
			</select>
			
			<br>
			
			지불할 금액 : <input type="number" name="money" min="1000" max="30000" /> <br>
			
			<button type="submit">주문하기</button>
			
		</form>
	</div>
	
	<%@ include file="common/footer.jsp" %>
	
</body>
</html>