package com.te.blogmanagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PostCommentDto {
	private String postId;
	private String parentId;
	private String title;
	private String published;
	private LocalDate createdAt;
	private LocalDate publishedAt;
	private String content;

	private PostDto postDto;
	
	

}
