package com.example.warsztatsamochodowy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * The type Warsztat samochodowy application.
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class WarsztatSamochodowyApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WarsztatSamochodowyApplication.class, args);
    }

}
