package com.petparade.api.dto;

import com.petparade.api.model.Pet;
import com.petparade.api.model.Rating;
import com.petparade.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
  private Long id;

  @NotEmpty(message = "Pet name cannot be empty")
  @Size(min = 1, max = 25, message = "Pet name must be between 1 - 25 characters")
  private String name;

  @Size(max = 100, message = "Pet bio cannot exceed 100 characters")
  private String bio;

  private Date birthday;

  private Boolean isFlagged;

  private Date dateCreated;

  private String image;

  private Long species;

  @NotNull(message = "All pets must have a corresponding owner")
  private Long owner;

  private List<RatingDto> ratings;

  private List<Long> likingUsers;

  private PetStatsDto stats;

  public PetDto(Pet pet) {
    this.setId(pet.getId());
    this.setName(pet.getName());
    this.setBio(pet.getBio());
    this.setBirthday(pet.getBirthday());
    this.setIsFlagged(pet.getFlagged());
    this.setDateCreated(pet.getDateCreated());
    this.setImage(pet.getImage());
    this.setSpecies(pet.getSpecies().getId());
    this.setOwner(pet.getUser().getId());

    // Set pet ratings with a list of rating dto
    List<RatingDto> petRatings = new ArrayList<>();
    for (Rating rating : pet.getRatings()) {
      petRatings.add(new RatingDto(rating));
    }
    this.setRatings(petRatings);

    // Set liking users with list of user ids
    List<Long> petLikingUsers = new ArrayList<>();
    for (User user : pet.getUserLikes()) {
      petLikingUsers.add(user.getId());
    }
    this.setLikingUsers(petLikingUsers);
  }
}
