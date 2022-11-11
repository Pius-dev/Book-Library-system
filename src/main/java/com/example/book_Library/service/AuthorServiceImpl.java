package com.example.book_Library.service;

import com.example.book_Library.dto.mapper;
import com.example.book_Library.dto.requestDto.AuthorRequestDto;
import com.example.book_Library.dto.responseDto.AuthorResponseDto;
import com.example.book_Library.model.Author;
import com.example.book_Library.model.Zipcode;
import com.example.book_Library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ZipcodeService zipcodeService;
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipcodeService zipcodeService) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;
    }
    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        if (authorRequestDto.getZipcodeId() == null){

            throw new IllegalArgumentException("Author needs a zipcode");

        }
        Zipcode zipcode = zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
        author.setZipcode(zipcode);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }
    @Transactional
    @Override
    public List<AuthorResponseDto> getAuthors() {
       List<Author> authors = StreamSupport
               .stream(authorRepository.findAll().spliterator(), false)
               .collect(Collectors.toList());
       return mapper.authorsToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {
        return mapper.authorToAuthorResponseDto(getAuthor(authorId));
    }

    @Override
    public Author getAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() ->
                new IllegalArgumentException(
                        "author with id" + authorId + "not found"));
        return author;
    }

    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {
       Author author = getAuthor(authorId);
       authorRepository.delete(author);
       return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        Author authorToEdit = getAuthor(authorId);
        authorToEdit.setName(authorRequestDto.getName());
        if (authorRequestDto.getZipcodeId() != null){

            Zipcode zipcode = zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
            authorToEdit.setZipcode(zipcode);

        }
        return mapper.authorToAuthorResponseDto(authorToEdit);
    }

    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId) {
        Author author = getAuthor(authorId);
        Zipcode zipcode = zipcodeService.getZipcode(zipcodeId);

        if (Objects.nonNull(author.getZipcode())) {
            throw new RuntimeException("Author already has zipcode");
        }
        author.setZipcode(zipcode);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipcode(null);
        return mapper.authorToAuthorResponseDto(author);
    }
}
