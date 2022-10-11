package com.thomasvitale.demojdbc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.datasource.url=jdbc:tc:postgresql:14:///")
class DemoJdbcApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostThenBookCreated() {
		var bookToCreate = new Book(null, "His Dark Materials");

		webTestClient
			.post()
			.uri("/books")
			.bodyValue(bookToCreate)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(Book.class).value(actualBook -> {
				Assertions.assertThat(actualBook.id()).isNotNull();
				Assertions.assertThat(actualBook.title()).isEqualTo(bookToCreate.title());
			});
	}

}
