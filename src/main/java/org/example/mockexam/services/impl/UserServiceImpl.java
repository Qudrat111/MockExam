package org.example.mockexam.services.impl;

import org.example.mockexam.configs.exception.BadCredentialsException;
import org.example.mockexam.configs.exception.UserNotFoundException;
import org.example.mockexam.enums.UserStatus;
import org.example.mockexam.modules.dto.request.ChangePasswordRequest;
import org.example.mockexam.modules.dto.response.UserResponse;
import org.example.mockexam.modules.entity.User;
import org.example.mockexam.modules.mapper.UserMapper;
import org.example.mockexam.repositories.UserRepository;
import org.example.mockexam.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getOneUser(Long id) {
        return userRepository.findByIdAndDeletedFalse(id)
                .map(userMapper::toResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAllNotDeleted().stream().map(userMapper::toResponse).toList();
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        User existUser = userRepository.findByIdAndDeletedFalse(changePasswordRequest.getId()).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), existUser.getPassword()))
            throw new BadCredentialsException();

        existUser.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(existUser);
    }

    @Override
    public UserResponse changeStatus(Long id, UserStatus status) {
        User existUser = userRepository.findByIdAndDeletedFalse(id).orElseThrow(UserNotFoundException::new);

        existUser.setStatus(status);

        return userMapper.toResponse(userRepository.save(existUser));
    }
}
