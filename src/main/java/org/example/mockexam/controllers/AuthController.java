package org.example.mockexam.controllers;


import jakarta.validation.Valid;
import org.example.mockexam.configs.annotation.IsAdmin;
import org.example.mockexam.modules.dto.request.UserCreateRequest;
import org.example.mockexam.modules.dto.request.UserLoginRequest;
import org.example.mockexam.modules.dto.response.TokenResponse;
import org.example.mockexam.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @IsAdmin
    @PostMapping("register")
    public void register(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        authService.register(userCreateRequest);
    }

    @PostMapping("login")
    public TokenResponse login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return authService.login(userLoginRequest);
    }
}
