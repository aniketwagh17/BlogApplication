package com.blog.application.payloads;

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
public class CommentDto {

	private Integer commentId;

	private String content;

	private String commentDate;

	private UserDto user;

}
