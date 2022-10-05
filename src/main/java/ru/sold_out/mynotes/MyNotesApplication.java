package ru.sold_out.mynotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MyNotesApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyNotesApplication.class, args);
	}
}
