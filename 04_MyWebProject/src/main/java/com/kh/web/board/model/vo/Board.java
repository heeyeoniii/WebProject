package com.kh.web.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {
	
	private static final long serialVersionUID = 1005L;
	
	private int bno;
	private int boardtype;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private int bcount;
	private String boardfile;
	private Date bdate;
	private String status;
	
	public Board() { }

	public Board(String btitle, String bcontent, String bwriter, String boardfile) {
		super();
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.boardfile = boardfile;
	}

	public Board(int bno, int boardtype, String btitle, String bcontent, String bwriter, int bcount, String boardfile,
			Date bdate, String status) {
		super();
		this.bno = bno;
		this.boardtype = boardtype;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bcount = bcount;
		this.boardfile = boardfile;
		this.bdate = bdate;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", boardtype=" + boardtype + ", btitle=" + btitle + ", bcontent=" + bcontent
				+ ", bwriter=" + bwriter + ", bcount=" + bcount + ", boardfile=" + boardfile + ", bdate=" + bdate
				+ ", status=" + status + "]";
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getBoardtype() {
		return boardtype;
	}

	public void setBoardtype(int boardtype) {
		this.boardtype = boardtype;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public String getBoardfile() {
		return boardfile;
	}

	public void setBoardfile(String boardfile) {
		this.boardfile = boardfile;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
