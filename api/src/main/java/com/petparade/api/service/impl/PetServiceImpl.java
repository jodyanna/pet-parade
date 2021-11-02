package com.petparade.api.service.impl;

import com.petparade.api.dto.PetDto;
import com.petparade.api.dto.PetStatsDto;
import com.petparade.api.exception.ResourceNotFoundException;
import com.petparade.api.model.Pet;
import com.petparade.api.model.Species;
import com.petparade.api.model.User;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.repository.RatingRepository;
import com.petparade.api.repository.SpeciesRepository;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {
  private final PetRepository petRepository;
  private final UserRepository userRepository;
  private final SpeciesRepository speciesRepository;
  private final RatingRepository ratingRepository;

  @Autowired
  public PetServiceImpl(
      PetRepository petRepository,
      UserRepository userRepository,
      SpeciesRepository speciesRepository,
      RatingRepository ratingRepository
  ) {
    this.petRepository = petRepository;
    this.userRepository = userRepository;
    this.speciesRepository = speciesRepository;
    this.ratingRepository = ratingRepository;
  }

  @Override
  public PetDto findById(Long id) {
    Pet pet = this.petRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Could not find pet with id: " + id));

    return setupPetDto(pet);
  }

  @Override
  public List<PetDto> findAll() {
    return this.petRepository
        .findAll()
        .stream()
        .map(this::setupPetDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<PetDto> findRecentCreated() {
    return this.petRepository
        .getRecentCreatedPets()
        .stream()
        .map(this::setupPetDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<PetDto> findAllBySpecies(Long id) {
    return this.petRepository
        .getPetsBySpecies(id)
        .stream()
        .map(this::setupPetDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<PetDto> findAllFlaggedPets() {
    return this.petRepository
        .getAllFlaggedPets()
        .stream()
        .map(this::setupPetDto)
        .collect(Collectors.toList());
  }

  @Override
  public PetDto save(PetDto petDto) {
    Pet pet = this.dtoToEntity(petDto);
    Pet savedPet = this.petRepository.save(pet);

    return new PetDto(savedPet);
  }

  @Override
  public void deleteById(Long id) {
    this.petRepository.deleteById(id);
  }

  // Utility methods
  /**
   * Sets up a new PetDto. The important thing here is that the stats for a pet get queried and set in addition
   * to the pet entity.
   * @param pet - pet entity object
   * @return - pet data transfer object
   */
  private PetDto setupPetDto(Pet pet) {
    PetDto petDto = new PetDto(pet);
    PetStatsDto petStatsDto = new PetStatsDto();

    petStatsDto.setRating(this.ratingRepository.getPetRating(pet.getId()));
    petStatsDto.setLikes(pet.getUserLikes().size());
    petDto.setStats(petStatsDto);

    return petDto;
  }

  private Pet dtoToEntity(PetDto petDto) {
    Pet pet = new Pet();

    pet.setId(petDto.getId());
    pet.setName(petDto.getName());
    pet.setBio(petDto.getBio());
    pet.setBirthday(petDto.getBirthday());
    pet.setFlagged(petDto.getIsFlagged());

    if (petDto.getDateCreated() == null) {
      pet.setDateCreated(new Date());
    } else {
      pet.setDateCreated(petDto.getDateCreated());
    }

    // pet.setDateModified(petDto.getDateModified());

    // pet.setImage(petDto.getImage());

    // Set pet owner (user)
    Optional<User> user = this.userRepository.findById(petDto.getOwner());
    user.ifPresent(pet::setUser);

    // Set pet species
    Optional<Species> species = this.speciesRepository.findById(petDto.getSpecies());
    species.ifPresent(pet::setSpecies);

    // Set liking users
    Set<User> userLikes = new HashSet<>(Collections.emptySet());
    if (petDto.getLikingUsers() != null) {
      for (Long id : petDto.getLikingUsers()) {
        this.userRepository.findById(id).ifPresent(userLikes::add);
      }
    }
    pet.setUserLikes(userLikes);

    pet.setRatings(this.ratingRepository.getAllRatingsByPetId(petDto.getId()));

    return pet;
  }
}
