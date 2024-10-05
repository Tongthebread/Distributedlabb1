package com.example.webshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Category {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String categoryName;

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
