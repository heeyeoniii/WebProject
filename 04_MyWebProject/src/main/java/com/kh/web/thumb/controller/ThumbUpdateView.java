package com.kh.web.thumb.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.thumb.model.service.ThumbnailService;

/**
 * Servlet implementation class ThumbUpdateView
 */
@WebServlet("/updateView.tn")
public class ThumbUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbUpdateView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		HashMap<String, Object> hmap = null;
		ThumbnailService service = new ThumbnailService();
		
		hmap = service.getUpdateView(bno);
		
		String page = "";
		
		if( hmap != null && hmap.get("thumb") != null) {
			request.setAttribute("thumbnail", hmap.get("thumb"));
			request.setAttribute("fileList", hmap.get("list"));
			
			page = "views/thumb/thumbUpdateView.jsp";
			
		} else {
			
			request.setAttribute("error-msg", "게시글 수정 화면 실패");
			
			page = "views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
