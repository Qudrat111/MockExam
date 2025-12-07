package org.example.mockexam.configs.loader;

import org.example.mockexam.enums.Role;
import org.example.mockexam.modules.entity.User;
import org.example.mockexam.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("admin")) {
            User user = new User();

            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setRole(Role.ADMIN);

            userRepository.save(user);
        }
    }
}
