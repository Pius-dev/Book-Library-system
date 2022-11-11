package com.example.book_Library.controller;


import com.example.book_Library.dto.requestDto.BookRequestDto;
import com.example.book_Library.dto.responseDto.BookResponseDto;
import com.example.book_Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/add")
    public ResponseEntity<BookResponseDto> addAuthor(@RequestBody final BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.addBook(bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable final Long id) {
        BookResponseDto bookResponseDto = bookService.getBookById(id);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<BookResponseDto>> getAuthors() {
        List<BookResponseDto> bookResponseDto = bookService.getBooks();
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookResponseDto> deleteAuthor(@PathVariable final Long id) {
        BookResponseDto bookResponseDto = bookService.deleteBook(id);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);

    }

    @PutMapping ("/edit/{id}")
    public ResponseEntity<BookResponseDto> editAuthor(@RequestBody final BookRequestDto bookRequestDto,
                                                        @PathVariable final Long id) {
        BookResponseDto bookResponseDto = bookService.editBook(id, bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);

    }

    @PostMapping("/addCategory/{categoryId}/toBook/{bookId}")
    public ResponseEntity<BookResponseDto> addCategory(@PathVariable final Long categoryId,
                                                       @PathVariable final Long bookId) {
        BookResponseDto bookResponseDto = bookService.addCategoryToBook(categoryId, bookId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);

    }


    @DeleteMapping("/removeCategory/{categoryId}/fromBook/{booId}")
    public ResponseEntity<BookResponseDto> removeZipcodeFromAuthor(@PathVariable final Long categoryId,
                                                                   @PathVariable final Long bookId) {
        BookResponseDto bookResponseDto = bookService.removeCategoryFromBook(categoryId, bookId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);

    }

    @PostMapping("/addAuthor/{authorId}/toBook/{bookId}")
    public ResponseEntity<BookResponseDto> addAuthor(@PathVariable final Long authorId,
                                                       @PathVariable final Long bookId) {
        BookResponseDto bookResponseDto = bookService.addAuthorToBook(authorId, bookId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("/removeAuthor/{authorId}/fromBook/{booId}")
    public ResponseEntity<BookResponseDto> removeAuthorFromAuthor(@PathVariable final Long authorId,
                                                                   @PathVariable final Long bookId) {
        BookResponseDto bookResponseDto = bookService.deleteAuthorFromBook(authorId, bookId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);

    }

}