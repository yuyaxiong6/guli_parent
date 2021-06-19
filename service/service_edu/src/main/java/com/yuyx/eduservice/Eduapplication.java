package com.yuyx.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//启动类
@ComponentScan(basePackages = {"com.yuyx"})
public class Eduapplication {
    String string;



    public static void main(String[] args) {
        SpringApplication.run(Eduapplication.class, args);

    }

}
