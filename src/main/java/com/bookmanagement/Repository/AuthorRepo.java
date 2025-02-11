package com.bookmanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmanagement.Model.Author;

public interface AuthorRepo extends JpaRepository<Author,Long>{
	public Optional<Author> findByName(String name);

}
