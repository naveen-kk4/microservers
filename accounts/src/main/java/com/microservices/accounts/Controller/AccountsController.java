package com.microservices.accounts.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("/sayMyName")
    public String sayMyName(){
        return "Hello Heisenberg!!!!!";
    }
}
