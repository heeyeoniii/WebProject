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
<title>사진 게시판 상세보기</title>
<script src="/myWeb/resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="/myWeb/resources/css/header.css" />
<style>
	section {
		width:1000px;
		height:auto;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	.detail td{
		text-align:center;
		width:1000px;
		border:1px solid white;
	}
	#titleImgArea {
		width:500px;
		height:300px;
		margin-left:auto;
		margin-right:auto;
	}
	#contentArea {
		height:30px;
	}
	.detailImgArea {
		width:250px;
		height:210px;
		margin-left:auto;
		margin-right:auto;
	}
	#titleImg {
		width:500px;
		height:300px;
	}
	.detailImg {
		width:250px;
		height:180px;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<section>
	<br><br>
		<table class="detail" align="center">
						<tr>
				<td width="50px">제목</td>
				<td colspan="5"><label><%= t.getBtitle() %></label></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><label><%= t.getBwriter() %></label></td>
				<td>조회수</td>
				<td><label><%= t.getBcount() %></label></td>
				<td>작성일</td>
				<td><label><%= t.getBdate() %></label></td>
			</tr>
			<tr>
				<td>대표사진</td>
				<td colspan="4">
					<div id="titleImgArea" align="center">
						<img id="titleImg" src="<%= request.getContextPath() %>/resources/thumbFiles/<%= titleImg.getChangename() %>">
					</div>
				</td>
				<td>
					<a href="<%= request.getContextPath() %>/resources/thumbFiles/<%= titleImg.getChangename() %>" download="<%= titleImg.getOriginalname() %>"><button type="button">다운로드</button></a>
				</td>
			</tr>
			<tr>
				<td>사진메모</td>
				<td colspan="6">
					<p id="contentArea"><%= t.getBcontent() %></p>
				</td>
			</tr>
		</table>
		<table class="detail">
			<tr>
				<td>
					<div class="detailImgArea">
					<% if (detailImg1.getChangename() != null) { %>
						<img id="detailImg1" class="detailImg" src="<%= request.getContextPath() %>/resources/thumbFiles/<%= detailImg1.getChangename()%>">
						<a href="<%= request.getContextPath() %>/resources/thumbFiles/<%= detailImg1.getChangename()%>" download="<%= detailImg1.getOriginalname()%>"><button type="button">다운로드</button></a>
					<% } else { %>
						<img id="detailImg1" class="detailImg" src="<%= request.getContextPath() %>/resources/images/no-image.png">
					<% } %>
					</div>
				</td>
				<td>
					<div class="detailImgArea">
					<% if (detailImg2.getChangename() != null) { %>
						<img id="detailImg2" class="detailImg" src="<%= request.getContextPath() %>/resources/thumbFiles/<%= detailImg2.getChangename()%>">
						<a href="<%= request.getContextPath() %>/resources/thumbFiles/<%= detailImg2.getChangename()%>" download="<%= detailImg2.getOriginalname()%>">
							<button type="button">다운로드</button>
						</a>
					<% } else { %>
						<img id="detailImg2" class="detailImg" src="<%= request.getContextPath() %>/resources/images/no-image.png">
					<% } %>
					</div>
				</td>
				<td>
					<div class="detailImgArea">
					<% if (detailImg3.getChangename() != null) { %>
						<img id="detailImg3" class="detailImg" src="<%= request.getContextPath() %>/resources/thumbFiles/<%= detailImg3.getChangename()%>">
						<a href="<%= request.getContextPath() %>/resources/thumbFiles/<%= detailImg3.getChangename()%>" download="<%= detailImg3.getOriginalname()%>"><button type="button">다운로드</button></a>
					<% } else { %>
						<img id="detailImg3" class="detailImg" src="<%= request.getContextPath() %>/resources/images/no-image.png">
					<% } %>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<button onclick="location.href='<%= request.getContextPath() %>/selectList.tn'">메뉴로 돌아가기</button>
					<% if(m != null && m.getUserId().equals(t.getBwriter())){ %>
					<button onclick="location.href='<%= request.getContextPath() %>/updateView.tn?bno='+<%=t.getBno()%>">수정하기</button>
					<button onclick="location.href='<%= request.getContextPath() %>/delete.tn?bno='+<%=t.getBno()%>">삭제하기</button>
					<% } %>
				</td>
			</tr>
		</table>
	<br><br>
	</section>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>
