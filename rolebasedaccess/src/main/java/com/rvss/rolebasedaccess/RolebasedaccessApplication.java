package com.rvss.rolebasedaccess;

import com.rvss.rolebasedaccess.entity.Role;
import com.rvss.rolebasedaccess.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rvss.rolebasedaccess.repository")
public class RolebasedaccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(RolebasedaccessApplication.class, args);
	}

	@Bean
	public CommandLineRunner initRoles(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findAll().isEmpty()) {
				roleRepository.save(new Role("ADMIN"));
				roleRepository.save(new Role("SCHOOL_ADMIN"));
				roleRepository.save(new Role("CLASS_TEACHER"));
				roleRepository.save(new Role("TEACHER"));
				roleRepository.save(new Role("STUDENT"));
				System.out.println("✅ Default roles seeded");
			}
		};
	}
}
