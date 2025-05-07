package com.rvss.subjectregister;

import com.rvss.subjectregister.dataservices.SubjectJPARepository;
import com.rvss.subjectregister.dataservices.SubjectTeacherJPARepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.rvss.subjectregister.beans.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackageClasses = {SubjectJPARepository.class, SubjectTeacherJPARepository.class})
@EntityScan("com.rvss.subjectregister.beans")

public class SubjectregisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubjectregisterApplication.class, args);
	}

}
