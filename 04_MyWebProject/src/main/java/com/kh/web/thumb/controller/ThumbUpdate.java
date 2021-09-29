package com.kh.web.thumb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.web.common.MyRenamePolicy;
import com.kh.web.thumb.model.service.ThumbnailService;
import com.kh.web.thumb.model.vo.Attachment;
import com.kh.web.thumb.model.vo.Thumbnail;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/update.tn")
public class ThumbUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThumbUpdate() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( ! ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", "multipart 전송이 아닙니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		int maxSize = 1024 * 1024 * 10;
		String savePath = request.getServletContext().getRealPath("/resources/thumbFiles");
		
		MultipartRequest mre = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
		
		int bno = Integer.parseInt(mre.getParameter("bno"));
		ThumbnailService service = new ThumbnailService();
		
		// 원본 게시글 조회
		HashMap<String, Object> hmap = service.getUpdateView(bno);
		ArrayList<String> originNames = new ArrayList<>();
		ArrayList<String> changeNames = new ArrayList<>();
		
		Enumeration<String> files = mre.getFileNames(); // 전송한 파일들의 태그명
		
		while( files.hasMoreElements() ) {
			String tagName = files.nextElement();
			
			originNames.add( mre.getOriginalFileName(tagName));
			changeNames.add( mre.getFilesystemName(tagName));
		}
		
		// 게시글 정보 수정 시작!
		
		Thumbnail t = (Thumbnail)hmap.get("thumb");
		
		t.setBtitle( mre.getParameter("btitle"));
		t.setBcontent( mre.getParameter("content"));
		
		// 원본 사진
		ArrayList<Attachment> oldList = (ArrayList<Attachment>)hmap.get("list");
		
		// 변경된 사진
		ArrayList<Attachment> newList = new ArrayList<>();
		
		for( int i = changeNames.size() - 1 ; i >= 0 ; i--) {
			int j = changeNames.size() - 1 - i; // 정방향 변수
			
			if( changeNames.get(i) != null && j <= oldList.size() - 1) {
				// 원래 명단에 있는 사진이 변경되었음
				
				// 이전 파일 삭제
				File oldFile = new File(savePath + "/" + oldList.get(j).getChangename());
				oldFile.delete();
				
				// 새 파일 등록
				oldList.get(j).setOriginalname(originNames.get(i));
				oldList.get(j).setChangename(changeNames.get(i));
				
			} else if(changeNames.get(i) != null && j > oldList.size() - 1) {
				// 새로운 사진이 추가되었을 경우
				
				Attachment a = new Attachment();
				
				a.setBno(bno);
				a.setOriginalname(originNames.get(i));
				a.setChangename(changeNames.get(i));
				a.setFlevel(2);
				
				newList.add(a);
			}
		}
		
		int result = service.updateThumbnail(t, oldList, newList);
		
		if ( result > 0 ) {
			response.sendRedirect("selectList.tn");
		} else {
			request.setAttribute("error-msg", "게시글 수정 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
