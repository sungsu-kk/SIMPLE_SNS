package com.sungsu.project.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SimpleSnsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSnsApplication.class, args);
    }

}
