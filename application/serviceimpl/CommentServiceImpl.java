package com.blog.application.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.application.dao.CommentRepo;
import com.blog.application.dao.PostRepo;
import com.blog.application.dao.UserRepo;
import com.blog.application.entity.Comment;
import com.blog.application.entity.Post;
import com.blog.application.entity.User;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.CommentDto;
import com.blog.application.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id ", postId));

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);
		comment.setUser(user);
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = myDateObj.format(myFormatObj);
		comment.setCommentDate(formattedDate);
		Comment savedComment = this.commentRepo.save(comment);

		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentRepo.delete(com);
	}

	@Override
	public CommentDto getCommentById(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));

		return this.modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllComment() {
		List<Comment> comments = this.commentRepo.findAll();

		List<CommentDto> comm = comments.stream().map((cat) -> this.modelMapper.map(cat, CommentDto.class))
				.collect(Collectors.toList());
		return comm;

	}

	@Override
	public List<CommentDto> getCommentByPostId(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));

		List<Comment> comment = this.commentRepo.findByPost(post);
		List<CommentDto> commentDto = comment.stream().map((comm) -> this.modelMapper.map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return commentDto;
	}

}
