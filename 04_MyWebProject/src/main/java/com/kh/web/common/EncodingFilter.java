package com.kh.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @WebFilter 는 특정 url이나 서블릿을 거쳐갈 때 <br>
 * 해당 필터를 동작 시켜라 라는 의미를 가짐. <br>
 * ex) /login.do : 로그인 서블릿 실행할 때 필터 먼저 처리해라 <br>
 * ex2) /* : 모든 서블릿 실행 시 필터를 동작시켜라 
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    public EncodingFilter() { }

	public void destroy() { }

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 서블릿 실행 전
		request.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
		
		// 서블릿 실행 후
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
