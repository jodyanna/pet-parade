package com.petparade.api.controller;

import com.petparade.api.dto.LikeRequestDto;
import com.petparade.api.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "likes")
public class LikeController {
  private final LikeService likeService;

  @Autowired
  public LikeController(LikeService likeService) {
    this.likeService = likeService;
  }

  @PostMapping
  public ResponseEntity<String> save(@Valid @RequestBody LikeRequestDto likeRequestDto) {
    this.likeService.save(likeRequestDto);

    return ResponseEntity.ok().body("Success");
  }

  @DeleteMapping
  public ResponseEntity<String> delete(@Valid @RequestBody LikeRequestDto likeRequestDto) {
    this.likeService.delete(likeRequestDto);

    return ResponseEntity.ok().body("Success");
  }
}
