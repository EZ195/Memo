package com.ezone.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ezone.memo.post.model.Post;

@Repository
public interface PostDAO {
	
	public int insertPost(
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("filePath") String filePath);
	
	public List<Post> selectList(@Param("userId") int userId);
	
	public Post selectPost(@Param("id") int id);
	
	public int updatePost(
			@Param("postId") int postId,
			@Param("subject") String subject,
			@Param("content") String content);
	
	public int deletePost(
			@Param("postId") int postId,
			@Param("userId") int userId);
}

	