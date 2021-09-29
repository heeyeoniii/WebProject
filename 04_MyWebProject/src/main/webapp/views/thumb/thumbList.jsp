<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.web.thumb.model.vo.*" %>
<%
	ArrayList<Thumbnail> list = (ArrayList<Thumbnail>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 게시판 목록</title>
<script src="/myWeb/resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="/myWeb/resources/css/header.css" />
<style>
	section {
		width : 1000px;
		height : auto;
		background : black;
		color : white;
		margin-left : auto;
		margin-right: auto;
		padding: 50px;
		margin-top : 50px;
	}
	
	#thumbnailArea {
		width : 760px;
		height : auto;
		margin-left : auto;
		margin-right : auto;
	}
	
	.thumb-list {
		width : 220px;
		border : 1px solid yellow;
		display : inline-block;
		margin : 10px;
		align : center;
	}
	
	.thumb-list:hover {
		opacity : 0.8;
		cursor : pointer;
	}
	
</style>
</head>
<body>

	<%@ include file="../common/header.jsp" %>

	<section>
		<br>
		<h2 align="center">사진 게시판</h2>
		<div id="thumbnailArea">
			<% for(Thumbnail thumb : list) { %>
				<div class="thumb-list" align="center">
					<div>
						<input type="hidden" name="bno" value="<%= thumb.getBno() %>" />
						<img src="<%=request.getContextPath() %>/resources/thumbFiles/<%= thumb.getBoardfile() %>"
						     width="200px" height="150px"/>					
					</div>
					
					<p>
						No. <%= thumb.getBno() + " " + thumb.getBtitle() %>
						<br>
						조회수 : <%= thumb.getBcount() %>
					</p>
				</div>			
			<% } %>
			<br><br>
			<% if(m != null) { %>
				<button onclick="location.href='views/thumb/thumbInsert.jsp'">작성하기</button>
			<% } %>
		</div>
	</section>
	
	<script>
		$(function(){
			$('.thumb-list').click(function(){
				// var bno = $(this).children().children().eq(0).val();
				var bno = $(this).find('input').val();
				// console.log(bno);
				
				location.href = '<%= request.getContextPath()%>/selectOne.tn?bno=' + bno;
			});
		})
	</script>

	<%@ include file="../common/footer.jsp" %>

</body>
</html>