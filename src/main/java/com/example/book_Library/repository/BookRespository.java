package com.example.book_Library.repository;

import com.example.book_Library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRespository extends CrudRepository<Book, Long> {
}
