package com.kh.first.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 연결 방법 <br>
 * 1) web.xml에 직접 하나씩 등록하는 방법 <br>
 * 2) @WebServlet("주소") 어노테이션으로 등록하는 방법 <br>
 * ** 둘을 같이 선언할 수 없다!
 */
@WebServlet("/test2.do")
public class TestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 문자셋 변경
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 2. 사용자가 보낸 데이터(name 속성의 값) 확인
		String userId = request.getParameter("userId");
		String password = request.getParameter("userPw");
		
		System.out.println(userId + " / " + password);
		
		// 3. 전송받은 데이터를 활용하여 HTML 작성하기
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		
		out.println("<head>");
		out.println("<mata charset='UTF-8'>");
		out.println("<title>로그인 결과</title>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<h1>로그인 결과</h1>");
		out.println("아이디 : " + userId + "<br>");
		out.println("비밀번호 : " + password);
		
		out.println("</body>");
		
		out.println("</html>");
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
