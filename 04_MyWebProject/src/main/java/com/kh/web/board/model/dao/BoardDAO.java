package com.kh.web.board.model.dao;

import static com.kh.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.web.board.model.vo.Board;

public class BoardDAO {
	private Properties prop;

	public BoardDAO() {
		prop = new Properties();
		String filePath = BoardDAO.class.getResource("/config/board-sql.properties").getPath();

		try {
			prop.load(new FileReader(filePath));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 전체 게시글 수 조회
	public int getListCount(Connection con) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = prop.getProperty("listCount");

		try {
			ps = con.prepareStatement(sql);

			// executeQuery() : 수행 결과로 ResultSet 객체의 값 반환
			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(ps);
		}

		return result;
	}

	// 페이지당 게시글 목록 조회
	public ArrayList<Board> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = prop.getProperty("selectList");

		try {
			ps = con.prepareStatement(sql);

			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;

			ps.setInt(1, endRow); // 첫 번째 ?에 endRow 대입
			ps.setInt(2, startRow); // 두 번째 ?에 startRow 대입

			rs = ps.executeQuery();

			while (rs.next()) {
				Board b = new Board();

				// 게시글 정보 받아오기
				b.setBno(rs.getInt("bno"));
				b.setBoardtype(rs.getInt("boardtype"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcontent(rs.getString("bcontent"));
				b.setBwriter(rs.getString("bwriter"));
				b.setBcount(rs.getInt("bcount"));
				b.setBdate(rs.getDate("bdate"));
				b.setBoardfile(rs.getString("boardfile"));

				// 게시글 list에 담기
				list.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(ps);
		}
		return list;
	}

	// 게시글 작성
	public int insertBoard(Connection con, Board b) {
		int result = 0;
		PreparedStatement ps = null;

		String sql = prop.getProperty("insertBoard");

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, b.getBtitle());
			ps.setString(2, b.getBcontent());
			ps.setString(3, b.getBwriter());
			ps.setString(4, b.getBoardfile());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(ps);
		}
		return result;
	}

	// 게시글 1개 조회
	public Board selectOne(Connection con, int bno) {
		Board b = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = prop.getProperty("selectOne");

		try {
			ps = con.prepareStatement(sql);

			ps.setInt(1, bno);

			rs = ps.executeQuery();

			if (rs.next()) {
				b = new Board();

				b.setBno(rs.getInt("bno"));
				b.setBoardtype(rs.getInt("boardtype"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcontent(rs.getString("bcontent"));
				b.setBwriter(rs.getString("bwriter"));
				b.setBcount(rs.getInt("bcount"));
				b.setBoardfile(rs.getString("boardfile"));
				b.setBdate(rs.getDate("bdate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(ps);
		}
		return b;
	}

	// 조회수 1 증가
	public int updateReadCount(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;

		String sql = prop.getProperty("updateReadCount");

		try {
			ps = con.prepareStatement(sql);

			ps.setInt(1, bno);

			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close(ps);
		}

		return result;
	}

	// 게시글 수정
	public int updateBoard(Connection con, Board b) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = prop.getProperty("updateBoard");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, b.getBtitle());
			ps.setString(2, b.getBcontent());
			ps.setString(3, b.getBoardfile());
			ps.setInt(4, b.getBno());		
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(ps);
		}
	
		return result;
	}

	// 게시글 삭제
	public int deleteBoard(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = prop.getProperty("deleteBoard");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			result = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(ps);
		}
		
		return result;
	}
}
