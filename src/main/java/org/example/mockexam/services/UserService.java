package org.example.mockexam.services;

import org.example.mockexam.enums.UserStatus;
import org.example.mockexam.modules.dto.request.ChangePasswordRequest;
import org.example.mockexam.modules.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getOneUser(Long id);

    List<UserResponse> getAllUsers();

    void changePassword(ChangePasswordRequest changePasswordRequest);

    UserResponse changeStatus(Long id, UserStatus status);
}
