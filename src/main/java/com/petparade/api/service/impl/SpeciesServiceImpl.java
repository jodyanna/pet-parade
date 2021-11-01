package com.petparade.api.service.impl;

import com.petparade.api.dto.SpeciesDto;
import com.petparade.api.model.Species;
import com.petparade.api.repository.SpeciesRepository;
import com.petparade.api.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeciesServiceImpl implements SpeciesService {
  private final SpeciesRepository speciesRepository;

  @Autowired
  public SpeciesServiceImpl(SpeciesRepository speciesRepository) {
    this.speciesRepository = speciesRepository;
  }

  @Override
  public List<SpeciesDto> findAll() {
    return this.speciesRepository
        .findAll()
        .stream()
        .map(SpeciesDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public SpeciesDto save(SpeciesDto speciesDto) {
    // Convert dto to entity
    Species species = new Species();
    species.setId(speciesDto.getId());
    species.setName(speciesDto.getName());

    Species savedSpecies = this.speciesRepository.save(species);

    return new SpeciesDto(savedSpecies);
  }
}
