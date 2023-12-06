package com.example.productservice.Controller;

import com.example.productservice.Dtos.ExceptionDto;
import com.example.productservice.Exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductControllerAdvisor {
    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody()  //because responsestatus annotation not only set the status but also returns whole trace of exception
    //which is not good to return,so we can use responsebody annoation to send response with empty body
    private ExceptionDto productNotFoundHandler(ProductNotFound productNotFound){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage(productNotFound.getMessage());
        exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
        //or instead of using responsestatus annotation we can do it with responseEntity,refer below line
        //ResponseEntity response=new ResponseEntity(exceptionDto,HttpStatus.NOT_FOUND);
        //return response entity instead of exceptiondto
        return exceptionDto;
    }
}
