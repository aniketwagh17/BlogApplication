package com.blog.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.application.payloads.ApiResponse;
import com.blog.application.payloads.CommentDto;
import com.blog.application.service.CommentService;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * 
	 * @param comment
	 * @param postId
	 * @return
	 */
	@PostMapping("/createComment/{userId}/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, 
			@PathVariable Integer userId, @PathVariable Integer postId) {

		CommentDto createComment = this.commentService.createComment(comment, userId, postId);
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param commentId
	 * @return
	 */

	@DeleteMapping("/deleteComment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {

		this.commentService.deleteComment(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!", true), HttpStatus.OK);
	}

	/**
	 * 
	 * @param commentId
	 * @return
	 */
	@GetMapping("/getCommnetById/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer commentId) {
		CommentDto comment = this.commentService.getCommentById(commentId);
		return new ResponseEntity<CommentDto>(comment, HttpStatus.OK);

	}

	/**
	 * 
	 * @return
	 */

	@GetMapping("/getAllComments")
	public ResponseEntity<List<CommentDto>> getAllComments() {
		List<CommentDto> list = this.commentService.getAllComment();
		return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/getCommnetByPostId/{postId}")
	public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable Integer postId) {
		List<CommentDto> comment = this.commentService.getCommentByPostId(postId);
		return new ResponseEntity<List<CommentDto>>(comment, HttpStatus.OK);

	}

}
