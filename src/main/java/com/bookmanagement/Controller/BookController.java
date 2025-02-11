package com.bookmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.Dto.BookDto;
import com.bookmanagement.Service.BookServicre;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookServicre bookService;
	
	//create book
	@PostMapping("/book/{author_id}")
	public ResponseEntity<Object> createbook(@PathVariable Long author_id ,@Valid @RequestBody BookDto book){
		return bookService.createBook(author_id, book);
	}
	
	//get book by id
	@GetMapping("/book/{book_id}")
	public ResponseEntity<Object> getBookById(@PathVariable Long book_id){
		return bookService.bookById(book_id);
	}
	
	//get all books
	@GetMapping("/book")
	public ResponseEntity<Object> getAllBooks(){
		return bookService.getAllBooks();
	}
}
