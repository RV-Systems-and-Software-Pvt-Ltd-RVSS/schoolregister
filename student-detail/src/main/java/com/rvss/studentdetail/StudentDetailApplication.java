package com.rvss.studentdetail;

import com.rvss.studentdetail.dataservices.StudentJPARepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackageClasses = StudentJPARepository.class)
@EntityScan("com.rvss.studentdetail.beans")
public class StudentDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentDetailApplication.class, args);
	}

}
