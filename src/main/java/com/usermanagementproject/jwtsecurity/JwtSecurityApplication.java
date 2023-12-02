package com.usermanagementproject.jwtsecurity;

import com.usermanagementproject.jwtsecurity.entity.Role;
import com.usermanagementproject.jwtsecurity.entity.User;
import com.usermanagementproject.jwtsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JwtSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (adminAccount == null){
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setLastName("a");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
			userRepository.save(user);
		}

	}
}
