package org.example.mockexam.services;

import org.example.mockexam.modules.dto.request.UserCreateRequest;
import org.example.mockexam.modules.dto.request.UserLoginRequest;
import org.example.mockexam.modules.dto.response.TokenResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    void register(UserCreateRequest userCreateRequest);

    TokenResponse login(UserLoginRequest userLoginRequest);
}
