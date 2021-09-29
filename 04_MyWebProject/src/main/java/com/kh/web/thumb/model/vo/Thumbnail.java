package com.kh.web.thumb.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import com.kh.web.board.model.vo.Board;

// Board 클래스 상속
public class Thumbnail extends Board implements Serializable {

	private static final long serialVersionUID = 9090L;
	
	private ArrayList<Attachment> attachments;

	// 생성자
	public Thumbnail() {}

	public Thumbnail(int bno, int boardtype, String btitle, String bcontent, String bwriter, int bcount,
			String boardfile, Date bdate, String status) {
		super(bno, boardtype, btitle, bcontent, bwriter, bcount, boardfile, bdate, status);
	}

	public Thumbnail(String btitle, String bcontent, String bwriter, String boardfile) {
		super(btitle, bcontent, bwriter, boardfile);
	}

	// toString()
	@Override
	public String toString() {
		return "Thumbnail [attachments=" + attachments + "]";
	}

	// Getters & Setters
	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
	}	
}
