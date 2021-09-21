package com.kh.jdbc.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter({"/login.do", "/memberInsert.do", "/memberUpdate.do"})
public class EncryptFilter implements Filter {

    public EncryptFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 1. 암호화를 위한 HTTP 통신으로 request 변경하기
		HttpServletRequest req = (HttpServletRequest)request; // 하향 형변환
		
		// 원본 비밀번호 확인
		System.out.println("원본 비밀번호 : " + req.getParameter("userPwd"));
		
		EncryptWrapper ew = new EncryptWrapper(req);
		
		System.out.println("바뀐 비밀번호 : " + ew.getParameter("userPwd"));
		
		chain.doFilter(ew, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
