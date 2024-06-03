package com.gundemgaming.fukantin.controller;

import com.gundemgaming.fukantin.dto.JwtAuthResponse;
import com.gundemgaming.fukantin.dto.UserAuthDto;
import com.gundemgaming.fukantin.service.impl.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class UserAuthController {

    private final AuthService authService;

    public UserAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody UserAuthDto authDto) {
        String token = authService.login(authDto);
        JwtAuthResponse authResponse = new JwtAuthResponse();
        authResponse.setAccessToken(token);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@Valid @RequestBody UserAuthDto authDto) {
        String response = authService.register(authDto);
        return ResponseEntity.ok(response);
    }

}
