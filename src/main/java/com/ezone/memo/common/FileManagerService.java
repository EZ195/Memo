package com.ezone.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	private String FILE_UPLOAD_PATH = "C:\\Users\\EZi09\\Desktop\\ezone\\memo\\upload\\images/";
	//파일저장
	//파일 저장 후 접근 경로 리턴
	public String saveFile(int userId, MultipartFile file) {
		
		// 파일경로
		// userId 가져오는 이유 : 사용자별로 파일이름이 겹치는 걸 방지하기 위해 사용자별로 폴더를 구분한다.
		// 같은 사용자가 같은 파일이름 겹치는 것 방지를 위해서 혀재 시간을 폴더 이름에 포함시킨다.
		// UNIX time : 1970년 1월 1일 0시 0분0초 기준으로 현재 몇 micro초(1/1000초)가 지났는지
		// System.currentTimeMillis();로 사용
		
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		// C:\\Users\\EZi09\\Desktop\\ezone\\memo\\upload\\images/6_12912098
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		// 디렉토리 생성
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			// 디렉토리 생성 에러
			return null;
		}
		
		// 파일 저장
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// img src="/images/6_12912654/test.png" 여기에 들어갈 경로를 리턴해주는 것
		return "/images/" + directoryName + file.getOriginalFilename();
	}

}
