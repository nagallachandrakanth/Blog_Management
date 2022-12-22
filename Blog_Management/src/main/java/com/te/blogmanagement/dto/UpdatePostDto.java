package com.te.blogmanagement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class UpdatePostDto {
	private Integer authorId;
	private Integer parentId;
	private String title;
	private String metaTitle;
	private String slug;
	private String summary;
	private String published;
	private LocalDateTime updatedAt;
	private LocalDate publishedAt;
	private String content;

}
