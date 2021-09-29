package com.kh.web.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		// ex) myWeb_20210927.140000.jpg로 변환
		
		// 컴퓨터의 시간 가져오기(1000분의 1초)
		long currentTime = System.currentTimeMillis();
		
		// 시간 형식 : 년월일_시분초
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		
		// 같은 이름의 파일 생성 방지 : 임의의 수 지정
		int randomNumber = (int)(Math.random() * 10000); // 0 ~ 9999
		
		// 파일 명 & 확장자 구분
		// ex) apple.jpg
		String name = oldFile.getName(); // apple.jpg
		int dot = name.lastIndexOf('.'); // 마지막 .의 위치
		
		String body = ""; // 파일 명
		String ext = ""; // 확장자
		
		if(dot != -1) {
			// 확장자가 있을 경우
			body = name.substring(0, dot); // 0 ~ dot 바로 전까지 자르기
			ext = name.substring(dot); // dot 뒤의 위치부터 마지막까지 자르기
		} else {
			body = name;
		}
		
		// 파일 명 변경
		String filename = "myWeb_" + sdf.format(new Date(currentTime)) 
		                + "_" + randomNumber + ext;
		
		// File(File parent, String Child) 
		// : parent 객체 폴더의 child라는 파일에 대한 File 객체 생성
		// getParentFile() : 부모 폴더를 파일 형태로 리턴
		File newFile = new File(oldFile.getParentFile(), filename);
		
		return newFile;
	}
}
