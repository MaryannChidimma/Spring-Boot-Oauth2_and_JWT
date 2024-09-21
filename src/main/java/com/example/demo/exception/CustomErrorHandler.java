package com.example.demo.exception;
import org.springframework.http.HttpStatus;

public class CustomErrorHandler extends RuntimeException {

    private final HttpStatus status;

    public CustomErrorHandler(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST; // Default status
    }

    public CustomErrorHandler(String message, Throwable cause) {
        super(message, cause);
        this.status = HttpStatus.BAD_REQUEST; // Default status
    }

    public CustomErrorHandler(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CustomErrorHandler(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
