package com.dut.CinemaProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dut.CinemaProject.dao.repos"})
public class CinemaProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(CinemaProjectApplication.class, args);
	}
}
