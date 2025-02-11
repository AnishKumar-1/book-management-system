package com.bookmanagement.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmanagement.CustomException.CustomResourceNotFound;
import com.bookmanagement.Dto.BookDto;
import com.bookmanagement.Dto.SimpleAuthorDto;
import com.bookmanagement.Model.Author;
import com.bookmanagement.Model.Books;
import com.bookmanagement.Repository.AuthorRepo;
import com.bookmanagement.Repository.BookRepo;

@Service
public class BookServicre {

	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private AuthorRepo authorRepo;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<Object> createBook(Long author_id, BookDto book) {
		Optional<Author> author = authorRepo.findById(author_id);
		if (!author.isPresent()) {
			throw new CustomResourceNotFound("Author not found with the id: " + author_id);
		}
		Books books = new Books();
		books.setName(book.getName());
		books.setAuthor(author.get());
		Books result = bookRepo.save(books);
		BookDto dto = modelMapper.map(result, BookDto.class);
		dto.setAuthor(modelMapper.map(result.getAuthor(), SimpleAuthorDto.class));
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);

	}

	// book by id
	public ResponseEntity<Object> bookById(Long book_id) {
		Optional<Books> book = bookRepo.findById(book_id);
		if (!book.isPresent()) {
			throw new CustomResourceNotFound("Book not found with the id: " + book_id);
		}
		BookDto bookDt = new BookDto();
		SimpleAuthorDto simpleAuthoDto = modelMapper.map(book.get().getAuthor(), SimpleAuthorDto.class);
		bookDt.setId(book.get().getId());
		bookDt.setName(book.get().getName());
		bookDt.setAuthor(simpleAuthoDto);
		return ResponseEntity.ok(bookDt);
	}
	
	//get all books
	public ResponseEntity<Object> getAllBooks(){
		List<Books> books=bookRepo.findAll();
		List<BookDto> bookDto=books.stream().map(booksResult->{
			SimpleAuthorDto simpleAutho=new SimpleAuthorDto();
			simpleAutho.setId(booksResult.getAuthor().getId());
			simpleAutho.setName(booksResult.getAuthor().getName());
			BookDto bookDt=new BookDto();
			bookDt.setId(booksResult.getId());
			bookDt.setName(booksResult.getName());
			bookDt.setAuthor(simpleAutho);
			return bookDt;
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok(bookDto);
	}

}
