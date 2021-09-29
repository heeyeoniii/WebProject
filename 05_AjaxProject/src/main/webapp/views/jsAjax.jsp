<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바스크립트와 Ajax</title>
</head>
<body>
	<h1>자바스크립트와 Ajax</h1>

	<p>
		Ajax란, 사용자가 요청한 정보를 응답할 때 <br>
		다른 페이지로 갱신하지 않고, 현재 페이지에 <br> 
		데이터를 추가하는 방식의 비동기 통신 방식. <br>
		<br>
		별도의 페이지 새로고침, 페이지 이동이 일어나지 않기 때문에<br>
		사용자는 실시간 같은 느낌을 받을 수 있고, 서버에서도 갱신한<br>
		최신 정보를 사용자에게 제공할 수 있다. <br>
		<br>
		단, 페이지 내부에 요청한 정보들이 누적되어 처리되기 때문에<br>
		남용할 경우 페이지가 점차적으로 느려질 수 있다.<br>
		(페이지 리소스 과부하 현상)
	</p>

	<h3>1. 버튼 클릭 시 특정 값을 서버에 전달하기</h3>
	<button onclick="callFunc1();">실행 확인</button>

	<script>
		// 1. 서버 연결 객체
		var httpRequest;

		// 2. 서버 연결 요청
		function getHttpRequest() {
			// 브라우저가 IE(인터넷 익스플로러)일 경우
			if (window.ActiveXObject) {
				// IE가 9버전 이상일 경우
				try {
					return new ActiveObject("Msxml2.XMLHTTP");
				} catch (e1) {
					// IE가 8버전 이하일 경우
					try {
						return new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e2) {
						return null;
					}
				}

			} else if (window.XMLHttpRequest) {
				// IE가 아닐 경우
				return new XMLHttpRequest();

			} else {
				// ajax 지원 X
				return null;
			}
		}

		// 비동기 통신 함수 구현
		function callFunc1() {
			// 1. 서버 전송 객체 설정
			httpRequest = getHttpRequest();

			// 2. 연결을 위한 서버 주소 선언
			httpRequest.open("GET", "/ajax/test1.do?name=coco", true);

			// 3. 데이터 통신 절차 확인 함수 선언
			httpRequest.onreadystatechange = callBackFn1;

			// 4. 데이터 전송 시작
			httpRequest.send();
		}

		function callBackFn1() {} // 통신 절차 피드백 함수
	</script>

	<h3>2. 버튼 클릭 시 서버에서 보낸 값 받아오기</h3>
	<button onclick="callFunc2();">실행 확인</button>
	
	<script>
		function callFunc2() {
			httpRequest = getHttpRequest();
			
			var name = window.prompt();
			
			httpRequest.open("GET", "/ajax/test2.do?name=" + name, true);
		
			httpRequest.onreadystatechange = callBackFn2;
			
			httpRequest.send();
		}
		
		// 서버 전송 절차 피드백 함수
		function callBackFn2() {
			if(httpRequest.readyState == 1) {
				alert("연결 설정(open) 상태");
				
			} else if(httpRequest.readyState == 2) {
				alert("send 후 서버에 결과 도착");
			
			} else if(httpRequest.readyState == 3) {
				alert("결과 데이터 전송 중");
			 
			} else if(httpRequest.readyState == 4) {
				alert("결과 전송 완료");
				
				if(httpRequest.status == 200) {
					alert(httpRequest.responseText); 
				} else {
					alert("에러 발생 : " + httpRequest.status);
				}
			}
		} 
	</script>

</body>
</html>