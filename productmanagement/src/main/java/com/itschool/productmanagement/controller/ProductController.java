package com.itschool.productmanagement.controller;

import com.itschool.productmanagement.entities.CategoryModel;
import com.itschool.productmanagement.entities.ProductModel;
import com.itschool.productmanagement.exception.CurrencyException;
import com.itschool.productmanagement.exception.DescriptionException;
import com.itschool.productmanagement.exception.NameException;
import com.itschool.productmanagement.exception.PriceException;
import com.itschool.productmanagement.service.CategoryService;
import com.itschool.productmanagement.service.CurrencyConvertorService;
import com.itschool.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CurrencyConvertorService currencyConvertorService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "product")
    public String displayProducts(Model model) {
        List<ProductModel> productModelList = productService.displayProduct();
        model.addAttribute("title", "Afisare Produse");
        model.addAttribute("products", productModelList);
        return "products";
    }

    @GetMapping(path = "add-product")
    public String viewProductPage(Model model) {
        model.addAttribute("newProduct", new ProductModel());
        List<CategoryModel> categories = categoryService.displayCategories();
        model.addAttribute("categories", categories);

        return "product-add";
    }

    @GetMapping(path = "product-add")
    private String addProduct(@ModelAttribute ProductModel newProduct, Model model) {
        System.out.println("Add Product ->" + newProduct.getProductName() + " " + newProduct.getProductDescription()
                + " " + newProduct.getPrice() + " " + newProduct.getCurrency());
        try {
            productService.addAProduct(newProduct);

            return "redirect:/product";
        } catch (NameException nameException) {
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("errorMessage", nameException.getMessage());
            return "product-add";
        } catch (PriceException priceException) {
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("errorMessagePrice", priceException.getMessage());
            return "product-add";
        } catch (DescriptionException descriptionException) {
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("errorMessageDescription", descriptionException.getMessage());
            return "product-add";
        } catch (CurrencyException currencyException) {
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("errorMessageCurrency", currencyException.getMessage());
            return "product-add";
        }
    }

    @GetMapping(path = "edit-product")
    private String viewProductPage(@RequestParam int id, Model model) {
        ProductModel foundProduct = productService.findById(id);
        model.addAttribute("editProduct", foundProduct);
        return "product-edit";


    }

    @GetMapping(path = "product-edit")
    public String editProduct(@ModelAttribute ProductModel editedProduct, Model model) {

        try {
            productService.addAProduct(editedProduct);

            return "redirect:/product";
        } catch (NameException nameException) {
            model.addAttribute("editProduct", editedProduct);
            model.addAttribute("errorMessage", nameException.getMessage());
            return "product-edit";
        } catch (PriceException priceException) {
            model.addAttribute("editProduct", editedProduct);
            model.addAttribute("errorMessagePrice", priceException.getMessage());
            return "product-edit";
        } catch (DescriptionException descriptionException) {
            model.addAttribute("editProduct", editedProduct);
            model.addAttribute("errorMessageDescription", descriptionException.getMessage());
            return "product-edit";
        }
    }

    @GetMapping(path = "deleteById")
    public String deleteById(@RequestParam("id") int id) {
        System.out.println("Deleting product with id:" + id);
        productService.deleteProduct(id);
        return "redirect:/product";
    }
}


