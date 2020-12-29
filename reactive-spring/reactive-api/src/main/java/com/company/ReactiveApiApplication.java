package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableWebFlux
public class ReactiveApiApplication {
//zewnetrzne api | baza | io plikowe | algorytm -> reaktywnie i imperatywnie i porownaj wydajnosc
    public static void main(String[] args) {
        SpringApplication.run(ReactiveApiApplication.class, args);
    }

}
