package com.petparade.api.service;

import com.petparade.api.dto.RatingDto;
import com.petparade.api.dto.RatingRequestDto;
import com.petparade.api.model.Pet;
import com.petparade.api.model.Rating;
import com.petparade.api.model.User;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.repository.RatingRepository;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.impl.RatingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
  @InjectMocks
  private RatingServiceImpl ratingService;

  @Mock
  private RatingRepository ratingRepository;
  @Mock
  private PetRepository petRepository;
  @Mock
  private UserRepository userRepository;
  @Mock
  private Rating rating;
  @Mock
  private RatingRequestDto ratingRequestDto;
  @Mock
  private User user;
  @Mock
  private Pet pet;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);

    this.rating = new Rating();
    this.rating.setId(1L);
    this.rating.setRating(3);
    this.rating.setDateCreated(new Date(1));

    this.user = new User();
    this.user.setId(1L);
    this.rating.setUser(user);

    this.pet = new Pet();
    this.pet.setId(1L);
    this.rating.setPet(this.pet);

    this.ratingRequestDto = new RatingRequestDto(3, 1L, 1L);
  }

  @Test
  public void save() {
    when(ratingRepository.save(any())).thenReturn(rating);
    when(petRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(pet));
    when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

    RatingDto result = ratingService.save(ratingRequestDto);

    assertEquals(new Integer(3), result.getRating());
    assertEquals(new Long(1), result.getRatingUser());
    assertEquals(new Long(1), result.getRatedPet());
  }
}
