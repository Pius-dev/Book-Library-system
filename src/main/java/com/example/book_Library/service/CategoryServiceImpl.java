package com.example.book_Library.service;

import com.example.book_Library.dto.mapper;
import com.example.book_Library.dto.requestDto.CategoryRequestDto;
import com.example.book_Library.dto.responseDto.CategoryResponseDto;
import com.example.book_Library.model.Book;
import com.example.book_Library.model.Category;
import com.example.book_Library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalArgumentException("Cannot find category with id:" + categoryId));
        return category;

    }

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
       Category category = new Category();
       category.setName(categoryRequestDto.getName());
       categoryRepository.save(category);
       return mapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto addCategoryById(Long categoryId) {
        Category category = getCategory(categoryId);
        return mapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        List<Category> categories = StreamSupport
                .stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.categoriesToCategoryResponseDtos(categories);
    }

    @Override
    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category categoryToEdit = getCategory(categoryId);
        categoryToEdit.setName(categoryToEdit.getName());

        return mapper.categoryToCategoryResponseDto(categoryToEdit);
    }

    @Override
    public CategoryResponseDto deleteCategory(Long categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return mapper.categoryToCategoryResponseDto(category);
    }

}
