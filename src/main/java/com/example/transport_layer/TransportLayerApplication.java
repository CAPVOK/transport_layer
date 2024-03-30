package com.example.transport_layer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TransportLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportLayerApplication.class, args);
    }

}
