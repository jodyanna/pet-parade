package com.petparade.api.service;

import com.petparade.api.dto.LikeRequestDto;

public interface LikeService {
  void save(LikeRequestDto likeRequestDto);
  void delete(LikeRequestDto likeRequestDto);
}
