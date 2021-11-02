package com.petparade.api.controller;

import com.petparade.api.dto.RatingDto;
import com.petparade.api.dto.RatingRequestDto;
import com.petparade.api.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("ratings")
public class RatingController {
  private final RatingService ratingService;

  @Autowired
  public RatingController(RatingService ratingService) {
    this.ratingService = ratingService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public RatingDto save(@Valid @RequestBody RatingRequestDto ratingRequestDto) {
    return this.ratingService.save(ratingRequestDto);
  }
}
