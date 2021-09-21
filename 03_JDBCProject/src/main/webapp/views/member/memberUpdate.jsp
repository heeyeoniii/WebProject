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
<title>회원 정보 수정</title>
<script src="/jdbc/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>

	<%@ include file="../common/header.jsp" %>
	
	<section style="background : lightcyan; padding : 10px 5px;">
		
		<h1> <%= m.getUserName() %>님의 회원 정보 수정 화면</h1>
		
		<form action="/jdbc/memberUpdate.do" method="post" id="updateFrm">
			<table>
				<tr>
					<td> 아이디 : </td>
					<td> <%= m.getUserId() %> </td>
					<td>  </td>
				</tr>
				<tr>
					<td> 변경할 PWD : </td>
					<td> <input type="password" name="userPwd" id="userPwd" /> </td>
					<td></td>
				</tr>
				<tr>
					<td> PWD 확인 : </td>
					<td> <input type="password" name="userPwd2" id="userPwd2" /></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3" id="pwdValidate"></td>
				</tr>
				<tr>
					<td>이름 : </td>
					<td> <%= m.getUserName() %> </td>
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
					<td> <input type="number" name="age" min="5" max="80" value="<%= m.getAge() %>"/> </td>
					<td></td>
				</tr>
				<tr>
					<td> 이메일 : </td>
					<td> <input type="text" name="email" value="<%= m.getEmail() %>"/> </td>
					<td></td>
				</tr>
				<tr>
					<td> 연락처 : </td>
					<td> <input type="text" name="phone" value="<%= m.getPhone() %>"/> </td>
					<td></td>
				</tr>
				<tr>
					<td> 주소 : </td>
					<td> <input type="text" name="address" value="<%= m.getAddress() %>"/> </td>
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
						<input type="submit" value="수정하기" />
						&nbsp;&nbsp;
						<input type="button" value="회원 탈퇴" onclick="memberDelete();" />
					</td>
				</tr>
				<!-- tr>td*3 -->
			</table>
		</form>
	
	</section>
	
	<%@ include file="../common/footer.jsp" %>
	<script>
		$('#updateFrm').submit(function(event){
			
			var pwd = $('#userPwd').val();
			var pwd2 = $('#userPwd2').val();
			
			if( pwd != pwd2 ) {
				$('#pwdValidate').text('비밀번호와 확인 값이 일치하지 않습니다!')
								 .show().fadeOut(1000);
			} else if( pwd == '') {
				$('#pwdValidate').text('비밀번호를 반드시 입력해주세요!')
								 .show().fadeOut(1000);
			} else {
				// 정상적으로 입력했다면
				return;
			}
			
			event.preventDefault();
		});
		
		$('input:radio').each(function(){
			if($(this).val() == '<%= m.getGender() %>' ) {
				$(this).prop('checked', true);
			} else {
				$(this).prop('checked', false);
			}
		});
		
		var hobbyArr = '<%= m.getHobby() %>'.split(', ');
		
		console.log(hobbyArr);
		
		$('input:checkbox').each(function(){
			if( $.inArray($(this).val(), hobbyArr) >= 0){
				$(this).prop('checked', true);
			}
		});
		
		function memberDelete(){
			var confirm = window.confirm("정말 탈퇴하시겠습니까?");
			if(confirm == true) {
				location.href = '/jdbc/memberDelete.do';
			} else {
				alert('탈퇴를 수행하지 않았습니다.');
			}
		}
	</script>
</body>
</html>