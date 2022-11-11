package com.example.book_Library.service;

import com.example.book_Library.dto.requestDto.AuthorRequestDto;
import com.example.book_Library.dto.requestDto.BookRequestDto;
import com.example.book_Library.dto.responseDto.AuthorResponseDto;
import com.example.book_Library.dto.responseDto.BookResponseDto;
import com.example.book_Library.model.Author;
import com.example.book_Library.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public BookResponseDto addBook(BookRequestDto bookRequestDto);
    public BookResponseDto getBookById(Long bookId);
    public Book getBook(Long bookId);
    public List<BookResponseDto> getBooks();
    public BookResponseDto deleteBook(Long bookId);
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto);
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId);
    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId);
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId);
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId);
}
