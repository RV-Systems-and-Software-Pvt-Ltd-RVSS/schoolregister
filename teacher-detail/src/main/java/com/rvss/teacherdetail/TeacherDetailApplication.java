package com.rvss.teacherdetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.rvss.teacherdetail.dataservices.TeacherJPARepository;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackageClasses = TeacherJPARepository.class)
@EntityScan("com.rvss.teacherdetail.beans")
public class TeacherDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherDetailApplication.class, args);
	}

}
