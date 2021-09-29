package com.kh.web.thumb.model.service;

import static com.kh.web.common.JDBCTemplate.close;
import static com.kh.web.common.JDBCTemplate.commit;
import static com.kh.web.common.JDBCTemplate.getConnection;
import static com.kh.web.common.JDBCTemplate.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.web.thumb.model.dao.ThumbnailDAO;
import com.kh.web.thumb.model.vo.Attachment;
import com.kh.web.thumb.model.vo.Thumbnail;

public class ThumbnailService {
	private Connection con;
	private ThumbnailDAO dao = new ThumbnailDAO();
	
	public ArrayList<Thumbnail> selectList() {
		con = getConnection();
		
		ArrayList<Thumbnail> list = dao.selectList(con);
		
		close(con);
		
		return list;
	}

	public int insertThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		con = getConnection();
		
		// 1. 사진 게시글 저장
		int result1 = dao.insertThumbnail(con, t);
		
		if (result1 > 0) {
			int bno = dao.getCurrentBno(con);
			
			for(int i = 0 ; i < list.size() ; i++) {
				list.get(i).setBno(bno);  // 최근 게시글 번호 전달
			}
		}
		
		// 2. 첨부 파일 저장
		int result2 = 0;
		for(int i = 0 ; i < list.size(); i++) {
			// 첫번째 이미지는 대표 이미지! FLEVEL = 1
			// 나머지 이미지는 FLEVEL = 2
			list.get(i).setFlevel(i == 0 ? 1 : 2);
			
			/* if 방식
			if( i == 0) {
				list.get(i).setFlevel(1);
			} else {
				list.get(i).setFlevel(2);
			}
			*/
			
			result2 = dao.insertAttachment(con, list.get(i));
			
			if(result2 == 0) { // 잘못 실행된 첨부파일 insert가 있다면 반복을 취소해라!
				break;
			}
		}
		
		// 3. 커밋과 롤백
		int totalResult = 0;
		
		if(result1 > 0 && result2 > 0) {
			commit(con);
			totalResult = 1;
		} else {
			rollback(con);
		}
		
		return totalResult;
	}

	public HashMap<String, Object> selectOne(int bno) {
		con = getConnection();
		
		HashMap<String, Object> hmap = dao.selectOne(con, bno);
		
		close(con);
		
		return hmap;
	}

	public HashMap<String, Object> getUpdateView(int bno) {
		con = getConnection();
		
		HashMap<String, Object> hmap = dao.selectOne(con, bno);
		
		close(con);
		
		return hmap;
	}

	public int updateThumbnail(Thumbnail t, ArrayList<Attachment> oldList, ArrayList<Attachment> newList) {
		con = getConnection();
		int result = 0;

		int result1 = dao.updateThumbnail(con, t); 
		
		if( result1 > 0) {
			int result2 = 0;
			
			for(Attachment a : oldList) { // 이전에 있던 사진 목록 
				result2 = dao.updateAttachment(con, a);
				
				if(result2 == 0) {
					break;
				}
			}
			
			for(Attachment a : newList) { // 새로 저장하는 사진 목록
				result2 = dao.insertAttachment(con, a);
				
				if(result2 == 0) {
					break;
				}
			}
			
			if(result2 > 0) {
				commit(con);
				result = 1; // 전체 과정 정상 처리
			} else {
				rollback(con);
			}
		}
		
		close(con);
		
		return result;
	}

	public int deleteThumbnail(int bno, String savePath) {
		con = getConnection();
		
		HashMap<String, Object> hmap = dao.selectOne(con, bno);
		
		int result = dao.deleteThumbnail(con, bno);
		
		if( result > 0) {
			// 게시글 삭제 성공 -> 첨부파일 삭제
			result = dao.deleteAttachment(con, bno);
			
			for(Attachment a : (ArrayList<Attachment>)hmap.get("list")) {
				File f = new File(savePath + a.getChangename());
				
				f.delete();
			}
		}
		
		if( result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
