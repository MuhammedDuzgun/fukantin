package com.gundemgaming.fukantin.service.impl;

import com.gundemgaming.fukantin.dto.CategoryDto;
import com.gundemgaming.fukantin.exception.ResourceNotFoundException;
import com.gundemgaming.fukantin.model.Category;
import com.gundemgaming.fukantin.repository.ICategoryRepository;
import com.gundemgaming.fukantin.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    private ICategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category createdCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(createdCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        //check is category exists
        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryToUpdate.setCategory(categoryDto.getCategory());

        categoryRepository.save(categoryToUpdate);

        return modelMapper.map(categoryToUpdate, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        //check is category exists
        Category categoryToDelete = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(categoryToDelete);
    }
}
