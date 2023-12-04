package com.example.productservice.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product extends BaseClass {
    private String title;
    private String description;
    private String image;
    private int price;
    private Category category;
}
