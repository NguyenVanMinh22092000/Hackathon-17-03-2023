package com.codegym.springtoken;

import com.codegym.springtoken.model.Role;
import com.codegym.springtoken.model.User;
import com.codegym.springtoken.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTokenApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "USER"));
			userService.saveRole(new Role(null, "ADMIN"));
			userService.saveRole(new Role(null, "MANAGER"));
			userService.saveRole(new Role(null, "SUPER_ADMIN"));

			userService.saveUser( new User(null, "Minh", "minh@", "1234", new ArrayList<>()));
			userService.saveUser( new User(null, "Hen", "hien@", "1234", new ArrayList<>()));
			userService.saveUser( new User(null, "Phong", "phong@", "1234", new ArrayList<>()));
			userService.saveUser( new User(null, "Linh", "linh@", "1234", new ArrayList<>()));

			userService.addRoleToUser("minh@", "USER");
			userService.addRoleToUser("minh@", "ADMIN");
			userService.addRoleToUser("hien@", "MANAGER");
			userService.addRoleToUser("hien@", "ADMIN");
			userService.addRoleToUser("phong@", "USER");
			userService.addRoleToUser("linh@", "SUPER_ADMIN");
			userService.addRoleToUser("linh@", "MANAGER");
		};
	}
}
