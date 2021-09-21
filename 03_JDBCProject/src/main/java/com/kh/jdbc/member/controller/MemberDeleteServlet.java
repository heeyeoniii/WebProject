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

@WebServlet("/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 문자셋 변경 -> Filter가 처리
		
		// 2. 로그인한 정보 가져오기
		// DELETE FROM MEMBER WHERE USERID = ? -> 아이디만 필요
		
		HttpSession session = request.getSession(false);
		
		// "member"에는 로그인한 정보가 담겨있다.
		// 위의 정보를 m으로 가져옴(얕은 복사의 개념)
		Member m = (Member)session.getAttribute("member");
		
		String userId = m.getUserId();
		
		// 3. 회원 탈퇴 서비스
		MemberService service = new MemberService();
		
		int result = service.deleteMember(userId);
		
		if(result > 0) {
			session.invalidate(); // 세션 무효화
			response.sendRedirect("index.jsp"); // 메인화면으로 보내기
			
		} else {
			request.setAttribute("error-msg", "회원 탈퇴 실패");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
