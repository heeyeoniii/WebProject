package com.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuOrderServlet
 */
@WebServlet("/menuOrder.do")
public class MenuOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 문자셋 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2. 전달받은 데이터 확인
		String menu = request.getParameter("menuSelect");
		
		System.out.println("menu : " + menu);
		
		// 3. 비즈니스 로직 (업무 로직 : 기능과 관련된 코드 작성)
		int price = 0;
		
		switch(menu) {
		// 돈피제카산
		case "돈까스" :
			price = 8000;
			break;
		case "피자" :
			price = 12900;
			break;
		case "제육볶음" :
			price = 7000;
			break;
		case "카레" : 
			price = 6000;
			break;
		case "산채비빔밥" :
			price = 5000;
			break;
		}
		
		// 4. 실행 결과 담기
		request.setAttribute("menu", menu);
		request.setAttribute("price", price);
		request.setAttribute("money", request.getParameter("money"));
		
		// 5. forward 출발!
		RequestDispatcher view = request.getRequestDispatcher("views/menuResult.jsp");
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
