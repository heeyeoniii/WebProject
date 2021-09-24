package com.kh.web.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.board.model.service.BoardService;
import com.kh.web.board.model.vo.Board;
import com.kh.web.board.model.vo.PageInfo;

@WebServlet("/selectList.bo")
public class BoardSelectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardSelectList() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 게시글 목록을 담을 변수 선언
		ArrayList<Board> list = new ArrayList<>();
		BoardService service = new BoardService();
		
		// 페이지 처리(페이지네이션) 시작
		int startPage, endPage; // 첫 페이지, 끝 페이지
		
		// 55개 게시글 -> 10개 in 페이지 1 -> 6개 페이지 필요
		int maxPage;
		
		// 현재 사용자가 보는 페이지
		int currentPage = 1;
		
		// 페이지당 게시글 수
		int limit = 10;
		
		// 만약 사용자가 특정 페이지 정보를 가져왔다면
		if( request.getParameter("currentPage") != null) {
			// request.getParameter() : 지정한 name 속성의 값 가져오기 
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 총 게시글 수 조회
		int listCount = service.getListCount();
		
		System.out.println("총 게시글 수 : " + listCount); 
		
		// 250 / 10 = 25
		// 251 / 10 = 25.1 -> 26
		maxPage = (int)((double)listCount/limit + 0.9);
		
		// 1 ~ 10 & 11 ~ 20
		startPage = (int)(((double)currentPage/limit + 0.9) - 1) * limit + 1;
		
		endPage = startPage + limit - 1; 
		
		// 만약 마지막 페이지가 endPage보다 작다면
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		// 페이지 처리 끝
		
		list = service.selectList(currentPage, limit);
		
		String page = ""; // 이동할 페이지 준비
		
		if(list != null && list.size() > 0) {			
			/*
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			...
			*/
			// 한 번에 처리하도록 VO 생성
			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage); 
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			
			page = "views/board/boardList.jsp";	
			
		} else {
			request.setAttribute("error-msg", "게시글 조회 실패!");
			page = "views/common/error-page";
		}
		
		request.getRequestDispatcher(page).forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
