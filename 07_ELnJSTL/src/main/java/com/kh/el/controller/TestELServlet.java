package com.kh.el.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/testEL.do")
public class TestELServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TestELServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		response.setCharacterEncoding("UTF-8");
		
		// request 영역 : 한 페이지 -> 다른 페이지, 데이터 유지
		// 회원 가입, 게시글 한 개 조회 등
		request.setAttribute("req", "Request 영역입니다!");
		request.setAttribute("lunchMenu", "마라탕");
		
		// session 영역 : 웹 브라우저가 종료될 때까지 데이터 유지
		// 로그인, 로그아웃, 세션 만료가 필요한 작업
		HttpSession session = request.getSession();
		session.setAttribute("ses", "Session 영역입니다!");
		session.setAttribute("lunchMenu", "샌드위치");
		
		// application 영역 : 서버 자체에서 데이터 유지
		// 한정 판매 시간, 티켓 예매 시간 등
		ServletContext application = request.getServletContext();
		application.setAttribute("app", "Application 영역입니다!");
		application.setAttribute("lunchMenu", "삼겹살");
		
		// 위에서 저장한 정보를 forward 방식으로 넘김
		request.getRequestDispatcher("views/el/testScope.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
