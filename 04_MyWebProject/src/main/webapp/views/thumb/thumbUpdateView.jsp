<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.web.thumb.model.vo.*"%>
<%
	Thumbnail t = (Thumbnail)request.getAttribute("thumbnail");
	ArrayList<Attachment> fileList
	   = (ArrayList<Attachment>)request.getAttribute("fileList");
	
	Attachment titleImg = fileList.get(0);
	Attachment detailImg1 = fileList.get(1);
	Attachment detailImg2 = fileList.get(2);
	Attachment detailImg3 = fileList.get(3);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 페이지</title>
<script src="/myWeb/resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="/myWeb/resources/css/header.css" />
<style>
	section {
		width : 800px;
		height: 700px;
		background : black;
		color: white;
		margin-left : auto;
		margin-right : auto;
		margin-top: 50px;
		padding : 30px;
	}
	
	table {
		border : 1px solid white;
	}
	
	#updateArea {
		width : 500px;
		height: 450px;
		margin-left: auto;
		margin-right: auto;
	}
	
	#titleImgArea {
		width: 350px;
		height: 200px;
		border: 2px dashed grey;
		text-align: center;
		display: table-cell;
		vertical-align: middle;
	}
	
	[id*=ImgArea]:hover {
		cursor: pointer;
	}
	
	[id*=contentImgArea] {
		width : 150px;
		height: 100px;
		border : 2px dashed grey;
		text-align : center;
		display : table-cell;
		vertical-align: middle;
	}
	
	#btnArea{
		margin-top : 15px;
		margin-left : auto;
		margin-right: auto;
		text-align : center;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<section>
	<h2 align="center">사진 게시글 수정</h2>
	<br>
		<form action="/myWeb/update.tn" method="post" enctype="multipart/form-data">
			<div id="updateArea"> <!-- 게시글 수정 영역 -->
				<input type="hidden" name="bno" value="<%= t.getBno() %>"/>
				<table align="center">
					
					<tr>
						<td width="100px"> 제목 </td>
						<td colspan="3">
							<input type="text" name="btitle" size="45" value="<%=t.getBtitle() %>"/>
						</td>
					</tr>
					<tr>
						<td>대표 이미지</td>
						<td colspan="3">
							<div id="titleImgArea">
								<img id="titleImg" width="350" height="200"
								 src="<%= request.getContextPath()%>/resources/thumbFiles/<%= titleImg.getChangename() %>">           
							</div>
						</td>
					</tr>
					<tr>
						<td>내용 사진</td>
						<td>
							<div id="contentImgArea1">
							<% if (detailImg1.getChangename() != null) { %>
								<img id="contentImg1" width="120" height="100"
								     src="<%=request.getContextPath() %>/resources/thumbFiles/<%= detailImg1.getChangename() %>">
							<% } else { %>
								<img id="contentImg1" width="120" height="100" src="<%= request.getContextPath() %>/resources/images/no-image.png">
							<% } %>
							</div>
						</td>
						<td>
							<div id="contentImgArea2">
							<% if (detailImg2.getChangename() != null) { %>
								<img id="contentImg2" width="120" height="100"
									src="<%=request.getContextPath() %>/resources/thumbFiles/<%= detailImg2.getChangename() %>">
							<% } else { %>
								<img id="contentImg2" width="120" height="100" src="<%= request.getContextPath() %>/resources/images/no-image.png">
							<% } %>
							</div>
						</td>
						<td>
							<div id="contentImgArea3">
							<% if (detailImg3.getChangename() != null) { %>
								<img id="contentImg3" width="120" height="100"
									src="<%=request.getContextPath() %>/resources/thumbFiles/<%= detailImg3.getChangename() %>">
							<% } else { %>
								<img id="contentImg3" width="120" height="100" src="<%= request.getContextPath() %>/resources/images/no-image.png">
							<% } %>
							</div>
						</td>
					</tr>
					<tr>
						<td width="100px">사진 메모</td>
						<td colspan="3">
							<textarea name="content" rows="5" cols="50" style="resize:none;">
							<%= t.getBcontent() %>
							</textarea>
						</td>
					</tr>
		      	</table>
			</div>
			
			<div class="fileArea" id="fileArea">
				<!-- 첨부 사진 추가 영역 -->
				<!-- (input:file#thumbImg$[name=thumbImg$ onchange=loadImg(this, $)])*4 -->
				<input type="file" accept="image/*" name="thumbImg1" id="thumbImg1" onchange="loadImg(this,1);" />
				<input type="file" accept="image/*" name="thumbImg2" id="thumbImg2" onchange="loadImg(this,2);" />
				<input type="file" accept="image/*" name="thumbImg3" id="thumbImg3" onchange="loadImg(this,3);" />
				<input type="file" accept="image/*" name="thumbImg4" id="thumbImg4" onchange="loadImg(this,4);" />
			</div>
			
			<div id="btnArea">
				<button type="submit">수정 완료</button> &nbsp;
				<button type="button" onclick="goDetail();">수정 취소</button>
			</div>
			
		</form>
	<br><br>
	</section>
		<script>
		function goDetail(){
			location.href="/myWeb/selectOne.tn?bno=<%= t.getBno() %>";
		}
	
		$('#titleImgArea').on('click', function(){
			$('#thumbImg1').click();
		});
		
		$('#contentImgArea1').on('click',function(){
			$('#thumbImg2').click();
		});
		
		$('#contentImgArea2').on('click',function(){ 
			$('#thumbImg3').click();
		});
		
		$('#contentImgArea3').on('click',function(){ 
			$('#thumbImg4').click();
		});
		
		$('#fileArea').hide();
		
		// 사진 미리보기 구현
		function loadImg(img, num) {
			if(img.files && img.files[0]) {
				var reader = new FileReader();
				
				reader.onload = function(e){
					switch(num) {
					case 1 : $('#titleImg').attr('src', e.target.result);
							 break;
					case 2 : $('#contentImg1').attr('src', e.target.result);
							 break;
					case 3 : $('#contentImg2').attr('src', e.target.result);
					 		 break;
					case 4 : $('#contentImg3').attr('src', e.target.result);
					 		 break;
					}
				}
				
				reader.readAsDataURL(img.files[0]);
			}
		}
	
	</script>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>













