package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class baseController {

        @GetMapping("/")
        public ResponseEntity<String> sayHello(){
            return ResponseEntity.ok("Hello, welcome!");
        }
}
