package com.rouvsen.springsecuritybasicauth;

import com.rouvsen.springsecuritybasicauth.dto.CreateUserRequest;
import com.rouvsen.springsecuritybasicauth.model.Role;
import com.rouvsen.springsecuritybasicauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityBasicAuthApplication implements CommandLineRunner {

	private final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicAuthApplication.class, args);
	}

	@Override
	public void run(String... args) {
		createDummyData();
	}

	private void createDummyData() {
		CreateUserRequest user1 = CreateUserRequest.builder()
				.name("John Doe")
				.username("Jenkoalaa")
				.password("pass")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
		CreateUserRequest user2 = CreateUserRequest.builder()
				.name("Hacklee")
				.username("Berdo mio")
				.password("pass")
				.authorities(Set.of(Role.ROLE_USER))
				.build();
		CreateUserRequest user3 = CreateUserRequest.builder()
				.name("Morde Olde")
				.username("Bioste")
				.password("pass")
				.authorities(Set.of(Role.ROLE_FSK))
				.build();
		userService.create(user1);
		userService.create(user2);
		userService.create(user3);
	}


}
