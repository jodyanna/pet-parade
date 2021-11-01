package com.petparade.api.service.impl;

import com.petparade.api.dto.SignupRequestDto;
import com.petparade.api.dto.UserDto;
import com.petparade.api.dto.UserStatsDto;
import com.petparade.api.exception.ResourceNotFoundException;
import com.petparade.api.model.Pet;
import com.petparade.api.model.Role;
import com.petparade.api.model.User;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.repository.RatingRepository;
import com.petparade.api.repository.RoleRepository;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PetRepository petRepository;
  private final RoleRepository roleRepository;
  private final RatingRepository ratingRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,
      PetRepository petRepository,
      RoleRepository roleRepository,
      RatingRepository ratingRepository,
      PasswordEncoder passwordEncoder
  ) {
    this.userRepository = userRepository;
    this.petRepository = petRepository;
    this.roleRepository = roleRepository;
    this.ratingRepository = ratingRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDto findById(Long id) {
    User user = this.userRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Could not find user with id: " + id));

    return setupUserDto(user);
  }

  @Override
  public UserDto findByEmail(String email) {
    User user = this.userRepository
        .findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Could not find user with email: " + email));

    return setupUserDto(user);
  }

  @Override
  public List<UserDto> findAll() {
    return this.userRepository
        .findAll()
        .stream()
        .map(this::setupUserDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto save(SignupRequestDto requestDto) {
    // Build new User entity
    User newUser = new User();
    newUser.setId(null);
    newUser.setUsername(requestDto.getUsername());
    newUser.setEmail(requestDto.getEmail());
    // Encode new user password
    newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
    newUser.setDateCreated(new Date());
    newUser.setPets(new HashSet<>());
    newUser.setLikedPets(new HashSet<>());
    newUser.setRatings(new HashSet<>());

    // Persist user
    User savedUser = this.userRepository.save(newUser);

    // Persist new user role
    Role userRole = new Role("ROLE_USER", savedUser);
    this.roleRepository.save(userRole);

    // Add role (in a set) to the saved user
    Set<Role> userRoles = new HashSet<>(Collections.emptySet());
    userRoles.add(userRole);
    savedUser.setRoles(userRoles);

    return setupUserDto(savedUser);
  }

  @Override
  public UserDto update(UserDto userDto) {
    User user = this.userRepository.findById(userDto.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Could not find user with id: " + userDto.getId()));
    user.setUsername(userDto.getUsername());
    user.setCity(userDto.getCity());
    user.setState(userDto.getState());

    User updatedUser = this.userRepository.save(user);

    return setupUserDto(updatedUser);
  }

  @Override
  public void deleteById(Long id) {
    this.userRepository.deleteById(id);
  }

  // Utilities

  /**
   * Set up User data transfer object. User stats are calculated and set here.
   * @param user - user entity object
   * @return - user data transfer object
   */
  private UserDto setupUserDto(User user) {
    UserDto userDto = new UserDto(user);
    UserStatsDto userStatsDto = new UserStatsDto();

    userStatsDto.setPetCount(user.getPets().size());
    userStatsDto.setPetLikesCount(countUserPetsLikes(user.getPets()));
    userStatsDto.setLikesGivenCount(user.getLikedPets().size());
    userStatsDto.setPetRatingsCount(countUserPetsRatings(user.getPets()));
    userStatsDto.setRatingsGivenCount(user.getRatings().size());

    userDto.setStats(userStatsDto);

    return userDto;
  }

  private Integer countUserPetsLikes(Set<Pet> pets) {
    Integer count = 0;

    for (Pet pet : pets) {
      count += pet.getUserLikes().size();
    }

    return count;
  }

  private Integer countUserPetsRatings(Set<Pet> pets) {
    Integer count = 0;

    for (Pet pet : pets) {
      count += pet.getRatings().size();
    }

    return count;
  }

  private User dtoToEntity(UserDto userDto) {
    User user = new User();

    user.setId(userDto.getId());
    user.setUsername(userDto.getUsername());
    user.setEmail(userDto.getEmail());
    user.setCity(user.getCity());
    user.setState(user.getState());

    if (userDto.getDateCreated() == null) {
      user.setDateCreated(new Date());
    } else {
      user.setDateCreated(userDto.getDateCreated());
    }

    // user.setDateModified();

    // roles
    if (userDto.getRoles() != null) {
      user.setRoles(this.roleRepository.getRolesByUserId(userDto.getId()));
    }

    // pets
    user.setPets(this.petRepository.getAllPetsByUserId(userDto.getId()));

    // likedPets
    Set<Pet> likedPets = new HashSet<>(Collections.emptySet());
    if (userDto.getLikedPets() != null) {
      for (Long id : userDto.getLikedPets()) {
        this.petRepository.findById(id).ifPresent(likedPets::add);
      }
    }
    user.setLikedPets(likedPets);

    // ratings
    user.setRatings(this.ratingRepository.getAllRatingsByUserId(userDto.getId()));

    return user;
  }
}
