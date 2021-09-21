package com.kh.jdbc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jdbc.member.model.service.MemberService;
import com.kh.jdbc.member.model.vo.Member;

/**
 * Controller : 화면(HTML/JSP)과 Java 파일을 이어주는 클래스
 * HTML에서 작성한 내용을 자바 서비스 기능 클래스에게 전달하고 
 * 그 결과를 다시 HTML(화면)로 전달하는 중간 다리 역할
 * ex) 사람 -- 리모컨(Controller) -- TV
 */
@WebServlet("/memberInsert.do")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 문자셋 변경
		request.setCharacterEncoding("UTF-8");
		
		// 2. join.jsp에서 작성한 정보 받아오기(name 속성 이용)
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		// ERROR 발생 : getParameter의 반환형 -> String
		// int age = request.getParameter("age"); 
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		// 취미는 같은 이름 속성의 값들이 배열 형식으로 전달된다.
		String[] hobby = request.getParameterValues("hobby");
		
		// 데이터베이스는 배열이 없으므로 '문자열화' 해준다.
		// ['독서', '명상', '코딩'] -> "독서, 명상, 코딩"
		String hobbyStringify = String.join(", ", hobby);	
		
		// 3. 객체(VO)를 생성해 값을 한번에 담아 처리하기
		// 가입일자는 자동으로 처리되므로 null
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobbyStringify, null);
		
		System.out.println("가입자 정보 : " + m);	
		
		// 4. 업무(서비스) 처리 객체 생성
		MemberService service = new MemberService();
		
		int result = service.insertMember(m);
		
		if(result > 0) {
			// redirect 방식
			System.out.println("회원 가입 성공!");
			// 성공 시 메인화면으로 이동
			response.sendRedirect("index.jsp"); 
		} else {
			// forward 방식
			System.out.println("회원 가입 실패!");
			
			// 실패 시 에러페이지로 이동
			RequestDispatcher view
				= request.getRequestDispatcher("views/common/errorPage.jsp");
		
			request.setAttribute("error-msg", "회원 가입 실패!");
			
			view.forward(request, response);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post로 들어온 것을 get으로 처리
		doGet(request, response);
	}
}