package com.petparade.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatsDto {
  private Integer petCount;
  private Integer petLikesCount;
  private Integer likesGivenCount;
  private Integer ratingsGivenCount;
  private Integer petRatingsCount;
}
