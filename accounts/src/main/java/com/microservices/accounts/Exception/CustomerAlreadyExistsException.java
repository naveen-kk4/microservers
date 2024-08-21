package com.microservices.accounts.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
/*
Usecase of @ResponseStatus
Automatic HTTP Response Status:

When the CustomerAlreadyExistsException is thrown in your application, Spring will automatically set the HTTP response status to 400 Bad Request without requiring any additional configuration or handling in your controller or service layer.
Simplifies Error Handling:

By using @ResponseStatus, you avoid writing repetitive error-handling code. There is no need to manually set the response status code in a controller's @ExceptionHandler or in the service layer. Spring will handle this for you.

 */
public class CustomerAlreadyExistsException extends RuntimeException{
    public  CustomerAlreadyExistsException (String message) {
        super(message);
    }


}


