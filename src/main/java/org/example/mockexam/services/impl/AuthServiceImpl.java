package org.example.mockexam.services.impl;

import org.example.mockexam.configs.exception.BadCredentialsException;
import org.example.mockexam.configs.exception.UserExistException;
import org.example.mockexam.configs.exception.UserInactiveException;
import org.example.mockexam.configs.exception.UserNotFoundException;
import org.example.mockexam.configs.jwt.JwtProvider;
import org.example.mockexam.enums.UserStatus;
import org.example.mockexam.modules.dto.request.UserCreateRequest;
import org.example.mockexam.modules.dto.request.UserLoginRequest;
import org.example.mockexam.modules.dto.response.TokenResponse;
import org.example.mockexam.modules.entity.User;
import org.example.mockexam.modules.mapper.UserMapper;
import org.example.mockexam.repositories.UserRepository;
import org.example.mockexam.services.AuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, @Lazy PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndDeletedFalse(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void register(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByUsername(userCreateRequest.getUsername())) throw new UserExistException();

        userRepository.save(userMapper.toEntity(userCreateRequest));
    }

    @Override
    public TokenResponse login(UserLoginRequest userLoginRequest) {
        User existUser = loadUserByUsername(userLoginRequest.getUsername());

        if (existUser.getStatus() == UserStatus.INACTIVE) throw new UserInactiveException();

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), existUser.getPassword()))
            throw new BadCredentialsException();

        return jwtProvider.generateAccessToken(userLoginRequest.getUsername());
    }
}
