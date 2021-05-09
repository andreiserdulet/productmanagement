package com.itschool.productmanagement.controller;

import com.itschool.productmanagement.entities.CategoryModel;
import com.itschool.productmanagement.exception.NameException;
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
    public String addCategory(@ModelAttribute CategoryModel newCategory, Model model) {
        System.out.println("Add category ->" + newCategory.getId() + " " + newCategory.getCategoryName());

        try {
            categoryService.addCategory(newCategory);
            return "redirect:/category";
        } catch (NameException nameException) {
            model.addAttribute("newCategory", newCategory);
            model.addAttribute("errorMessageCategory", nameException.getMessage());
            return "product-add";
        }
    }

    @GetMapping(path = "edit-category")
    public String viewCategoryPage(@RequestParam int id, Model model) {
        CategoryModel foundCategory = categoryService.findByIdCategory(id);
        model.addAttribute("editCategory", foundCategory);
        return "category-edit";
    }

    @GetMapping(path = "category-edit")
    public String editCategory(@ModelAttribute CategoryModel editedCategory, Model model) {
        try {
            categoryService.edit(editedCategory);
            return "redirect:/category";
        } catch (NameException nameException) {
            model.addAttribute("editCategory", editedCategory);
            model.addAttribute("errorMessageCategory", nameException.getMessage());
            return "category-edit";
        }
    }

    @GetMapping(path = "deleteByIdCategory")
    public String deleteByIdCategory(@RequestParam("id") int id) {
        categoryService.deleteByIdCategory(id);
        return "redirect:/category";

    }

    @GetMapping(path = "findByIdCategory")
    public String findByIdCategory(@RequestParam("id") int id, Model model) {
        CategoryModel categoryModel = categoryService.findByIdCategory(id);
        model.addAttribute("foundCategory", categoryModel);
        return "view-category";
    }
}
