package com.baeldung.queryparamdoc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = BookController.class)
class BookControllerReactiveIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;
    @Test
    void smokeTest() {
        assertThat(webTestClient).isNotNull();
    }

    @Test
    @WithMockUser
    void giveEndpoint_whenSendGetRequest_thenSuccessfulResponse() {
        webTestClient.get().uri("/books")
          .exchange().expectStatus().isOk();
    }
}