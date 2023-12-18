package com.blog.application.service;

import java.util.List;

import com.blog.application.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId);

	void deletePost(Integer postId);

	PostDto getPostById(Integer postId);

	List<PostDto> getAllPost();

	PostDto updatePost(PostDto postDto, Integer postId);

	List<PostDto> getPostsByUser(Integer userId);

}
