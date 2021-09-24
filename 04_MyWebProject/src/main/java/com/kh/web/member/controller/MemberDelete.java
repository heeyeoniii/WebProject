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

/**
 * Servlet implementation class MemberDelete
 */
@WebServlet("/delete.do")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public MemberDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("mid");
		
		System.out.println("삭제할 회원 아이디 : " + userId);
		
		MemberService service = new MemberService();
		
		int result = service.deleteMember(userId);
		
		if( result > 0) {
			
			HttpSession session = request.getSession(false);
			
			session.invalidate();
			
			response.sendRedirect("index.jsp");
			
		} else {
			
			RequestDispatcher view
			= request.getRequestDispatcher("views/common/errorPage.jsp");
		
			request.setAttribute("error-msg", "회원 정보 삭제 실패");
			
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
