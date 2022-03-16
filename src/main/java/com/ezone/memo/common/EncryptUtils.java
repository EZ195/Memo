package com.ezone.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	// ��ȣȭ �޼ҵ�
	public static String md5(String message) { // md5 : ��ȣȭ�� �˰��� ���̴� ��
	// static ������ ��ü �������� �� �� �ִ� �޼ҵ尡 ��	
		String resultData = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			// ���ڸ� �ϳ��ϳ� �ɰ��� ��ȣȭ �ϱ� ������ ���ڸ� byte�� �ٲ���
			byte[] bytes = message.getBytes();
			// ��ȣȭ ����
			md.update(bytes);
			// ��ȣȭ�� ��� ���
			byte[] digest = md.digest();
			
			// digest�� ���ڿ��� �ƴϱ� ������ �ٽ� 16������ ���ڿ� ���·� ��ȯ���Ѿ���
			for(int i = 0; i < digest.length; i++) {
				resultData += Integer.toHexString(digest[i] & 0xff);
				
				//   01001001   : ��Ʈ ������ �Ѵ� 1�̸� 1 �ϳ��� 0�̸� 0
				// & 11111111
				//   01001001
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
		}
		
		return resultData; // ��ȣȭ�� �ϼ��� ���ڿ��� ���ϵ�
	}
}
