package com.kh.web.thumb.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.thumb.model.service.ThumbnailService;
import com.kh.web.thumb.model.vo.Thumbnail;

@WebServlet("/selectList.tn")
public class ThumbnailSelectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ThumbnailSelectList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Thumbnail> list = new ArrayList<>();
		ThumbnailService service = new ThumbnailService();
		
		list = service.selectList();
		
		String page = "";
		
		if(list != null) {
			request.setAttribute("list", list);
			page = "views/thumb/thumbList.jsp";
		} else {
			request.setAttribute("error-msg", "사진 게시글 목록 조회 실패");
			page = "views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
