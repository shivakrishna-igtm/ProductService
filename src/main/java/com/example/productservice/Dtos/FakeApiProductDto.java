package com.example.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeApiProductDto {
    private Long id;
    private String title;
    private int price;
    private String category;
    private String description;
    private String image;
}
