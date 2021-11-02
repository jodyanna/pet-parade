package com.petparade.api.service;

import com.petparade.api.dto.RatingDto;
import com.petparade.api.dto.RatingRequestDto;

public interface RatingService {
  RatingDto save(RatingRequestDto ratingRequestDto);
}
