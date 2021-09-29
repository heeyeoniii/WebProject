package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.UserVO;

@WebServlet("/test5.do")
public class Test5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Test5() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<UserVO> userList = new ArrayList<>();

		// 10명의 정보 등록하기
		UserVO user1 = new UserVO(1, "강포동", "남", "010-9999-9129");
		UserVO user2 = new UserVO(2, "김돌현", "남", "010-3665-4875");
		UserVO user3 = new UserVO(3, "박나라", "남", "010-9693-5222");
		UserVO user4 = new UserVO(4, "하이유", "여", "010-3665-4488");
		UserVO user5 = new UserVO(5, "김해술", "남", "010-7863-4444");
		UserVO user6 = new UserVO(6, "심봉선", "여", "011-365-4485");
		UserVO user7 = new UserVO(7, "윤은해", "여", "017-996-4233");
		UserVO user8 = new UserVO(8, "김종만", "남", "010-4443-2222");
		UserVO user9 = new UserVO(9, "장쯔위", "여", "010-6668-2224");
		UserVO user10 = new UserVO(10, "구동", "남", "011-5845-6632");

		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
		userList.add(user5);
		userList.add(user6);
		userList.add(user7);
		userList.add(user8);
		userList.add(user9);
		userList.add(user10);
		
		response.setContentType("application/json");
		new Gson().toJson(userList, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
