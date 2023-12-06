package com.example.productservice.Dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
//@Getter
//@Setter
@Data   //data works like combo of getter and setter
public class ExceptionDto {
    private HttpStatus httpStatus;
    private String message;
}
