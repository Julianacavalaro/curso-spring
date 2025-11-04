package org.example.hello;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord  {

    public String hello(){
        return "Hello Word";
    }
}
