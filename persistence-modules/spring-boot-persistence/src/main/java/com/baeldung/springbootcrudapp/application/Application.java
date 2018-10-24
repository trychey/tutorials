package com.baeldung.springbootcrudapp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.baeldung.springbootcrudapp.application"})
@EnableJpaRepositories(basePackages="com.baeldung.springbootcrudapp.application.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.baeldung.springbootcrudapp.application.entities")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
