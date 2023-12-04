package com.example.productservice.Dtos;
//this dto adds an extra layer between required output and externalapi response
//because if variables in external api are extra added then we have to change our productreqDto too...if not it will throw
//an error and if we change according to the changes in external api,we may not require those variables in our outpt
//so we crate exra class which makes sure that the required variables are set no matter whatever the change happened in external apu

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralProductDto {
    private Long id;
    private String title;
    private int price;
    private String category;
    private String description;
    private String image;
}
