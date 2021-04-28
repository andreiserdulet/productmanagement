package com.itschool.productmanagement.repository;

import com.itschool.productmanagement.entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
}
