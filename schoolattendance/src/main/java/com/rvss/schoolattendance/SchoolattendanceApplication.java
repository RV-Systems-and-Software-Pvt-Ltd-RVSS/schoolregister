package com.rvss.schoolattendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication

@EnableAsync
@EnableJpaRepositories(basePackages = {
		"com.rvss.schoolattendance.dataservices"
})
@EntityScan(basePackages = {
		"com.rvss.schoolattendance.beans"
})
public class SchoolattendanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolattendanceApplication.class, args);
	}

}
