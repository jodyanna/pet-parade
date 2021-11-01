package com.petparade.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.petparade.api.repository")
public class PetParadeApplication {

  public static void main(String[] args) {
    SpringApplication.run(PetParadeApplication.class, args);
  }

}
