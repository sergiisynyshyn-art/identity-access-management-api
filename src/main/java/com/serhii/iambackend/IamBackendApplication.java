package com.serhii.iambackend;

import domain.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IamBackendApplication {

    public static void main(String[] args) {

        User user = User.builder()
                .email("test@test.com")
                .enabled(true)
                .build();

        System.out.println(user.getEmail());

        SpringApplication.run(IamBackendApplication.class, args);
    }
}
