<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.net.HttpURLConnection, java.net.URL, java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQeury와 Ajax</title>
<script src="/ajax/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1>jQuery와 Ajax</h1>

	<h3>1. 버튼 클릭 시 서버로 데이터 전송하기</h3>
	<button onclick="callFunc1();">실행 확인</button>

	<script>
		function callFunc1() {
			// $.get() / $.post()
			// -> ("요청 URL", "전달할 값", "콜백함수");

			/*
			$.ajax({
				url : "요청 주소",
				type : "get/post",
				data : "전달할 값"
				     / { name : "홍길동", age : 20 },
				async : true / false, (비동기 / 동기 사용 여부)  
				dataType : "text/html", // 서버에서 응답하는 데이터의 유형
				success : function(data) { . . . },
				error : function(error, code, msg) { . . . },
				complete : function() { . . . } // 무조건 실행하는 함수				
			})
			 */
			$.ajax({
				url : "/ajax/test1.do",
				type : "get",
				data : {name : '코코'},
				success : function(data) {
					alert("전송 성공!");
				}, error : function(error) {
					alert("전송 실패!");
				}
			});
		}
	</script>
	
	<h3>2. 버튼 클릭 시 서버에서 보낸 값 받아오기</h3>
	<button onclick="callFunc2();">실행 확인</button>
	
	<script>
		function callFunc2() {
			$.ajax({
				url : "/ajax/test2.do",
				type : "get",
				data : {name : window.prompt()},
				success : function(data) {
					alert(data);
				}, error : function(errorcode) {
					alert(errorcode);
				}
			});
		}
	</script>
	
	<h3>3. 서버로 json(자바스크립트 객체) 데이터 전송, 결과 확인하기</h3>
	이름 : <input type="text" id="myName" /> <br />
	나이 : <input type="number" id="myAge" /> <br />
	성별 : <input type="radio" name="gender" value="M" /> 남성
	      <input type="radio" name="gender" value="F" /> 여성 <br />
	<br />
	<button id="test3Btn">실행 확인</button>
	
	<script>
		$('#test3Btn').on('click', function(){
			var jsonData = {};
			
			jsonData.name = $('#myName').val();
			jsonData.age = $('#myAge').val();
			jsonData.gender = $('[name=gender]:checked').val();
			
			console.log(jsonData);
			$.ajax({
				url : "/ajax/test3.do",
				type : "get",
				data : jsonData,
				success : function( data ) {
					alert("데이터 전달 성공");
				}, error : function( errorcode ) {
					alert("데이터 전달 실패");
				}
			});
		});
	</script>
	
	<h3>4. 서버에서 Java 객체 받아오기</h3>
	
	<p>
		서버로 사용자 번호를 보내, <br />
		해당 번호에 맞는 사용자 정보 받아오기 <br />
		<br />
		사용자 번호 : <input type="number" id="userIdx" /> <br /><br />
		<button id="test4Btn">실행 확인</button>
	</p>
	
	<p id="test4Result" style="border: 3px solid hotpink; padding: 10px"></p>
	
	<script>
		$('#test4Btn').on('click', function(){
			$.ajax({
				url : "/ajax/test4.do",
				type : "get",
				data : {userIdx : $('#userIdx').val()},
				success : function(data) {
					// console.log(data);
					var result = data.userNo + ", "
					           + data.userName + ", "
					           + data.gender + ", " 
					           + data.phone;
					
					$('#test4Result').html(result);
					
				}, error : function(errorcode){
					console.log(errorcode);
				}
			});
		});
	</script>
	
	<h3>5. Gson을 활용한 자바 객체 받아오기</h3>
	
	<button id="test5Btn">실행 확인</button>
	
	<p id="test5Result" style="border: 3px solid green; padding: 10px"></p>
	
	<script>
		$('#test5Btn').on('click', function() {
			$.ajax({
				url : "/ajax/test5.do",
				type : "get",
				success : function(data) {
					// console.log(data);
					var result = "";
					
					for(var i in data) {
						var user = data[i];
						
						result += user.userNo + ", "
						        + user.userName + ", "
						        + user.gender + ", "
						        + user.phone + "<br>";
					}
					
					$('#test5Result').html(result);
					
				}, error : function(errorcode) {
					console.log(errorcode);
				}
			});
		});
	</script>
	
	
	
	<br /><br /><br /><br /><br />
	<a href="../index.jsp">메인으로 돌아가기</a>
	<br /><br /><br /><br /><br />

</body>
</html>