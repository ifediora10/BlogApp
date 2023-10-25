package com.example.demo.controllers;

import com.example.demo.dtos.request.LoginDto;
import com.example.demo.dtos.request.SignupDto;
import com.example.demo.dtos.response.UserResponseDto;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginAndSignUpController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody SignupDto signupDto){
        return ResponseEntity.ok(userService.registerNewUser(signupDto));
    }

    @PostMapping(path = "/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<UserResponseDto> loginUser(@Valid @RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.loginUser(loginDto));
    }
}
