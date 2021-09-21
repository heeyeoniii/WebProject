package com.kh.jdbc.common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		// 원문 -> 암호화된 문장으로 바꾸기("userPwd")
		if(name != null && name.equals("userPwd")) {
			// 암호화 진행 : SHA-512(해시 알고리즘)
			return getSHA512(super.getParameter(name));
			
		} else {
			return super.getParameter(name);
		}
	}
	
	// SHA-512 암호화 기능 메소드
	private static String getSHA512(String password) {
		
		// 자바 버전에 따라 지원하지 않을 수도 있다 -> try & catch
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			// 비밀번호를 바이트 단위로 처리
			byte[] bytes = password.getBytes(Charset.forName("UTF-8"));
			md.update(bytes);
			
			return Base64.getEncoder().encodeToString(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			System.out.println("암호화 모듈 부재로 인한 에러 발생");
			e.printStackTrace();
		}
		return null;
	}
}
