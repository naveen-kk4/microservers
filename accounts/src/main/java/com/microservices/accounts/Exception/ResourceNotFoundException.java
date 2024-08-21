package com.microservices.accounts.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue){
        /*
        Format specifiers start with a % character, followed by an optional set of flags and a type character. Here are some common format specifiers:

%s: String
%d: Decimal integer
%f: Floating-point number
%x: Hexadecimal integer
%c: Character
%%: Literal % character
String name = "John";
int age = 25;
String formattedString = String.format("Name: %s, Age: %d", name, age);
         */
        super(String.format("%s not found with the given input data %s:'%s'" , resourceName,fieldName,fieldValue));
    }
}
