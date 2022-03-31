package com.ezone.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ezone.memo.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value="file", required = false) MultipartFile file,
			HttpServletRequest request) { // session에 저장된 userID 가져오는 법
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = postBO.addPost(userId, subject, content, file);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		}
		else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@PostMapping("/update")
	public Map<String,String> update(
			@RequestParam("postId") int postId,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content) {
		
		int count = postBO.updatePost(postId, subject, content);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result" , "success");
		}
		else {
			result.put("result" , "fail");
		}
		
		return result;
	}
	
	@GetMapping("/delete")
	public Map<String, String> delete(
			@RequestParam("postId") int postId,
			HttpServletRequest request){
		
		// userId 추가하는 이유 - url에서 임의로 삭제하는 것 방지하기 위해 userId도 추가
		HttpSession session = request.getSession();
		int userId = (Integer)session .getAttribute("userId");
		
		
		// 저장된 파일도 삭제해야함 - BO에서 해결
		int count = postBO.deletePost(postId,userId);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		}
		else {
			result.put("result", "fail");
		}
		
		return result;
 	}

}
