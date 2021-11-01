package com.petparade.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
  @NotEmpty(message = "Email is required")
  private String email;

  @NotEmpty(message = "Username is required")
  private String password;
}
