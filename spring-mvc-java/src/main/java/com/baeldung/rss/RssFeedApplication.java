package com.baeldung.rss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Spring Boot launcher for an application which exposes an RSS Feed.
 * 
 * @author Donato Rimenti
 *
 */
@SpringBootApplication
public class RssFeedApplication extends SpringBootServletInitializer {

	/**
	 * Launches a Spring Boot application which exposes an RSS Feed.
	 * 
	 * @param args null
	 */
    public static void main(final String[] args) {
        SpringApplication.run(RssFeedApplication.class, args);
    }
}