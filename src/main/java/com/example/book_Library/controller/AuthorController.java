package com.example.book_Library.controller;


import com.example.book_Library.dto.requestDto.AuthorRequestDto;
import com.example.book_Library.dto.responseDto.AuthorResponseDto;
import com.example.book_Library.model.Author;
import com.example.book_Library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    
    private final AuthorService authorService;
    
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping("/add")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody final AuthorRequestDto authorRequestDto) {
        AuthorResponseDto authorResponseDto = authorService.addAuthor(authorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable final Long id) {
        AuthorResponseDto authorResponseDto = authorService.getAuthorById(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AuthorResponseDto>> getAuthors() {
        List<AuthorResponseDto> authorResponseDtos = authorService.getAuthors();
        return new ResponseEntity<>(authorResponseDtos, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AuthorResponseDto> deleteAuthor(@PathVariable final Long id) {
        AuthorResponseDto authorResponseDto = authorService.deleteAuthor(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);

    }

    @PutMapping ("/edit/{id}")
    public ResponseEntity<AuthorResponseDto> editAuthor(@RequestBody final AuthorRequestDto AuthorRequestDto,
                                         @PathVariable final Long id) {
        AuthorResponseDto authorResponseDto = authorService.editAuthor(id, AuthorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);

    }

    @PostMapping("/addZipcode/{zipcodeId}/toAuthor/{authorId}")
    public ResponseEntity<AuthorResponseDto> addZipcode(@PathVariable final Long zipcodeId,
                                                        @PathVariable final Long authorId) {
        AuthorResponseDto authorResponseDto = authorService.addZipcodeToAuthor(zipcodeId, authorId);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("/deleteZipcode/{id}")
    public ResponseEntity<AuthorResponseDto> removeZipcodeFromAuthor(@PathVariable final Long id) {
        AuthorResponseDto authorResponseDto = authorService.deleteZipcodeFromAuthor(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);

    }

}

