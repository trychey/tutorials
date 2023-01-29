package com.baeldung.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.net.ssl.SSLPeerUnverifiedException;

import com.baeldung.client.spring.ClientConfig;
import com.baeldung.web.dto.Foo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ClientConfig.class }, loader = AnnotationConfigContextLoader.class)
public class ClientLiveTest {

    @Autowired
    private RestTemplate secureRestTemplate;

    // tests

    @Test
    void whenContextIsBootstrapped_thenNoExceptions() {
        //
    }

    @Test
    void whenSecuredRestApiIsConsumed_then200OK() {
        final ResponseEntity<Foo> responseEntity = secureRestTemplate.exchange("http://localhost:8082/httpclient-simple/api/foos/1", HttpMethod.GET, null, Foo.class);
        assertThat(responseEntity.getStatusCode().value(), is(200));
    }

    @Test
    void whenHttpsUrlIsConsumed_thenException() {
        final String urlOverHttps = "https://localhost:8443/httpclient-simple/api/bars/1";

        assertThrows(ResourceAccessException.class, () -> {
            final ResponseEntity<String> response = new RestTemplate()
                .exchange(urlOverHttps, HttpMethod.GET, null, String.class);
            assertThat(response.getStatusCode().value(), equalTo(200));
        });
    }

}
