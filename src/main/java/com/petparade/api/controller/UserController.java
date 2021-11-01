package com.petparade.api.controller;

import com.petparade.api.dto.SignupRequestDto;
import com.petparade.api.dto.UserDto;
import com.petparade.api.dto.LoginRequestDto;
import com.petparade.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserDto> getAllUsers() {
    return this.userService.findAll();
  }

  @GetMapping("{id}")
  public UserDto getUserById(@PathVariable Long id) {
    return this.userService.findById(id);
  }

  @PostMapping(value = "login")
  public UserDto loginUser(@Valid @RequestBody LoginRequestDto request) {
    return this.userService.findByEmail(request.getEmail());
  }

  @PostMapping(value = "signup")
  public UserDto save(@Valid @RequestBody SignupRequestDto requestDto) {
    return this.userService.save(requestDto);
  }

  @PutMapping
  public UserDto update(@Valid @RequestBody UserDto userDto) {
    return this.userService.update(userDto);
  }

  @DeleteMapping("{id}")
  public void deleteUserById(@PathVariable Long id) {
    this.userService.deleteById(id);
  }

}
