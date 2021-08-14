package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		//ApplicationContext in backend
		SpringApplication.run(Application.class, args);
		System.out.println("Hello Boot");
		System.out.println("Okay");
		System.out.println("Welcome");
		System.out.println("Welcome");
		Path folder = Paths.get("upload");
		if(!Files.exists(folder)) {
			try {
				Files.createDirectory(folder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
