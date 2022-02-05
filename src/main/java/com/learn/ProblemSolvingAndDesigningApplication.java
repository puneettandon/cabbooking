package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = "com.learn.cabbooking")
public class ProblemSolvingAndDesigningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProblemSolvingAndDesigningApplication.class, args);
	}

}
