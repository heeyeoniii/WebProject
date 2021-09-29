package com.kh.ajax.model.vo;

import java.io.Serializable;

public class UserVO implements Serializable {
	// 필드, 멤버
	private int userNo;
	private String userName;
	private String gender;
	private String phone;

	// 생성자	
	public UserVO() { }
	
	public UserVO(int userNo, String userName, String gender, String phone) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.gender = gender;
		this.phone = phone;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserVO [userNo=" + userNo + ", userName=" + userName + ", gender=" + gender + ", phone=" + phone + "]";
	}
}
