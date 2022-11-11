package com.example.book_Library.service;

import com.example.book_Library.dto.mapper;
import com.example.book_Library.dto.requestDto.BookRequestDto;
import com.example.book_Library.dto.responseDto.BookResponseDto;
import com.example.book_Library.model.Author;
import com.example.book_Library.model.Book;
import com.example.book_Library.model.Category;
import com.example.book_Library.model.Zipcode;
import com.example.book_Library.repository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {
    private final BookRespository bookRespository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRespository bookRespository, AuthorService authorService, CategoryService categoryService) {
        this.bookRespository = bookRespository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setName(bookRequestDto.getName());
        if (bookRequestDto.getAuthorId().isEmpty()) {
            throw new IllegalArgumentException("You need at least one author");
        } else {
            List<Author> authors = new ArrayList<>();
            for (Long authorId : bookRequestDto.getAuthorId()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);

        }
        if (bookRequestDto.getCategoryId() == null) {
            throw new IllegalArgumentException("Book needs at least one Category");
        }
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);

        Book book1 = bookRespository.save(book);
        return mapper.bookToBookResponseDto(book1);

    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        Book book = getBook(bookId);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public Book getBook(Long bookId) {
        Book book = bookRespository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("Cannot find book with id:" + bookId));
        return book;
    }

    @Override
    public List<BookResponseDto> getBooks() {
        List<Book> books = StreamSupport
                .stream(bookRespository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.bookToBookResponseDtos(books);
    }

    @Override
    public BookResponseDto deleteBook(Long bookId) {
        Book book = getBook(bookId);
        bookRespository.delete(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto) {
        Book bookToEdit = getBook(bookId);
        bookToEdit.setName(bookRequestDto.getName());
        if (!bookRequestDto.getAuthorId().isEmpty()) {
            List<Author> authors = new ArrayList<>();

            for (Long authorId : bookRequestDto.getAuthorId()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            bookToEdit.setAuthors(authors);
        }
        if (bookRequestDto.getCategoryId() != null) {
            Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
            bookToEdit.setCategory(category);

        }
        return mapper.bookToBookResponseDto(bookToEdit);

    }


    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);

        if (author.getBooks().contains(author)) {
            throw new IllegalArgumentException("This author is already assigned to this book");
        }
        book.addAuthor(author);
        author.addBook(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);

        if (!(author.getBooks().contains(book))) {
            throw new IllegalArgumentException("Book does not have this author");
        }
        book.deleteAuthor(author);
        author.removeBook(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (Objects.nonNull(book.getCategory())) {
            throw new IllegalArgumentException("book already has category");
        }
        book.setCategory(category);
        category.addBook(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (!(Objects.nonNull(book.getCategory()))) {
            throw new IllegalArgumentException("book does not have a  category to delete");
        }
        book.setCategory(null);
        category.addBook(book);

        return mapper.bookToBookResponseDto(book);
    }
}
