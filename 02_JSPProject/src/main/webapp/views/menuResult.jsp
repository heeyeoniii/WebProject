<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="common/errorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 결과</title>
</head>
<body>
	<h2>주문 내역 확인</h2>
	<%
		String menu = (String)request.getAttribute("menu");
		int price = (Integer)request.getAttribute("price");
		// int money = (Integer)request.getAttribute("money");
		int money = Integer.parseInt((String)request.getAttribute("money"));
	%>
	
	<p>
		주문하신 음식 : <%= menu %> <br>
		주문 음식 가격 : <%= price %> <br>
		받은 금액 : <%= money %> <br>
		<hr>
		거스름돈 : <%= money - price %> <br>
	</p>
</body>
</html>






