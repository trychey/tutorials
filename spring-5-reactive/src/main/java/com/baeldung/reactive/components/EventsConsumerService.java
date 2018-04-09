/*
 *
 * Copyright 2001-2018 by HireRight, Inc. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of HireRight, Inc. Use is subject to license terms.
 *
 * History:
 *	arman.mussagaliyev	2018-04-06	Created
 */
package com.baeldung.reactive.components;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

/**
 * 
 */
public class EventsConsumerService {
    
    private static Logger LOG = LoggerFactory.getLogger(EventsConsumerService.class);
    
    public static Flux<Long> consume()
    {
        WebClient client = WebClient.create("http://localhost:8080");
        WebClient.UriSpec<WebClient.RequestBodySpec> request = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec service= request.uri("/events");
        
        return service          
          .accept(MediaType.TEXT_EVENT_STREAM)
          .retrieve()
          .bodyToFlux(Long.class);
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        EventsConsumerService.consume()
          .map(a -> a.toString()) 
          .retry() //retry if exception is occurred
          .subscribe(LOG::info); //Do something with an event
      
        Thread.currentThread().join();
    }
    
}
