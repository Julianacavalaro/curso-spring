package org.example.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ErroResponse {

    private Class<?> exceptionClass;
    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime thrownAt;

    public  <T> ErroResponse(Class exceptionClass, HttpStatus httpStatus, String message){
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
        this.message = message;
        this.thrownAt = LocalDateTime.now();
    }
}
