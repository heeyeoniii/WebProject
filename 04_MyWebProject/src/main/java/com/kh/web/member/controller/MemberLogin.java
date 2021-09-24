package com.kh.web.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.member.model.service.MemberService;
import com.kh.web.member.model.vo.Member;

/**
 * Servlet implementation class MemberLogin
 */
@WebServlet("/login.do")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 여기까지 문제 없음!
		System.out.println("서블릿 전달 확인 : " + userId + "/" + userPwd);
		
		Member loginMember = new Member(userId, userPwd);
		
		MemberService service = new MemberService();
		
		loginMember = service.selectMember(loginMember);
		
		System.out.println("받아온 회원 정보 확인 : " + loginMember);
		
		if( loginMember != null ) {
			// 로그인 성공!
			
			HttpSession session = request.getSession();
			
			session.setAttribute("member", loginMember);
			
			response.sendRedirect("index.jsp"); 
			// 1. url 주소가 변경된다.
			// 2. request 정보를 전달하지 않는다.
			
		} else {
			// 로그인 실패!
			
			request.setAttribute("error-msg", "로그인 실패!, 아이디나 비밀번호 확인 필요!");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			view.forward(request, response);		
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
