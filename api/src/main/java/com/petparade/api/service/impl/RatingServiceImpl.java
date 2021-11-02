package com.petparade.api.service.impl;

import com.petparade.api.dto.RatingDto;
import com.petparade.api.dto.RatingRequestDto;
import com.petparade.api.exception.ResourceNotFoundException;
import com.petparade.api.model.Pet;
import com.petparade.api.model.Rating;
import com.petparade.api.model.User;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.repository.RatingRepository;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RatingServiceImpl implements RatingService {
  private final RatingRepository ratingRepository;
  private final PetRepository petRepository;
  private final UserRepository userRepository;

  @Autowired
  public RatingServiceImpl(
      RatingRepository ratingRepository,
      PetRepository petRepository,
      UserRepository userRepository
  ) {
    this.ratingRepository = ratingRepository;
    this.petRepository = petRepository;
    this.userRepository = userRepository;
  }

  @Override
  public RatingDto save(RatingRequestDto ratingRequestDto) {
    Rating rating = dtoToEntity(ratingRequestDto);
    Rating savedRating = this.ratingRepository.save(rating);

    return new RatingDto(savedRating);
  }

  /**
   * Converts a rating request data transfer object into a rating entity. New data created gets initialized here.
   * @param ratingRequestDto - the data sent from client rating form
   * @return - rating entity
   */
  private Rating dtoToEntity(RatingRequestDto ratingRequestDto) {
    Rating rating = new Rating();

    rating.setRating(ratingRequestDto.getRating());
    rating.setDateCreated(new Date());

    // Get rated pet
    Pet pet = this.petRepository.findById(ratingRequestDto.getPetId())
            .orElseThrow(() -> new ResourceNotFoundException("Could not find pet with id: " + ratingRequestDto.getPetId()));
    rating.setPet(pet);

    // Get rating user
    User user = this.userRepository.findById(ratingRequestDto.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("Could not find user with id: " + ratingRequestDto.getUserId()));
    rating.setUser(user);

    return rating;
  }
}
