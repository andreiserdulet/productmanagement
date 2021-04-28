package com.itschool.productmanagement.service;

import com.itschool.productmanagement.entities.ProductModel;
import com.itschool.productmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> displayProduct() {
        List<ProductModel> productModelList = productRepository.findAll();
        return productModelList;
    }

    public void addAProduct(ProductModel product) {
        productRepository.save(product);
    }
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
    public ProductModel findById(int id){
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);
        return optionalProductModel.get();
    }
    public void edit(ProductModel editedProduct){
        productRepository.save(editedProduct);
    }
}
