package com.kh.web.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		// pineapple.jpg  --> myWeb_20210927_140000.jpg
		
		// 컴퓨터의 시간 가져오기 (천분의 일초)
		long currentTime = System.currentTimeMillis();

		// 시간의 형식 (년월일_시분초)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		
		// 같은 이름의 파일 생성을 방지 (임의의 수)
		int randomNumber = (int)(Math.random() * 10000); // 0 ~ 9999
		
		// pineapple.jpg --> pineapple / .jpg
		String name = oldFile.getName();
		
		int dot = name.lastIndexOf('.');
		String body = ""; // 파일 명
		String ext = "";  // 확장자
		
		if( dot != -1) {
			body = name.substring(0, dot);
			ext = name.substring(dot);
		} else {
			body = name;
		}
		
		// 파일 명 변경 -> myWeb_20210927_140000_100.jpg
		String filename = "myWeb_" + sdf.format(new Date(currentTime)) + "_" + randomNumber + ext;
		
		File newFile = new File(oldFile.getParentFile(), filename);
		
		return newFile;
	}

}
