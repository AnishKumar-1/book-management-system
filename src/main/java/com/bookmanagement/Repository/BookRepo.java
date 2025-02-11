package com.bookmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmanagement.Model.Books;

public interface BookRepo extends JpaRepository<Books, Long> {

}
