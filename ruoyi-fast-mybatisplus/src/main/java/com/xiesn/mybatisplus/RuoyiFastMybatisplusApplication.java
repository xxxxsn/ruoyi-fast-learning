package com.xiesn.mybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoyiFastMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiFastMybatisplusApplication.class, args);
    }

}
