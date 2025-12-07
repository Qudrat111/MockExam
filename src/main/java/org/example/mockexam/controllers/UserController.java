package org.example.mockexam.controllers;

import jakarta.validation.Valid;
import org.example.mockexam.configs.annotation.IsAdmin;
import org.example.mockexam.enums.UserStatus;
import org.example.mockexam.modules.dto.request.ChangePasswordRequest;
import org.example.mockexam.modules.dto.response.UserResponse;
import org.example.mockexam.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.mockexam.configs.util.SecurityUtil.getUserId;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("me")
    public UserResponse getMe() {
        return userService.getOneUser(getUserId());
    }

    @IsAdmin
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @IsAdmin
    @PutMapping("change-password")
    public void changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
    }

    @IsAdmin
    @PutMapping("change-status/{id}")
    public UserResponse changeStatus(@PathVariable Long id, @RequestParam UserStatus status) {
        return userService.changeStatus(id, status);
    }
}
