package com.yee.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class YeeApplication {

    public static void main(String[] args) {

        SpringApplication.run(YeeApplication.class, args);
    }

}
