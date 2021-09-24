package com.kh.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.board.model.service.BoardService;
import com.kh.web.board.model.vo.Board;

@WebServlet("/selectOne.bo")
public class BoardSelectOne extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardSelectOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// location.href = "<%= request.getContextPath() %>
		//                  /selectOne.bo?bno=" + bno; -> 쿼리스트링
		// ex) 253번 게시글 클릭 시, myWeb/selectOne.bo?bno=253으로 이동

		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardService service = new BoardService();
		
		Board b = service.selectOne(bno);
		
		String page = "";
		
		if(b != null) {
			request.setAttribute("board", b);
			page = "views/board/boardDetail.jsp";
		} else {
			request.setAttribute("error-msg", "게시글 상세 조회 실패!");
			page = "views/common/errorPage.jsp";
		}	
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
