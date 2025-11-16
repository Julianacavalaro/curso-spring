package org.example.api.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicadoException.class)
    public Map<String, String> handleDuplicado(DuplicadoException ex) {
        return Map.of("erro", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NaoEncontradoException.class)
    public Map<String, String> handleNaoEncontrado(NaoEncontradoException ex) {
        return Map.of("erro", ex.getMessage());
    }
}
