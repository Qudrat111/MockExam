package org.example.mockexam.modules.mapper;

import org.example.mockexam.modules.dto.request.UserCreateRequest;
import org.example.mockexam.modules.dto.response.UserResponse;
import org.example.mockexam.modules.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserCreateRequest userCreateRequest) {
        User newUser = new User();
        newUser.setUsername(userCreateRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        return newUser;
    }

    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(user.getRole());
        userResponse.setStatus(user.getStatus());
        return userResponse;
    }
}
