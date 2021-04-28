package com.itschool.productmanagement.service;

import com.itschool.productmanagement.entities.CategoryModel;
import com.itschool.productmanagement.entities.ProductModel;
import com.itschool.productmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> displayCategories() {
        List<CategoryModel> categories = categoryRepository.findAll();
        return categories;
    }

    public void addCategory(CategoryModel categoryModel) {
        categoryRepository.save(categoryModel);

    }

    public void deleteByIdCategory(int idCategory) {
        categoryRepository.deleteById(idCategory);
    }

    public CategoryModel findByIdCategory(int idCategory) {
        Optional<CategoryModel> optionalCategoryModel = categoryRepository.findById(idCategory);
        return optionalCategoryModel.get();
    }
}
