package com.thomasvitale.demorest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebMvcTest(DemoController.class)
public class DemoControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenGetThenReturnList() {
        webTestClient
            .get()
            .uri("/books")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Book.class).hasSize(0);
    }
    
}
