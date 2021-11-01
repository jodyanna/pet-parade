package com.petparade.api.service.impl;

import com.petparade.api.dto.LikeRequestDto;
import com.petparade.api.model.Pet;
import com.petparade.api.model.User;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LikeServiceImpl implements LikeService {
  private final UserRepository userRepository;
  private final PetRepository petRepository;

  @Autowired
  public LikeServiceImpl(UserRepository userRepository, PetRepository petRepository) {
    this.userRepository = userRepository;
    this.petRepository = petRepository;
  }

  @Override
  public void save(LikeRequestDto likeRequestDto) {
    User ratingUser = this.userRepository.getById(likeRequestDto.getUserId());
    Pet likedPet = this.petRepository.getById(likeRequestDto.getPetId());

    // Add rating user to pet
    likedPet.getUserLikes().add(ratingUser);
    // Add liked pet to user
    ratingUser.getLikedPets().add(likedPet);

    // Persist both user and pet
    this.userRepository.save(ratingUser);
    this.petRepository.save(likedPet);
  }

  @Override
  public void delete(LikeRequestDto likeRequestDto) {
    User ratingUser = this.userRepository.getById(likeRequestDto.getUserId());
    Pet likedPet = this.petRepository.getById(likeRequestDto.getPetId());

    // remove rating user from pet
    Set<User> userLikes = likedPet.getUserLikes();
    userLikes.remove(ratingUser);
    likedPet.setUserLikes(userLikes);

    // remove liked pet from user
    Set<Pet> likedPets = ratingUser.getLikedPets();
    likedPets.remove(likedPet);
    ratingUser.setLikedPets(likedPets);

    // Persist both user and pet
    this.userRepository.save(ratingUser);
    this.petRepository.save(likedPet);
  }
}
