package com.ezone.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezone.memo.common.EncryptUtils;
import com.ezone.memo.user.dao.UserDAO;
import com.ezone.memo.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	public int addUser(
			String loginId, 
			String password,
			String name,
			String email) {
		
		// password 암호화 작업   - BO의 역할
		
		String encryptPassword = EncryptUtils.md5(password);
		
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
	
	public User getUser(String loginId, String password) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		return userDAO.selectUser(loginId, encryptPassword);
		
	}
}
