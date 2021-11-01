package com.petparade.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

  @GetMapping
  public String greeting() {
    return "Hi! Welcome to the Pet Parade API.";
  }
}
