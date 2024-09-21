package com.example.demo.response;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {

    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
    private T data;

    public SuccessResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<SuccessResponse<T>> createSuccessResponse(int statusCode, String message, T data) {
        SuccessResponse<T> response = new SuccessResponse<>(statusCode, message, data);
        return new ResponseEntity<>(response, HttpStatus.valueOf(statusCode));
    }
}
