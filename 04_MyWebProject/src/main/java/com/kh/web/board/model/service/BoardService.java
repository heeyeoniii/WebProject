package com.kh.web.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.web.board.model.dao.BoardDAO;
import com.kh.web.board.model.vo.Board;

import static com.kh.web.common.JDBCTemplate.*;

public class BoardService {
	private Connection con;
	private BoardDAO dao = new BoardDAO();
	
	public int getListCount() {
		con = getConnection();
		
		int result = dao.getListCount(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<Board> selectList(int currentPage, int limit) {
		con = getConnection();
		ArrayList<Board> list = dao.selectList(con, currentPage, limit);
		
		close(con);
		
		return list;
	}

	public int insertBoard(Board b) {
		con = getConnection();
		
		int result = dao.insertBoard(con, b);
		
		if( result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public Board selectOne(int bno) {
		con = getConnection();
		
		Board b = dao.selectOne(con, bno);
		
		if( b != null ) {
			// 조회수 1 증가
			b.setBcount( b.getBcount() + 1);
			
			int result = dao.updateReadCount(con, bno);
			
			if(result > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		}
		
		close(con);
		
		return b;
	}

	public Board updateView(int bno) {
		con = getConnection();
		
		Board b = dao.selectOne(con, bno);
		
		close(con);
		
		return b;
	}

	public int updateBoard(Board b) {
		con = getConnection();
		int result = dao.updateBoard(con, b);
		
		if( result > 0 ) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int deleteBoard(int bno) {
		con = getConnection();
		
		int result = dao.deleteBoard(con, bno);
		
		if( result > 0 ) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	
	
	
	
}
