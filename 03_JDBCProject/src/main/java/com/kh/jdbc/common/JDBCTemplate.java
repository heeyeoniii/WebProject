package com.kh.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	// JDBC (Java DataBase Connectivity)
	// : 여러 DAO들 간의 공통적인 JDBC 연결 구문을
	// 하나의 클래스 안의 메소드들로 통합하는 형태의 클래스
	// 중복되는 소스들을 최대한 배재하여 여러 개의 객체를
	// 하나의 템플릿(양식, 와꾸)으로 만듦으로써
	// 공유(static)하여 사용할 수 있는 공통 메소드화 할 수 있다.

	/**
	 * Singleton 디자인 패턴 
	 * 여러 클래스가 공통되는 코드를 필요로 하거나 
	 * 하나의 애플리케이션에서 단 하나의 클래스로 기능들을 관리하고자 할 때 사용하는 
	 * 코딩 패턴 여러 메소드들을 정적인 'static'으로 관리하는 클래스
	 */

	// java.sql.Connection 임포트
	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			// 연결할 주소, 아이디, 비밀번호
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "JSP", "JSP");
			
			// 자동 커밋 방지
			con.setAutoCommit(false);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		return con; // Connection 반환
}
	
	public static void close(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		// 변경사항 저장 메소드
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		// 변경사항 취소 메소드
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
