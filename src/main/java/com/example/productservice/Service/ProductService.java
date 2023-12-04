package com.example.productservice.Service;

import com.example.productservice.Dtos.GeneralProductDto;

import java.util.List;


public interface ProductService {
    public GeneralProductDto getProductById(Long id);
    public List<GeneralProductDto> getAllProducts();
    public  GeneralProductDto createProduct(GeneralProductDto generalProductDto);
    public  void deleteProduct(Long id);
    public void updateProduct(Long id);
}
