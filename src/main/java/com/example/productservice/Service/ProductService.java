package com.example.productservice.Service;

import com.example.productservice.Dtos.FakeApiProductDto;
import com.example.productservice.Dtos.GeneralProductDto;
import com.example.productservice.Exceptions.ProductNotFound;

import java.util.List;


public interface ProductService {
    public GeneralProductDto getProductById(Long id) throws ProductNotFound;
    public List<GeneralProductDto> getAllProducts();
    public  GeneralProductDto createProduct(GeneralProductDto generalProductDto);
    public  GeneralProductDto deleteProduct(Long id);
    public GeneralProductDto updateProduct(Long id, GeneralProductDto generalProductDto);
}
