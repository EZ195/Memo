package com.ezone.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	// 암호화 메소드
	public static String md5(String message) { // md5 : 암호화의 알고리즘에 쓰이는 것
	// static 있으면 객체 생성없이 쓸 수 있는 메소드가 됨	
		String resultData = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			// 문자를 하나하나 쪼개서 암호화 하기 때문에 문자를 byte로 바꿔줌
			byte[] bytes = message.getBytes();
			// 암호화 셋팅
			md.update(bytes);
			// 암호화된 결과 얻기
			byte[] digest = md.digest();
			
			// digest는 문자열이 아니기 때문에 다시 16진수의 문자열 형태로 변환시켜야함
			for(int i = 0; i < digest.length; i++) {
				resultData += Integer.toHexString(digest[i] & 0xff);
				
				//   01001001   : 비트 연산자 둘다 1이면 1 하나라도 0이면 0
				// & 11111111
				//   01001001
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
		}
		
		return resultData; // 암호화가 완성된 문자열이 리턴됨
	}
}
