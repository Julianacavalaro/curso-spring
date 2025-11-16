package org.example.api.exception;


import org.springframework.http.HttpStatus;

public class DuplicadoException extends RuntimeException {

    public DuplicadoException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT; // 409
    }
}
