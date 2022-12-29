package com.example.springproject;

import com.example.springproject.service.RepoServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringprojectApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringprojectApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(RepoServiceImpl service){
//		System.out.println("start load data!");
//		return args -> {
//			service.loadData("gnembon/carpet-extra",1);

//		};
//	}
}
