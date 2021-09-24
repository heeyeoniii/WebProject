package com.kh.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.board.model.service.BoardService;
import com.kh.web.board.model.vo.Board;


@WebServlet("/updateView.bo")
public class BoardUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUpdateView() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		Board b = new BoardService().updateView(bno);
		
		String page = "";
		
		if(b != null) {
			request.setAttribute("board", b);
			page = "views/board/boardUpdate.jsp";
		} else {
			request.setAttribute("error-msg", "게시글 수정화면 연동 실패!");
			page = "views/common/errorPage.jsp";
		}	
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
