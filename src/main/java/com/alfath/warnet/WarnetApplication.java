package com.alfath.warnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WarnetApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarnetApplication.class, args);
	}

}
