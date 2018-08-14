package com.hcl_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AndroidPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(AndroidPocApplication.class, args);
	}
}
