package com.kh.jdbc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout.do")
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MemberLogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// .getSession() / .getSession(true) : 없다면 새로운 세션을 만들어 가져와라
		// .getSession(false) : 없다면 그냥 null로 가져와라
		HttpSession session = request.getSession(false);
		
		// 로그인을 했다면
		if(session != null) {
			System.out.println(session.getId()); // 세션의 아이디 출력
			
			session.invalidate(); // 세션 무효화
		}	
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
