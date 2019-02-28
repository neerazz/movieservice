package com.neeraj.microservice.movies.movieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MovieserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieserviceApplication.class, args);
    }

}
