package com.petparade.api.dto;

import com.petparade.api.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
  private Integer rating;
  private Long ratingUser;
  private Long ratedPet;
  private Date dateCreated;

  public RatingDto(Rating rating) {
    this.rating = rating.getRating();
    this.ratingUser = rating.getUser().getId();
    this.ratedPet = rating.getPet().getId();
    this.dateCreated = rating.getDateCreated();
  }
}
