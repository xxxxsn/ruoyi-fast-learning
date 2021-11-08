package com.xiesn.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoyiFastShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiFastShiroApplication.class, args);
    }

}
