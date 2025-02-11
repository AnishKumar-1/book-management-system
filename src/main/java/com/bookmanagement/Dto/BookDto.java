package com.bookmanagement.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	
	private Long id;
	@NotBlank(message="book name not found")
	private String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private SimpleAuthorDto author;

}
