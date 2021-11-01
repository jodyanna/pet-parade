package com.petparade.api.service;

import com.petparade.api.dto.LikeRequestDto;
import com.petparade.api.model.Pet;
import com.petparade.api.model.User;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.impl.LikeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * This test is incomplete.
 * TODO: Setup and get return from save method, check that update occurred.
 * TODO: Setup and get return from delete method, check that change occurred.
 */
@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {
  @InjectMocks
  private LikeServiceImpl likeService;

  @Mock
  private UserRepository userRepository;
  @Mock
  private PetRepository petRepository;
  @Mock
  private User user;
  @Mock
  private Pet pet;
  @Mock
  private LikeRequestDto likeRequestDto;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);

    this.user = new User();
    this.user.setId(1L);

    this.pet = new Pet();
    this.pet.setId(1L);

    Set<User> userLikes = new HashSet<>();
    this.pet.setUserLikes(userLikes);

    Set<Pet> likedPets = new HashSet<>();
    this.user.setLikedPets(likedPets);

    this.likeRequestDto = new LikeRequestDto(1L, 1L);
  }

  @Test
  public void save() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
    when(petRepository.findById(anyLong())).thenReturn(Optional.ofNullable(pet));
    when(userRepository.save(any())).thenReturn(null);
    when(petRepository.save(any())).thenReturn(null);
  }

}
