package com.petparade.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
  @NotEmpty(message = "Username is required")
  private String username;

  @NotEmpty(message = "Email is required")
  private String email;

  @NotEmpty(message = "Password is required")
  private String password;
}
