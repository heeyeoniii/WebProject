package com.kh.jdbc.member.model.dao;

import static com.kh.jdbc.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.jdbc.member.model.vo.Member;

/**
 * DAO(Data Access Object) 
 * : Service로부터 받은 정보를 실제 DB에 저장하고,
 *   해당 SQL을 실행한 결과를 받아오는 객체
 *   (CRUD 실행 객체)
 */

public class MemberDAO {

	public int insertMember(Connection con, Member joinMember) {
		// 추가된 행의 수
		int result = 0;
		
		// Statement st = null; -> 복잡하고 작성하기 어려움
		PreparedStatement ps = null; 
		
		// 틀을 먼저 만들어두고 시작 = PreparedStatement
		String sql = "INSERT INTO MEMBER VALUES "
				+" (?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";		
		
		try {
			ps= con.prepareStatement(sql);
			
			// 데이터베이스 값 추가 -> DB 숫자 시작은 1부터
			ps.setString(1, joinMember.getUserId());  // 첫번째 물음표에 아이디 넣기
			ps.setString(2, joinMember.getUserPwd()); // ...
			ps.setString(3, joinMember.getUserName());
			ps.setString(4, joinMember.getGender());
			ps.setInt(5, joinMember.getAge());
			ps.setString(6, joinMember.getEmail());
			ps.setString(7, joinMember.getPhone());
			ps.setString(8, joinMember.getAddress());
			ps.setString(9, joinMember.getHobby());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {		
			// ps.close();
			// JDBCTemplate.close(ps);
			close(ps);
		}
		
		return result;
	}

	// 회원 조회 기능
	public Member selectMember(Connection con, Member loginMember) {
		// 1. SQL 객체 선언
		Member result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ? AND PASSWORD = ?";
		
		try {
			// 2. ps에 만들어 놓은 sql 연결하기
			ps = con.prepareStatement(sql);
		
			// 3. ? 채우기
			ps.setString(1, loginMember.getUserId());
			ps.setString(2, loginMember.getUserPwd());
			
			// 4. 결과집합(Result Set) 받아오기
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = new Member();
				
				result.setUserId(rs.getString("USERID")); // 컬럼명
				result.setUserPwd(rs.getString("password")); // 컬럼명(대소문자 구분 X)
				result.setUserName(rs.getString("userName")); // 컬럼명
				result.setGender(rs.getString(4)); // 컬럼 조회 순서
				result.setAge(rs.getInt("age"));
				result.setAddress(rs.getString("address"));
				result.setPhone(rs.getString("phone"));
				result.setEmail(rs.getString("email"));
				result.setHobby(rs.getString("hobby"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 순서대로 작성
			close(rs);
			close(ps);
		}		
		return result;
	}

	public int updateMember(Connection con, Member updateMember) {
		int result = 0; // 업데이트된 행의 수
		PreparedStatement ps = null;
		
		String sql = "UPDATE MEMBER SET PASSWORD= ?, "
				   + " GENDER = ?, AGE = ?, EMAIL = ?, "
				   + " ADDRESS = ?, HOBBY = ?, PHONE = ? "
				   + " WHERE USERID = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, updateMember.getUserPwd());
			ps.setString(2, updateMember.getGender());
			ps.setInt(3, updateMember.getAge());
			ps.setString(4, updateMember.getEmail());
			ps.setString(5, updateMember.getAddress());
			ps.setString(6, updateMember.getHobby());
			ps.setString(7, updateMember.getPhone());
			ps.setString(8, updateMember.getUserId());
			
			// 업데이트를 실행해라
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			close(ps);
		}	
		return result;
	}

	public int deleteMember(Connection con, String userId) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userId); // 첫번째 ?에 userId 넣기
	
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(ps);
		}	
		return result;
	}
}