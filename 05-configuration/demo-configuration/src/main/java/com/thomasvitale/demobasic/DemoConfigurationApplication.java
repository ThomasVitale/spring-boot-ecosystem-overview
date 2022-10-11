package com.thomasvitale.demobasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DemoConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoConfigurationApplication.class, args);
	}

}

@ConfigurationProperties(prefix = "demo")
record DemoProperties(String greeting){}

@RestController
class DemoController {

	private final DemoProperties demoProperties;

	public DemoController(DemoProperties demoProperties) {
		this.demoProperties = demoProperties;
	}

	@GetMapping("/")
	String greeting() {
		return demoProperties.greeting();
	}
	
}
