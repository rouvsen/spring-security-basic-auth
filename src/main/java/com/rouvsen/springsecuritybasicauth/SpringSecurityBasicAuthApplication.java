package com.rouvsen.springsecuritybasicauth;

import com.rouvsen.springsecuritybasicauth.model.Role;
import com.rouvsen.springsecuritybasicauth.model.User;
import com.rouvsen.springsecuritybasicauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityBasicAuthApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicAuthApplication.class, args);
	}

	@Override
	public void run(String... args) {
		createDummyData();
	}

	private void createDummyData() {
		User user1 = User.builder()
				.name("John Doe")
				.username("Jenkoalaa")
				.password("pass")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
		User user2 = User.builder()
				.name("Hacklee")
				.username("Berdo mio")
				.password("pass")
				.authorities(Set.of(Role.ROLE_USER))
				.build();
		User user3 = User.builder()
				.name("Morde Olde")
				.username("Bioste")
				.password("pass")
				.authorities(Set.of(Role.ROLE_FSK))
				.build();
		userRepository.saveAll(List.of(user1, user2, user3));
	}


}
