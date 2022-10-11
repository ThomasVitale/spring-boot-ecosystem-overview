package com.thomasvitale.demorest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestApplication.class, args);
	}

}

@RestController
@RequestMapping("books")
class DemoController {

	private static Map<Long,Book> books = new ConcurrentHashMap<>();

	@GetMapping
	Iterable<Book> getBooks() {
		return books.values();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Book addBook(@RequestBody Book book) {
		books.put(book.id(), book);
		return book;
	}

}

record Book(Long id, String title){}
