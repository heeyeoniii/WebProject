package com.kh.web.thumb.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Attachment implements Serializable {

	private static final long serialVersionUID = 1200L;

	// Attachment 테이블의 컬럼과 동일하게
	private int fno;
	private int bno;
	private String originalname;
	private String changename;
	private int flevel;
	private Date uploaddate;
	private String status;

	public Attachment() {}

	public Attachment(int fno, int bno, String originalname, String changename, int flevel, Date uploaddate,
			String status) {
		this.fno = fno;
		this.bno = bno;
		this.originalname = originalname;
		this.changename = changename;
		this.flevel = flevel;
		this.uploaddate = uploaddate;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Attachment [fno=" + fno + ", bno=" + bno + ", originalname=" + originalname + ", changename="
				+ changename + ", flevel=" + flevel + ", uploaddate=" + uploaddate + ", status=" + status + "]";
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getOriginalname() {
		return originalname;
	}

	public void setOriginalname(String originalname) {
		this.originalname = originalname;
	}

	public String getChangename() {
		return changename;
	}

	public void setChangename(String changename) {
		this.changename = changename;
	}

	public int getFlevel() {
		return flevel;
	}

	public void setFlevel(int flevel) {
		this.flevel = flevel;
	}

	public Date getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
