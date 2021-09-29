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

@WebServlet("/insert.tn")
public class ThumbInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThumbInsert() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. multipart 전송 여부 확인
		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("error-msg", "multipart 전송이 아닙니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}

		// 2. 파일 사이즈 선언(10MB)
		int maxSize = 1024 * 1024 * 10;

		// 3. 저장 경로 선언
		String root = request.getServletContext().getRealPath("/"); // webapp 폴더의 경로
		String savePath = root + "resources/thumbFiles";

		// 4. MultipartRequest 객체 선언
		MultipartRequest mre = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());

		// 5. 파일 저장을 수행할 객체 선언
		ArrayList<String> originNames = new ArrayList<>(); // 원본 이름
		ArrayList<String> changeNames = new ArrayList<>(); // 변경된 이름

		// 순서가 정해지지 않은 객체들을 순서화 시켜준다.
		Enumeration<String> files = mre.getFileNames();

		while (files.hasMoreElements()) {
			String tagName = files.nextElement();

			// System.out.println("tagName : " + tagName);
			// System.out.println("originName : " + mre.getOriginalFileName(tagName));
			// System.out.println("changeName : " + mre.getFilesystemName(tagName));

			originNames.add(mre.getOriginalFileName(tagName));
			changeNames.add(mre.getFilesystemName(tagName));
		}
		// 파일 저장 완료

		Thumbnail t = new Thumbnail();

		t.setBtitle(mre.getParameter("title"));
		t.setBcontent(mre.getParameter("content"));
		t.setBwriter(mre.getParameter("userId"));

		ArrayList<Attachment> list = new ArrayList<>();

		for (int i = originNames.size() - 1; i >= 0; i--) {
			// 파일이 거꾸로 담겨있기 때문에
			Attachment a = new Attachment();

			a.setOriginalname(originNames.get(i));
			a.setChangename(changeNames.get(i));

			list.add(a);
		}
		
		
		ThumbnailService service = new ThumbnailService();

		int result = service.insertThumbnail(t, list);
		
		if(result > 0) {
			response.sendRedirect("selectList.tn");
		} else {
			request.setAttribute("error-msg", "사진 게시글 추가 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
