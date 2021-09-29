package com.kh.web.thumb.model.dao;

import static com.kh.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.web.thumb.model.vo.Attachment;
import com.kh.web.thumb.model.vo.Thumbnail;

public class ThumbnailDAO {

	private Properties prop = null;
	
	public ThumbnailDAO() {
		prop = new Properties();
		
		String filePath = ThumbnailDAO.class
				          .getResource("/config/thumb-sql.properties").getPath();
		
		try {
			prop.load( new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

	public ArrayList<Thumbnail> selectList(Connection con) {
		ArrayList<Thumbnail> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			ps = con.prepareStatement(sql);
		
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Thumbnail tn = new Thumbnail();
				
				tn.setBno( rs.getInt("bno"));
				tn.setBtitle( rs.getString("btitle"));
				tn.setBcontent( rs.getString("bcontent"));
				tn.setBwriter( rs.getString("bwriter"));
				tn.setBcount( rs.getInt("bcount"));
				tn.setBdate( rs.getDate("bdate"));
				tn.setBoardfile( rs.getString("changename"));
				
				list.add(tn);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return list;
	}

	public int insertThumbnail(Connection con, Thumbnail t) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = prop.getProperty("insertThumbnail");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, t.getBtitle());
			ps.setString(2, t.getBcontent());
			ps.setString(3, t.getBwriter());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			close(ps);
		}			
		
		return result;
	}

	public int getCurrentBno(Connection con) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String  sql = prop.getProperty("currentBno");
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
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

	public int insertAttachment(Connection con, Attachment attachment) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1,  attachment.getBno());
			ps.setString(2, attachment.getOriginalname());
			ps.setString(3, attachment.getChangename());
			ps.setInt(4,  attachment.getFlevel());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}

	public HashMap<String, Object> selectOne(Connection con, int bno) {
		HashMap<String, Object> hmap = new HashMap<>();
		ArrayList<Attachment> list = new ArrayList<>();
		Thumbnail t = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectOne");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {

				// ------ 게시글 ------ //
				
				t = new Thumbnail();
				
				t.setBno( bno );
				t.setBtitle( rs.getString("btitle"));
				t.setBcontent( rs.getString("bcontent"));
				t.setBwriter( rs.getString("bwriter"));
				t.setBcount( rs.getInt("bcount"));
				t.setBdate( rs.getDate("bdate"));
				
				// ------ 첨부파일(사진) ------ //
				
				Attachment a = new Attachment();
				
				a.setFno(  rs.getInt("fno") );
				a.setBno(  bno  );
				a.setOriginalname( rs.getString("originalname"));
				a.setChangename(   rs.getString("changename"));
				a.setFlevel(       rs.getInt("flevel"));
				a.setUploaddate(   rs.getDate("uploaddate"));
				
				list.add(a);
			}
			
			hmap.put("thumb", t);
			hmap.put("list", list);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return hmap;
	}

	public int updateThumbnail(Connection con, Thumbnail t) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("updateThumb");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, t.getBtitle());
			ps.setString(2, t.getBcontent());
			ps.setInt(3,  t.getBno());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}		
		
		return result;
	}

	public int updateAttachment(Connection con, Attachment a) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("updateAttachment");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString( 1, a.getOriginalname() );
			ps.setString( 2, a.getChangename() );
			ps.setInt(    3, a.getFno() );
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}

	public int deleteThumbnail(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("deleteThumbnail");
		
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

	public int deleteAttachment(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("deleteAttachment");
		
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
