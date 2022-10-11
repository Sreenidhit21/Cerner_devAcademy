package com.devAcademy.patientManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = "com.devAcademy.patientManagement.entity") 
public class PatientManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientManagementApplication.class, args);
	}

}
