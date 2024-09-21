package com.example.demo.auth;
import com.example.demo.auth.dto.AuthenticationResponse;
import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.dto.RegisterRequest;
import com.example.demo.exception.CustomErrorHandler;
import com.example.demo.response.SuccessResponse;
import com.example.demo.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private  final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<AuthenticationResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        Optional<User> user = service.doesUserExist(request.getEmail());
        if (user.isPresent()) {
            throw new CustomErrorHandler
                    ("User with that email address already exists", HttpStatus.CONFLICT);
        }
        AuthenticationResponse response = service.register(request);
        return SuccessResponse.createSuccessResponse
                (HttpStatus.CREATED.value(), "User Registration", response);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<AuthenticationResponse>> login(
           @Valid @RequestBody LoginRequest request
    ) throws CustomErrorHandler {
        Optional<User> user = service.doesUserExist(request.getEmail());
        if (user.isEmpty()) {
            throw new CustomErrorHandler
                    ("Invalid Credentials", HttpStatus.CONFLICT);
        }
        AuthenticationResponse response = service.login(request, user);
        return SuccessResponse.createSuccessResponse
                (HttpStatus.OK.value(), "User Login", response);
    }
}

