package com.example.book_Library.service;

import com.example.book_Library.dto.requestDto.AuthorRequestDto;
import com.example.book_Library.dto.requestDto.ZipcodeRequestDto;
import com.example.book_Library.dto.responseDto.AuthorResponseDto;
import com.example.book_Library.model.Author;
import com.example.book_Library.model.Zipcode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);
    public List<AuthorResponseDto > getAuthors();
    public AuthorResponseDto getAuthorById(Long authorId);
    public Author getAuthor(Long authorId);
    public AuthorResponseDto deleteAuthor(Long authorId);
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto);
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId);
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId);
}
