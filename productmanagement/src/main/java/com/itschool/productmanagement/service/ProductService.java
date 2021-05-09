package com.itschool.productmanagement.service;

import com.itschool.productmanagement.entities.ProductModel;
import com.itschool.productmanagement.exception.CurrencyException;
import com.itschool.productmanagement.exception.DescriptionException;
import com.itschool.productmanagement.exception.NameException;
import com.itschool.productmanagement.exception.PriceException;
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
        if (product.getProductName().equals("")) {
            RuntimeException exception = new NameException("The product is not valid");
            throw exception;
        }
        else if (product.getProductDescription().length() >= 1000 || product.getProductDescription().equals("")){
            RuntimeException exception = new DescriptionException("Description is not valid");
            throw exception;
        }
        else if (product.getPrice() >= 1000 || product.getPrice() == 0) {
            RuntimeException exception = new PriceException("The price is not valid");
            throw  exception;
        }
        else if(!product.getCurrency().equals("lei") && !product.getCurrency().equals("euro")){
            RuntimeException exception = new CurrencyException("Currency is not accepted");
            throw exception;
        }
        else {
            productRepository.save(product);
        }
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public ProductModel findById(int id) {
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);
        return optionalProductModel.get();
    }

    public void edit(ProductModel editedProduct) {
        if (editedProduct.getProductName().equals("")) {
            RuntimeException exception = new NameException("The product is not valid");
            throw exception;
        }
        else if (editedProduct.getProductDescription().length() >= 1000 || editedProduct.getProductDescription().equals("")){
            RuntimeException exception = new DescriptionException("Description is not valid");

            throw exception;
        }
        else if (editedProduct.getPrice() >= 1000 || editedProduct.getPrice() == 0) {
            RuntimeException exception = new PriceException("The price is not valid");
            throw  exception;
        }
        else if(!editedProduct.getCurrency().equals("lei") || !editedProduct.getCurrency().equals("euro")){
            RuntimeException exception = new CurrencyException("The currency is not valid");
            throw exception;
        }
        else {
            productRepository.save(editedProduct);
        }
    }
}
