package com.blog.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entity.Comment;
import com.blog.application.entity.Post;
import com.blog.application.entity.User;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	List<Comment> findByPost(Post post);

}
