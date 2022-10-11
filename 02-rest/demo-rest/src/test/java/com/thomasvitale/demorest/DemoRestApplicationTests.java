package com.thomasvitale.demorest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoRestApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostThenBookCreated() {
		var bookToCreate = new Book(21L, "His Dark Materials");

		webTestClient
			.post()
			.uri("/books")
			.bodyValue(bookToCreate)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(Book.class).isEqualTo(bookToCreate);
	}

}
