package com.baeldung.globaltimezone;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        // For having the value available from the very early stages of the execution, even before detecting the Spring Profile, set the value here:
        // TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
        LOGGER.info("Default timezone, before main run, is set to: " + TimeZone.getDefault()
            .getDisplayName());

        SpringApplication.run(MainApplication.class, args);
    }

    @PostConstruct
    public void executeAfterMain() {
        // For having the value available just after WebApplicationContext initialization is completed, set the value here:
        // TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
        LOGGER.info("Default timezone, after main run, is set to: " + TimeZone.getDefault()
            .getDisplayName());
    }
}
