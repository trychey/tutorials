package com.baeldung.tutorials.openapi.quotes;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.AdviceWithBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baeldung.tutorials.openapi.quotes.client.QuotesApi;

@SpringBootTest
class ApplicationUnitTest {

	@Autowired
	private ProducerTemplate producerTemplate;

	@Autowired
	private CamelContext camel;


	@Test
	void contextLoads() throws Exception {
		AdviceWith.adviceWith(camel, QuotesApi.GET_QUOTE_ROUTE_ID, in -> {
			in.mockEndpointsAndSkip("rest:*");
		});

		producerTemplate.sendBody(QuotesApi.GET_QUOTE,null);
	}

}
