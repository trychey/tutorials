package com.baeldung.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HttpClientCancelRequestV4LiveTest {

    private static final Logger log = LoggerFactory.getLogger(HttpClientCancelRequestV4LiveTest.class);

    private static final String SAMPLE_URL = "http://www.github.com";

    private CloseableHttpClient instance;

    private CloseableHttpResponse response;

    @BeforeEach
    public final void before() {
        instance = HttpClientBuilder.create().build();
    }

    @AfterEach
    public final void after() throws IllegalStateException, IOException {
        ResponseUtil.closeResponse(response);
    }

    @Test
    final void whenRequestIsCanceled_thenCorrect() throws IOException {
        instance = HttpClients.custom().build();
        final HttpGet request = new HttpGet(SAMPLE_URL);
        response = instance.execute(request);

        try {
            final HttpEntity entity = response.getEntity();

            log.debug(String.valueOf(response.getStatusLine()));
            if (entity != null) {
                log.debug("Response content length: {}" , entity.getContentLength());
                entity.getContent().close();
            }


            // Do not feel like reading the response body
            // Call abort on the request object
            request.abort();
        } finally {
            response.close();
        }
    }
}
