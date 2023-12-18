package com.blog.application.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.application.payloads.ApiResponse;
import com.blog.application.payloads.PostDto;
import com.blog.application.service.FileService;
import com.blog.application.service.PostService;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	/**
	 * 
	 * @param postDto
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	@PostMapping("/createPost/{userId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId) {
		PostDto createPost = this.postService.createPost(postDto, userId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/getAllPost")
	public ResponseEntity<List<PostDto>> getAllPost() {

		List<PostDto> postDto = this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(postDto, HttpStatus.OK);
	}

	/**
	 * 
	 * @param postId
	 * @return
	 */
	@GetMapping("/getPostById/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {

		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}

	/**
	 * 
	 * @param postId
	 * @return
	 */
	@DeleteMapping("/deletePostById/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully deleted !!!", true);
	}

	/**
	 * 
	 * @param postDto
	 * @param postId
	 * @return
	 */
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	/**
	 * 
	 * @param image
	 * @return
	 * @throws IOException
	 */

	@PostMapping("/post/image/upload")
	public ResponseEntity<ApiResponse> uploadPostImage(@RequestParam("file") MultipartFile image) throws IOException {
		String fileName = null;
		try {
			fileName = this.fileService.uploadImage(path, image);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("File is uploaded with name " + fileName + " successfully", true), HttpStatus.OK);

	}

	/**
	 * 
	 * @param imageName
	 * @param response
	 * @throws IOException
	 */

	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

	/**
	 * 
	 * @param userId
	 * @return
	 */

	@GetMapping("/getPost/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {

		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

}
