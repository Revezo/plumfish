package com.traanite.plumfish;

import com.traanite.plumfish.publisher.PublisherProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({PublisherProperties.class})
public class PlumfishApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlumfishApplication.class, args);
    }

}
