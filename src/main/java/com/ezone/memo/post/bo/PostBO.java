package com.ezone.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezone.memo.common.FileManagerService;
import com.ezone.memo.post.dao.PostDAO;
import com.ezone.memo.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String subject, String content, MultipartFile file) {
		
		// 파일을 저장하고, 경로를 만들어 낸다.
		String filePath = FileManagerService.saveFile(userId, file);

		return postDAO.insertPost(userId, subject, content, filePath);
		
	}
	
	public List<Post> getPostList(int userId){
		return postDAO.selectList(userId);
	}
	
	public Post getPost(int id) {
		
		return postDAO.selectPost(id);
	}
	
	public int updatePost(int postId, String subject, String content) {
		return postDAO.updatePost(postId, subject, content);
	}
	
	public int deletePost(int postId , int userId) {
		
		Post post = this.getPost(postId);
		// 파일 삭제 
		
		FileManagerService.removeFile(post.getImagePath());
		
		return postDAO.deletePost(postId,userId);
	}

}
