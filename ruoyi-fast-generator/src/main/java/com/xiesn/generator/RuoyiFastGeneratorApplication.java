package com.xiesn.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoyiFastGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiFastGeneratorApplication.class, args);
    }

}
