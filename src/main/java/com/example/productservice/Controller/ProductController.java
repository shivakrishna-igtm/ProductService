package com.example.productservice.Controller;

import com.example.productservice.Dtos.ExceptionDto;
import com.example.productservice.Dtos.FakeApiProductDto;
import com.example.productservice.Dtos.GeneralProductDto;
import com.example.productservice.Exceptions.ProductNotFound;
import com.example.productservice.Service.FakeProductImpl;
import com.example.productservice.Service.ProductService;
import jakarta.servlet.annotation.HandlesTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/")
public class ProductController {
    ProductService ps;
    @Autowired
    ProductController(@Qualifier("fakeProductImplementation") ProductService service){
        this.ps=service;
    }
    @GetMapping("/{id}")
    public GeneralProductDto getProductById(@PathVariable("id") Long id) throws ProductNotFound {
        return ps.getProductById(id);
    }
    //this handler works for all the methods in controller.if any method hits this kind of exception then this method will
    //be called by the use of exception handler annotation which is automatically called
    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ExceptionDto productNotFoundHandler(ProductNotFound productNotFound){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage(productNotFound.getMessage());
        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
        //or instead of using responsestatus annotation we can do it with responseEntity,refer below line
        //ResponseEntity response=new ResponseEntity(exceptionDto,HttpStatus.NOT_FOUND);
        //return response entity instead of exceptiondto
        return exceptionDto;
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
    @DeleteMapping("/{id}")
    public GeneralProductDto deleteProduct(@PathVariable("id") Long id){
        return ps.deleteProduct(id);

    }
    @PatchMapping("/{id}")
    public GeneralProductDto updateProduct(@RequestBody GeneralProductDto generalProductDto,@PathVariable("id") Long id){
        return ps.updateProduct(id,generalProductDto);

    }
}
