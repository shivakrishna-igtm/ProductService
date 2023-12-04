package com.example.productservice.Controller;

import com.example.productservice.Dtos.GeneralProductDto;
import com.example.productservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/")
public class ProductController {
    ProductService ps;
    @Autowired
    ProductController(ProductService service){
        this.ps=service;
    }
    @GetMapping("/{id}")
    public GeneralProductDto getProductById(@PathVariable("id") Long id){
        return ps.getProductById(id);
    }
    @GetMapping()
    public List<GeneralProductDto> getAllProducts(){
        return ps.getAllProducts();
    }
    @PostMapping
    public GeneralProductDto createProduct(@RequestBody GeneralProductDto generalProductDto){
        try{
            ps.createProduct(generalProductDto);
        }
        catch (Exception e){
            System.out.print(e);
        }
        return generalProductDto;

    }
    public void deleteProduct(){

    }
    public void updateProduct(){

    }
}
