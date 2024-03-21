package com.rouvsen.springsecuritybasicauth.dto;

import com.rouvsen.springsecuritybasicauth.model.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateUserRequest(
        String name,
        String username,
        String password,
        Set<Role> authorities
) {
}
