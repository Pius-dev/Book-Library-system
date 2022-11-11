package com.example.book_Library.controller;


import com.example.book_Library.dto.requestDto.CategoryRequestDto;
import com.example.book_Library.dto.requestDto.ZipcodeRequestDto;
import com.example.book_Library.dto.responseDto.CategoryResponseDto;
import com.example.book_Library.model.Zipcode;
import com.example.book_Library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> addZipcode(@RequestBody final CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.addCategory(categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable final Long id) {
        CategoryResponseDto categoryResponseDto = categoryService.addCategoryById(id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);

    }

    @GetMapping("/getall")
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getCategories();
        return new ResponseEntity<List<CategoryResponseDto>>(categoryResponseDtos, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(@PathVariable final Long id) {
        CategoryResponseDto categoryResponseDto = categoryService.deleteCategory(id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);

    }

    @PutMapping ("/edit/{id}")
    public ResponseEntity<CategoryResponseDto> editZipcode(@RequestBody final CategoryRequestDto categoryRequestDto,
                                               @PathVariable final Long id) {
        CategoryResponseDto categoryResponseDto = categoryService.editCategory(id, categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);

    }



}
