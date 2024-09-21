package com.example.demo.exception;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomErrorMessage {
    private final int statusCode;
    private final LocalDateTime timestamp;
    private final String message;
    private final List<CustomFieldError> errorList;
}
