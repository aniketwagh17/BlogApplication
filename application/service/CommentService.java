package com.blog.application.service;

import java.util.List;

import com.blog.application.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postIdm);

	void deleteComment(Integer commentId);

	CommentDto getCommentById(Integer commentId);

	List<CommentDto> getAllComment();

	List<CommentDto> getCommentByPostId(Integer postId);

}
