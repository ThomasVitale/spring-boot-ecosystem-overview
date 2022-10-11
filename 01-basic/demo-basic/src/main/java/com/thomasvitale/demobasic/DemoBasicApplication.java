package com.thomasvitale.demobasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBasicApplication.class, args);
	}

}

@RestController
class DemoController {

	@GetMapping("/")
	String greeting() {
		return "Hello, folks!";
	}
	
}
