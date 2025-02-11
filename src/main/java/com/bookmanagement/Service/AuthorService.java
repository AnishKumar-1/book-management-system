package com.bookmanagement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmanagement.CustomException.CustomResourceNotFound;
import com.bookmanagement.Dto.AuthorDto;
import com.bookmanagement.Dto.BookDto;
import com.bookmanagement.Model.Author;
import com.bookmanagement.Repository.AuthorRepo;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepo authRepo;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<Object> createAuth(AuthorDto author) {

		Optional<Author> auth = authRepo.findByName(author.getName());

		if (auth.isPresent()) {
			throw new CustomResourceNotFound("Author already exists");
		}

		Author a = modelMapper.map(author, Author.class);
		Author result = authRepo.save(a);
		AuthorDto response = modelMapper.map(result, AuthorDto.class);
		if (response.getBooks() != null) {
			List<BookDto> bookDto = result.getBooks().stream().map(books -> modelMapper.map(books, BookDto.class))
					.collect(Collectors.toList());
			response.setBooks(bookDto);
		} else {
			response.setBooks(new ArrayList<>());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// find author by its id
	public ResponseEntity<Object> authById(Long author_id) {
		Optional<Author> author = authRepo.findById(author_id);
		if (!author.isPresent()) {
			throw new CustomResourceNotFound("Author not found with the id: " + author_id);
		}
		AuthorDto authDto = new AuthorDto();
		authDto.setId(author.get().getId());
		authDto.setName(author.get().getName());
		List<BookDto> bookDtoList = author.get().getBooks().stream().map(book -> {
			BookDto dto = new BookDto();
			dto.setId(book.getId());
			dto.setName(book.getName());
			return dto;
		}).collect(Collectors.toList());

		authDto.setBooks(bookDtoList);

		return ResponseEntity.ok(authDto);
	}

	// get all authors
	public ResponseEntity<Object> allAuthor() {
		List<Author> author = authRepo.findAll();
		List<AuthorDto> authDto = author.stream().map(listOfListObject -> {
			AuthorDto auth = new AuthorDto();
			auth.setId(listOfListObject.getId());
			auth.setName(listOfListObject.getName());
			List<BookDto> bookDto = listOfListObject.getBooks().stream().map(books -> {
				BookDto bookdt = new BookDto();
				bookdt.setId(books.getId());
				bookdt.setName(books.getName());
				return bookdt;
			}).collect(Collectors.toList());

			auth.setBooks(bookDto);
			return auth;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(authDto);

	}

}
