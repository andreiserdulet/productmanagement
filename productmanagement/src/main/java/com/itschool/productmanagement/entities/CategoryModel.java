package com.itschool.productmanagement.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;
    private String categoryName;


    public int getId() {
        return idCategory;
    }

    public void setId(int id) {
        this.idCategory = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
