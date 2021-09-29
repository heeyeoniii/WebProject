package com.kh.web.thumb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.thumb.model.service.ThumbnailService;

/**
 * Servlet implementation class ThumbDelete
 */
@WebServlet("/delete.tn")
public class ThumbDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		ThumbnailService service = new ThumbnailService();
		
		String savePath = request.getServletContext().getRealPath("/resources/thumbFiles");
		
		int result = service.deleteThumbnail(bno, savePath);
		
		if( result > 0 ) {
			response.sendRedirect("selectList.tn");
		} else {
			request.setAttribute("error-msg", "사진 게시글 삭제 실패!");
			
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
