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

@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberUpdateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 문자셋 변경 : EncodingFilter가 처리

		// 2. 회원 정보 수정 데이터 받아오기
		String userPwd = request.getParameter("userPwd");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String hobby = String.join(", ", request.getParameterValues("hobby"));

		// 3. 해당 회원의 변경 전 정보 가져오기
		HttpSession session = request.getSession(false);

		Member updateMember = (Member) session.getAttribute("member");

		// 4. 변경할 회원 정보 처리하기

		updateMember.setUserPwd(userPwd);
		updateMember.setGender(gender);
		updateMember.setAge(age);
		updateMember.setEmail(email);
		updateMember.setPhone(phone);
		updateMember.setAddress(address);
		updateMember.setHobby(hobby);

		System.out.println("변경 정보 : " + updateMember);
		
		// 5. 서비스와 연동
		MemberService service = new MemberService();
		
		int result = service.updateMember(updateMember);
		
		if(result > 0) {
			// 회원 정보 수정 완료 -> 다시 로그인하기(로그인 화면으로)
			session.invalidate(); // 세션 무효화
			response.sendRedirect("views/member/login.jsp");	
			
		} else {
			// 회원 정보 수정 실패 -> 에러페이지로
			request.setAttribute("error-msg", "회원 정보 수정 실패");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
