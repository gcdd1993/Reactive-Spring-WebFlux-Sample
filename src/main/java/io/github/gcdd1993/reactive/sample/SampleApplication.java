package io.github.gcdd1993.reactive.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SampleApplication.class, args);
        } catch (Exception ex) {
            log.error("startup failed", ex);
        }
    }

}
