package com.ezone.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezone.memo.user.bo.UserBO;
import com.ezone.memo.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/sign_up")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		int count = userBO.addUser(loginId, password, name, email);
		
		Map<String, String> result = new HashMap<>();
		
		if (count == 1) {
			result.put("result", "success");
		}
		else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		
		User user = userBO.getUser(loginId, password);
		Map<String,String> result = new HashMap<>();
		
		if(user != null) {
			//성공
			result.put("result", "success");
			
			HttpSession session = request.getSession();
			
			// 로그인여부 저장 - id, loginId, name, 
			session.setAttribute("userId", user.getId());  //(key(저장한 값의 이름), 저장할 값)
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
		}
		else {
			// 실패
			result.put("result", "fail");
		}
		
		return result;
	}
}
