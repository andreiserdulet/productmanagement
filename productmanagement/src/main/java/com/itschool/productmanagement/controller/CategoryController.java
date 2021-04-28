package com.itschool.productmanagement.controller;

import com.itschool.productmanagement.entities.CategoryModel;
import com.itschool.productmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "category")
    public String displayCategories(Model model) {
        List<CategoryModel> categoryModelList = categoryService.displayCategories();
        model.addAttribute("categories", categoryModelList);
        return "categories";
    }

    @GetMapping(path = "add-category")
    public String viewCategoryPage(Model model) {
        model.addAttribute("newCategory", new CategoryModel());
        return "category-add";
    }

    @GetMapping(path = "category-add")
    public String addCategory(@ModelAttribute CategoryModel newCategory) {
        System.out.println("Add category ->" + newCategory.getId() + " " + newCategory.getCategoryName());
        categoryService.addCategory(newCategory);
        return "redirect:/category";
    }

    @GetMapping(path = "edit-category")
    public String viewCategoryPage(@RequestParam int id, Model model) {
        CategoryModel foundCategory = categoryService.findByIdCategory(id);
        model.addAttribute("editCategory", foundCategory);
        return "category-edit";
    }

    @GetMapping(path = "category-edit")
    public String editCategory(@ModelAttribute CategoryModel editedCategory) {
        categoryService.edit(editedCategory);
        return "redirect:/category";
    }

    @GetMapping(path = "deleteByIdC")
    public String deleteByIdCategory(@RequestParam("id") int id) {
        categoryService.deleteByIdCategory(id);
        return "redirect:/category";

    }

    @GetMapping(path = "findByIdC")
    public String findByIdCategory(@RequestParam("id") int id, Model model) {
        CategoryModel categoryModel = categoryService.findByIdCategory(id);
        model.addAttribute("foundCategory", categoryModel);
        return "view-category";
    }
}
