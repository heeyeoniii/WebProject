package com.kh.first.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet3
 */
@WebServlet("/test3.do")
public class TestServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet3() {
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
		
		// 2. 화면 전달 값 받아오기
		String color = request.getParameter("colorPick");
		System.out.println("색 전달 확인 : " + color);
		
		// 3. 확인한 값을 바탕으로 서비스 수행
		String advice = "";
		
		switch( color ) {
		case "red" :
			advice = "열정과 생명을 상징하는 색을 고른 당신, <br>"
			       + "이번 주 금요일엔 시험이 기다리고 있습니다...";
			break;
		case "orange" :
			advice = "용기, 유쾌함을 상징하는 색을 선택한 당신, <br>"
				   + "이번 주 금요일 유쾌한 시험은 어떠신가요?";
			break;
		case "yellow" :
			advice = "돈과 풍요, 그러나 질투를 상징하는 노란색...<br>"
			       + "이번 주 금요일, 시험보고 풍요를 누리세요 ^^";
			break;
		case "green" :
			advice = "평화와 안식을 상징하는 색을 선택한 그대에게..<br>"
			       + "내일 평화로운 하루가 되길 바라며 시험....";
			break;
		case "blue" :
			advice = "힘과 창의력으로 똘똘 뭉친 당신에게 <br>"
			       + "멋진 하루가 될 수 있는 시험을 선사합니다.";
			break;
		case "purple":
			advice = "신비주의 보라색맛을 상징하는 색을 선택한 당신,<br>"
			       + "이번주 금요일 시험 한차례 어떠신가요, 훗!";
			break;
		}
		
		// 4. 화면 구현 전용 서블릿에게 데이터 전달
		RequestDispatcher view = request.getRequestDispatcher("/test3Ack.do");
		
		// 5. 전달할 데이터 패키징
		request.setAttribute("advice", advice);
		
		// 6. 출발
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
