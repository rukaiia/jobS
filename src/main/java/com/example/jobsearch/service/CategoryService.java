package com.example.jobsearch.service;

import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.model.Category;
import lombok.SneakyThrows;

import java.util.List;

public interface CategoryService {
    @SneakyThrows
    CategoryDto getCategoryById(Integer id);

    String getParentCategory(Object entry);

    List<Category> getAllCategories();

    Integer checkInCategories(int categoryId);

    Integer checkInCategories(String category);

    Integer getCategoryByName(String category);
}
