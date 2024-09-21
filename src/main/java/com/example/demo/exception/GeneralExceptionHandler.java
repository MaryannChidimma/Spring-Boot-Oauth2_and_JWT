package com.example.demo.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import  java.util.Map;
import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    @ExceptionHandler(CustomErrorHandler.class)
    public ResponseEntity<CustomErrorMessage> handleCustomErrorHandler(CustomErrorHandler ex) {
        // Determine the status code
        HttpStatus httpStatus = (ex.getStatus() != null ? ex.getStatus() : HttpStatus.BAD_REQUEST);

        // Create the error response
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(
                httpStatus.value(),            // status code
                LocalDateTime.now(),           // timestamp
                ex.getMessage(),               // error message
                Collections.emptyList()        // empty error list
        );

        log.warn(customErrorMessage.toString());
        return new ResponseEntity<>(customErrorMessage, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<CustomFieldError> fieldErrors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.add(new CustomFieldError(fieldName, errorMessage));
        });

        // Create the error response
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(
                HttpStatus.BAD_REQUEST.value(), // status code
                LocalDateTime.now(),           // timestamp
                "Validation failed",           // error message
                fieldErrors                    // error details
        );

        log.warn(customErrorMessage.toString());
        return new ResponseEntity<>(customErrorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<CustomErrorMessage> handleAuthenticationException(AuthenticationException ex) {
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now(),
                "Authentication failed: " + ex.getMessage(),
                Collections.emptyList()
        );
        return new ResponseEntity<>(customErrorMessage, HttpStatus.UNAUTHORIZED);
    }
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public CustomErrorMessage handleGeneralExceptions(Exception ex) {
//        return new CustomErrorMessage(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                LocalDateTime.now(),
//                "An unexpected error occurred",
//                Collections.emptyList()
//        );
//    }
}

