package com.petparade.api.service;

import com.petparade.api.dto.SignupRequestDto;
import com.petparade.api.dto.UserDto;

import java.util.List;

public interface UserService {
  UserDto findById(Long id);

  UserDto findByEmail(String email);

  List<UserDto> findAll();

  UserDto save(SignupRequestDto requestDto);

  UserDto update(UserDto userDto);

  void deleteById(Long id);
}
