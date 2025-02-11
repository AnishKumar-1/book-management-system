package com.bookmanagement.Dto;

import java.util.List;

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
public class AuthorDto {

	private Long id;
	@NotBlank(message="author name not found")
	private String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<BookDto> books;

}
