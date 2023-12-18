package com.blog.application.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
@AllArgsConstructor
public class PostDto {

	private Integer postId;

	private String title;

	private String content;

	private String category;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date addedDate;

	private String imageName;

	private UserDto user;

	private Set<CommentDto> comments = new HashSet<>();

}
