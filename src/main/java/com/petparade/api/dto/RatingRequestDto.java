package com.petparade.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequestDto {
  @NotNull
  @Min(message = "Rating must be between 1 -5", value = 1)
  @Max(message = "Rating must be between 1 -5", value = 5)
  private Integer rating;

  @NotNull(message = "userId is required")
  private Long userId;

  @NotNull(message = "petId is required")
  private Long petId;
}
