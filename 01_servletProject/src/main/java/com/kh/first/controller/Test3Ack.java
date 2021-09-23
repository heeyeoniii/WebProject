package com.kh.first.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test3Ack
 */
@WebServlet("/test3Ack.do")
public class Test3Ack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test3Ack() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 문자셋 변경 -test3.do 에서 했으니 패스!
		
		// 2. HTML 작성
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		
		out.println("<head>");
		out.println("<title>색상 테스트</title>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<h1>색상 테스트</h1>");
		
		out.println("<p style='border: 5px solid " + request.getParameter("colorPick") + ";'>");
		
		out.println(request.getParameter("colorPick")+ "선택!<br>");
		out.println("색상 평 : " + request.getAttribute("advice"));
		
		
		out.println("</p>");
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
