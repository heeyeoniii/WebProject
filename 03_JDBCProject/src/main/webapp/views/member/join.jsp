<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
	<h1>가입 양식</h1>
	<form action="/jdbc/memberInsert.do" method="post">
		<table>
			<tr>
				<td> 아이디 : </td>
				<td> <input type="text" name="userId"/> </td>
				<td> <button type="button" id="dupBtn" disabled> 중복 확인 </button> </td>
			</tr>
			<tr>
				<td> PWD : </td>
				<td> <input type="password" name="userPwd" id="userPwd" /> </td>
				<td></td>
			</tr>
			<tr>
				<td> PWD 확인 : </td>
				<td> <input type="password" name="userPwd2" id="userPwd2" /></td>
				<td></td>
			</tr>
			<tr>
				<td>이름 : </td>
				<td> <input type="text" name="userName"/> </td>
				<td></td>
			</tr>
			<tr>
				<td> 성별 : </td>
				<td>
					<input type="radio" name="gender" value="M" /> 남성
					<input type="radio" name="gender" value="F" /> 여성
				</td>
				<td></td>
			</tr>
			<tr>
				<td> 나이 : </td>
				<td> <input type="number" name="age" min="5" max="6"/> </td>
				<td></td>
			</tr>
			<tr>
				<td> 이메일 : </td>
				<td> <input type="text" name="email" /> </td>
				<td></td>
			</tr>
			<tr>
				<td> 연락처 : </td>
				<td> <input type="text" name="phone"/> </td>
				<td></td>
			</tr>
			<tr>
				<td> 주소 : </td>
				<td> <input type="text" name="address" /> </td>
				<td></td>
			</tr>
			<tr>
				<td> 취미 : </td>
				<td>
					<input type="checkbox" name="hobby" value="코딩" /> 코딩 
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="독서" /> 독서 
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="요리" /> 요리
					
					<br>
					
					<input type="checkbox" name="hobby" value="수면" /> 수면 
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="명상" /> 명상
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="RPS" /> RPS
					
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2">
					<input type="submit" value="가입하기" />
					&nbsp;&nbsp;
					<input type="reset" value="작성취소" />
				</td>
			</tr>
			<!-- tr>td*3 -->
		</table>
	</form>
	<br /><br />
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>