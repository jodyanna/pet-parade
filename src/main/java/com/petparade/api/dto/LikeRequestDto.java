package com.petparade.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDto {
  @NotNull(message = "petId is required")
  private Long petId;

  @NotNull(message = "userId is required")
  private Long userId;
}
