package com.gundemgaming.fukantin.service;

import com.gundemgaming.fukantin.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    List<CategoryDto> getAllCategories();
    CategoryDto getCategory(Long categoryId);
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    void deleteCategory(Long categoryId);

}
