package com.edu.fly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlyApplication {

    public static void main(String[] args){
        System.out.println("Hello Flu Education");
        SpringApplication.run(FlyApplication.class, args);
    }
}
