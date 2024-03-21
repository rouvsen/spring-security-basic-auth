package com.rouvsen.springsecuritybasicauth.service;

import com.rouvsen.springsecuritybasicauth.dto.CreateUserRequest;
import com.rouvsen.springsecuritybasicauth.model.User;
import com.rouvsen.springsecuritybasicauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User create(CreateUserRequest request) {
        return userRepository.save(
                User.builder()
                        .name(request.name())
                        .username(request.username())
                        .password(passwordEncoder.encode(request.password()))
                        .authorities(request.authorities())
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .isEnabled(true)
                        .build()
        );
    }

}
