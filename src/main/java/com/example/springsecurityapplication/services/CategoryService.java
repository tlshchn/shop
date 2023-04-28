package com.example.springsecurityapplication.services;


import com.example.springsecurityapplication.models.Category;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void addDefaultCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            Category defaultCategory1 = new Category();
            defaultCategory1.setName("Мебель");
            Category defaultCategory2 = new Category();
            defaultCategory2.setName("Обувь");
            categoryRepository.save(defaultCategory1);
            categoryRepository.save(defaultCategory2);
        }
    }
}
