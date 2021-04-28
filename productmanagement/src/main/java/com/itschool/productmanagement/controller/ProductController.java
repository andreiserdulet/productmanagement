package com.itschool.productmanagement.controller;

import com.itschool.productmanagement.entities.ProductModel;
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
        return "product-add";
    }

    @GetMapping(path = "product-add")
    private String addProduct(@ModelAttribute ProductModel newProduct) {
        System.out.println("Add Product ->" + newProduct.getProductName() + " " + newProduct.getProductDescription()
                + " " + newProduct.getPrice() + " " + newProduct.getCurrency());
        productService.addAProduct(newProduct);
        return "redirect:/product";
    }
    @GetMapping(path = "edit-product")
    private String viewProductPage(@RequestParam int id, Model model){
        ProductModel foundProduct = productService.findById(id);
        model.addAttribute("editProduct", foundProduct);
        return "product-edit";

    }
    @GetMapping(path = "product-edit")
    public String editProduct(@ModelAttribute ProductModel editedProduct){
        productService.edit(editedProduct);
        return "redirect:/product";
    }
    @GetMapping(path = "deleteById")
    public String deleteById(@RequestParam("id") int id) {
        System.out.println("Deleting product with id:" + id);
        productService.deleteProduct(id);
        return "redirect:/product";
    }

}
