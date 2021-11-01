package com.petparade.api.service;

import com.petparade.api.dto.PetDto;

import java.util.List;

public interface PetService {
  PetDto findById(Long id);

  List<PetDto> findAll();

  List<PetDto> findRecentCreated();

  List<PetDto> findAllFlaggedPets();

  List<PetDto> findAllBySpecies(Long id);

  PetDto save(PetDto petDto);

  void deleteById(Long id);
}
