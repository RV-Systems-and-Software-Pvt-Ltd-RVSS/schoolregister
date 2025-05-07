package com.rvss.schoolregister;

import com.rvss.schoolregister.dataservices.DatabaseConfig;
import com.rvss.schoolregister.dataservices.SchoolRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@EnableMongoRepositories(basePackageClasses = SchoolRepository.class)
public class SchoolregisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolregisterApplication.class, args);
	}

}
