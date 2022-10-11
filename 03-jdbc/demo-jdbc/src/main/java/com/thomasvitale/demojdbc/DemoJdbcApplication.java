package com.thomasvitale.demojdbc;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJdbcApplication.class, args);
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
	Iterable<Book> getBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("{id}")
	Optional<Book> getBooks(@PathVariable Long id) {
		return bookRepository.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Book addBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

}

record Book(@Id Long id, String title){}

interface BookRepository extends CrudRepository<Book,Long> {}
