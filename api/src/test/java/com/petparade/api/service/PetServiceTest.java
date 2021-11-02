package com.petparade.api.service;

import com.petparade.api.dto.PetDto;
import com.petparade.api.model.Pet;
import com.petparade.api.model.Rating;
import com.petparade.api.model.Species;
import com.petparade.api.model.User;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.repository.RatingRepository;
import com.petparade.api.repository.SpeciesRepository;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.impl.PetServiceImpl;
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

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
  @InjectMocks
  private PetServiceImpl petService;

  @Mock
  private PetRepository petRepository;
  @Mock
  private UserRepository userRepository;
  @Mock
  private SpeciesRepository speciesRepository;
  @Mock
  private RatingRepository ratingRepository;
  @Mock
  private PetDto petDto;
  @Mock
  private Pet pet;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);

    // Setup pet entity
    this.pet = new Pet();
    this.pet.setId(1L);
    this.pet.setName("Violet");
    this.pet.setBio("My childhood pet and best friend. RIP");
    this.pet.setBirthday(new Date(0));
    this.pet.setFlagged(false);

    User user = new User();
    user.setId(1L);
    this.pet.setUser(user);

    this.pet.setSpecies(new Species(1L, "DOG"));

    Set<User> userLikes = new HashSet<>();
    this.pet.setUserLikes(userLikes);

    Set<Rating> ratings = new HashSet<>();
    this.pet.setRatings(ratings);

    // Setup pet dto
    this.petDto = new PetDto(this.pet);
  }

  @Test
  public void findById() {
    when(petRepository.findById(anyLong())).thenReturn(Optional.ofNullable(pet));

    PetDto result = petService.findById(1L);

    assertEquals(new Long(1), result.getId());
    assertEquals("Violet", result.getName());
    assertEquals(new Long(1), result.getOwner());
    assertEquals("My childhood pet and best friend. RIP", result.getBio());
    assertEquals(new Long(1), result.getSpecies());
  }

  @Test
  public void findAll() {
    List<Pet> petList = Collections.singletonList(pet);

    when(petRepository.findAll()).thenReturn(petList);

    List<PetDto> result = petService.findAll();

    assertTrue(result.size() > 0);
  }

  @Test
  public void findRecentCreated() {
    Set<Pet> petSet = new HashSet<>();
    petSet.add(pet);

    when(petRepository.getRecentCreatedPets()).thenReturn(petSet);

    List<PetDto> result = petService.findRecentCreated();

    assertTrue(result.size() > 0);
  }

  @Test
  public void findAllBySpecies() {
    Set<Pet> petSet = new HashSet<>();
    petSet.add(pet);

    when(petRepository.getPetsBySpecies(anyLong())).thenReturn(petSet);

    List<PetDto> result = petService.findAllBySpecies(1L);

    assertTrue(result.size() > 0);
  }

  @Test
  public void findAllFlaggedPets() {
    Set<Pet> petSet = new HashSet<>();
    petSet.add(pet);

    when(petRepository.getAllFlaggedPets()).thenReturn(petSet);

    List<PetDto> result = petService.findAllFlaggedPets();

    assertTrue(result.size() > 0);
  }

  @Test
  public void save() {
    when(petRepository.save(any())).thenReturn(pet);

    PetDto result = petService.save(petDto);

    assertEquals(new Long(1), result.getId());
    assertEquals("Violet", result.getName());
    assertEquals(new Long(1), result.getOwner());
    assertEquals("My childhood pet and best friend. RIP", result.getBio());
    assertEquals(new Long(1), result.getSpecies());
  }
}
