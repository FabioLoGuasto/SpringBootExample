package it.shop.shoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoesApplication.class, args);
		
		// SWAGGER
		// http://localhost:8080/swagger-ui/index.html
	}

}
