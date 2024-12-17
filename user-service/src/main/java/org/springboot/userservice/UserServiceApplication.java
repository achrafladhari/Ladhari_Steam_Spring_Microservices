package org.springboot.userservice;

import lombok.AllArgsConstructor;
import org.springboot.userservice.config.ApplicationConfig;
import org.springboot.userservice.config.SecurityConfig;
import org.springboot.userservice.repository.UserRepo;
import org.springboot.userservice.user.Role;
import org.springboot.userservice.user.UserApp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@EnableFeignClients
@SpringBootApplication
@AllArgsConstructor
public class UserServiceApplication {

	//Building ADMIN USER
	private final ApplicationConfig securityConfiguration;
	@Bean
	CommandLineRunner commandLineRunner(UserRepo userRepository) {
		return args -> {
			var adminExists=userRepository.findByUsername("admin");
			if (adminExists.isPresent()){
				System.out.println("admin already exists");
			}
			else {
				UserApp u1 = UserApp.builder()
					.name("admin")
					.email("admin@admin.com")
					.username("admin")
					.id(UUID.randomUUID().toString())
					.password(securityConfiguration.passwordEncoder().encode("admin"))
					.role(Role.ADMIN)
					.build();
				userRepository.save(u1);
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
