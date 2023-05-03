package com.example.tripms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TripMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripMsApplication.class, args);
    }

}
