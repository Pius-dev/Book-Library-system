package com.example.book_Library.service;

import com.example.book_Library.dto.requestDto.CategoryRequestDto;
import com.example.book_Library.dto.responseDto.CategoryResponseDto;
import com.example.book_Library.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public Category getCategory(Long categoryId);
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    public  CategoryResponseDto addCategoryById(Long categoryId);
    public List<CategoryResponseDto> getCategories();
    public  CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto);
    public  CategoryResponseDto deleteCategory(Long categoryId);
}
