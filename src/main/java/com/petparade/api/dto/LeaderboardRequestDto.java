package com.petparade.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardRequestDto {
  @Min(message = "Species must be a positive number", value = 0)
  private Long species;
}
