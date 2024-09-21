package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomFieldError {
    private String field;
    private String message;
    //public CustomFieldError(String field, String defaultMessage) {
    //}
}
