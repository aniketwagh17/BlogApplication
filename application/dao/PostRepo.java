package com.blog.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.application.entity.Post;
import com.blog.application.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

}
