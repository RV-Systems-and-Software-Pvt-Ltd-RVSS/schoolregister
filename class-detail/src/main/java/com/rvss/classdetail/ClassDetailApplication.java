package com.rvss.classdetail;

import com.rvss.classdetail.beans.SchoolClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.rvss.classdetail.dataservices.SchoolClassJPARepository;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(basePackageClasses = SchoolClassJPARepository.class)
@EntityScan("com.rvss.classdetail.beans")
public class ClassDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassDetailApplication.class, args);
	}

}
