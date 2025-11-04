package org.example;

import org.example.hello.HelloWord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CursoSpringApplication.class, args);
        HelloWord helloWord = new HelloWord();
       System.out.println(helloWord.hello());
    }
}
