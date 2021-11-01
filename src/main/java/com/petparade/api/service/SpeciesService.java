package com.petparade.api.service;

import com.petparade.api.dto.SpeciesDto;

import java.util.List;

public interface SpeciesService {
  List<SpeciesDto> findAll();

  SpeciesDto save(SpeciesDto speciesDto);
}
