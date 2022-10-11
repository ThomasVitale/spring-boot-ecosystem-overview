package com.thomasvitale.demoevents;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class DemoEventsApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoEventsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoEventsApplication.class, args);
	}

	@Bean
	Function<Instrument,String> uppercase() {
		return instrument -> {
			log.info("Converting {} to uppercase", instrument.name());
			return instrument.name().toUpperCase();
		};
	}

	@Bean
	Function<Mono<String>,Mono<Skill>> sentence() {
		return mono -> mono.map(instrument -> {
			log.info("Building sentence with {}", instrument);
			return new Skill("I play the "  + instrument);
		});
	}

}

record Instrument(String name){}
record Skill(String sentence){}
