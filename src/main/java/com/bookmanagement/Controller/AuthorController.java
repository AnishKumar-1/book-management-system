package com.bookmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.Dto.AuthorDto;
import com.bookmanagement.Service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthorController {

	@Autowired
	private AuthorService authService;
	
	@PostMapping("/author")
	public ResponseEntity<Object> createAuthor(@Valid @RequestBody AuthorDto author){
		return authService.createAuth(author);
	}
	
	//get author by its id
	@GetMapping("/author/{author_id}")
	public ResponseEntity<Object> authorById(@PathVariable Long author_id){
		return authService.authById(author_id);
	}
	
	//get all books
	@GetMapping("/author")
	public ResponseEntity<Object> getAllAuthor(){
		return authService.allAuthor();
	}
}
