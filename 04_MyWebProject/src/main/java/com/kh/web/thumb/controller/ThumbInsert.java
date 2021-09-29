package com.kh.web.thumb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

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

/**
 * Servlet implementation class ThumbInsert
 */
@WebServlet("/insert.tn")
public class ThumbInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. multipart 전송 여부 확인
		if ( ! ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", "multipart 전송이 아닙니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		// 2. 파일 사이즈 선언
		int maxSize = 1024 * 1024 * 10; // 10MB
		
		// 3. 저장 경로 선언
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + "resources/thumbFiles";
		
		// 4. MultipartRequest 객체 선언
		MultipartRequest mre = new MultipartRequest(request, savePath, maxSize, "UTF-8"
													, new MyRenamePolicy());
		
		// 5. 파일 저장 수행할 객체 선언
		ArrayList<String> originNames = new ArrayList<>();
		ArrayList<String> changeNames = new ArrayList<>();
		
		Enumeration<String> files = mre.getFileNames(); // 순서가 정해지지 않는 객체들을 순서화 시킴
		
		while( files.hasMoreElements()) {
			String tagName = files.nextElement();
			// 여기까지 문제 없음!
			/*
			System.out.println("tagName : " + tagName);
			System.out.println("originName : " + mre.getOriginalFileName(tagName));
			System.out.println("changeName : " + mre.getFilesystemName(tagName));
			*/
			
			originNames.add(mre.getOriginalFileName(tagName));
			changeNames.add(mre.getFilesystemName(tagName));
		}
		
		// ---------- 파일 저장 완료 --------- //
		
		Thumbnail t = new Thumbnail();
		
		t.setBtitle(  mre.getParameter("title"));
		t.setBcontent( mre.getParameter("content"));
		t.setBwriter( mre.getParameter("userId"));
		
		ArrayList<Attachment> list = new ArrayList<>();
		
		for(int i = originNames.size() - 1 ; i >= 0 ; i--) {
			Attachment a = new Attachment();
			
			a.setOriginalname(originNames.get(i));
			a.setChangename(changeNames.get(i));
			
			list.add(a);
		}
		// t.setAttachments(list);
		
		ThumbnailService service = new ThumbnailService();
		
		int result = service.insertThumbnail(t, list);
		
		if( result > 0) {
			response.sendRedirect("selectList.tn");
		} else {
			request.setAttribute("error-msg", "사진 게시글 추가 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
