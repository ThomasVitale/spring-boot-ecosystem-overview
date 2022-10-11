package com.thomasvitale.demoreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class DemoReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoReactiveApplication.class, args);
	}

}

@RestController
@RequestMapping("books")
class DemoController {

	private final BookRepository bookRepository;

	public DemoController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping
	Flux<Book> getBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("{id}")
	Mono<Book> getBooks(@PathVariable Long id) {
		return bookRepository.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Mono<Book> addBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

}

record Book(@Id Long id, String title){}

interface BookRepository extends ReactiveCrudRepository<Book,Long> {}
