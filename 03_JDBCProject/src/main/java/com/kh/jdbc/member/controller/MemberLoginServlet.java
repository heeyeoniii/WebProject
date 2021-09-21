package com.kh.jdbc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jdbc.member.model.service.MemberService;
import com.kh.jdbc.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 문자셋 변경 -> Filter가 처리
		// request.setCharacterEncoding("UTF-8");
		
		// 1. 로그인 정보 받아오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member loginMember = new Member(userId, userPwd);
		
		// 2. 로그인 서비스 실행
		MemberService service = new MemberService();
		
		loginMember = service.selectMember(loginMember);
		
		// 3. 로그인 성공, 실패에 따른 화면 전환
		if(loginMember != null) {
			// page < request < session < application
			// 로그인 성공
			// request.setAttribute("member", loginMember);
			
			// loginOk.jsp 페이지로 이동
			// RequestDispatcher view = request.getRequestDispatcher("views/member/loginOK.jsp");
			
			// view.forward(request, response);
			
			// session 영역에 담기 -> 로그인 정보 유지
			HttpSession session = request.getSession();
			
			session.setAttribute("member", loginMember);
			
			response.sendRedirect("index.jsp");
			
			
		} else {
			// 로그인 실패
			request.setAttribute("error-msg", "로그인 실패!");
			
			// errorPage.jsp 페이지로 이동
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
