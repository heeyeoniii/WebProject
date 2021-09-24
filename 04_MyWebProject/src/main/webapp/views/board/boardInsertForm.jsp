<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="/myWeb/resources/css/header.css" />
<script src="/myWeb/resources/js/jquery-3.6.0.min.js"></script>
<style>
	.outer {
		width:900px;
		height:500px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	table {
		padding: 15px;
		border:1px solid white;
	}

	.tableArea {
		width:500px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
	<% if ( m != null ) { %>
	
		<div class="outer">
			
			<br>
			
			<h2 align="center">게시글 작성</h2>
			<div class="tableArea">
			
				<!-- <%= request.getContextPath() %> : 절대 경로(myWeb) -->
				
				<form action="<%= request.getContextPath() %>/Insert.bo"
				      method="post" enctype="multipart/form-data">
				      <table>
				      	<tr>
				      		<td>제목</td>
				      		<td colspan="3">
				      			<input type="text" name="title" size="40" />
				      		</td>
				      	</tr>
				      	<tr>
				      		<td>작성자</td>
				      		<td colspan="3">
				      			<%= m.getUserName() %>
				      			<input type="hidden" name="userId" 
				      			       value="<%= m.getUserId() %>" />
				      		</td>
				      	</tr>
				      	<tr>
				      		<td>첨부 파일</td>
				      		<td colspan="3">
				      			<input type="file" name="file" id="file" />
				      		</td>
				      	</tr>
				      	<tr>
				      		<td>내 용</td>
				      		<td colspan="3">
				      			<textarea name="content"
				      			          cols="50" rows="15"
				      			          style="resize:none;"></textarea>
				      		</td>
				      	</tr>
				      </table>
				      <br>
				      <div align="center">
				      	  <button type="submit">게시글 등록</button>
				      	  <button type="reset">작성 취소</button>
				      </div>
				</form>
			
			</div>
		</div>
	
	<% } else { 
		request.setAttribute("error-msg", "회원만 접근 가능합니다!");
		
		request.getRequestDispatcher("../common/errorPage.jsp")
		       .forward(request, response);
	 } %>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>