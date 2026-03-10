package com.example.hellowicket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class EmployeeApp {
    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder()
                .sources(EmployeeApp.class)
                .run(args);
    }
}
