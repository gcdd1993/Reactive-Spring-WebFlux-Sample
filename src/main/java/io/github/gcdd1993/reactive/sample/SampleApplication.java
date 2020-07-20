package io.github.gcdd1993.reactive.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class SampleApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SampleApplication.class, args);
        } catch (Exception ex) {
            log.error("startup failed", ex);
        }
    }

}
