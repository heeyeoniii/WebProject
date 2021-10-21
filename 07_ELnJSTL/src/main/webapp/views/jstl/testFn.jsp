<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문자열 관련 JSTL 태그</title>
</head>
<body>
	<h1>문자열 관련 JSTL 태그</h1>
	<p>
		문자열 관련 fn 태그는 EL 형식 안에서 사용한다.
	</p>
	
	<c:set var="str" value="I am your father." />
	
	<p>
		str : ${ str }
	</p>
	
	<p>
		모두 대문자로 변경 : ${ fn:toUpperCase(str) } <br>
		모두 소문자로 변경 : ${ fn:toLowerCase(str) } <br>
		'father'의 위치 : ${ fn:indexOf(str, 'father') } <br>
		'father' -> 'mother' : ${ fn:replace(str, 'father', 'mother') } <br>
		문자열의 갯수 : ${ fn:length(str) } <br>
		특정 부분 분리하기(am) : ${ fn:substring(str, 2, 4) }
	</p>
	
	<hr />
	
	<h3>split과 join</h3>	
	<p>
		split : 주어진 문자열을 특정 구분자로 쪼개는 함수 <br>
		join : 쪼개진 문자열을 하나의 문자열로 합치는 함수
	</p>
	
	<c:set var="items" value="귤,유자차,붕어빵,롱패딩,핫팩,오뎅,스키장" />
	<c:set var="splitItem" value="${ fn:split(items, ',') }" />
	
	<p>
		split : ${ splitItem } <br>
		split[0] : ${ splitItem[0] } <br>
		join : ${ fn:join(splitItem, '/') }
	</p>
	
</body>
</html>