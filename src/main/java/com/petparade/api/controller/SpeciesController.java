package com.petparade.api.controller;

import com.petparade.api.dto.SpeciesDto;
import com.petparade.api.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("species")
public class SpeciesController {
  private final SpeciesService speciesService;

  @Autowired
  public SpeciesController(SpeciesService speciesService) {
    this.speciesService = speciesService;
  }

  @GetMapping
  public List<SpeciesDto> getAllSpecies() {
    return this.speciesService.findAll();
  }

  @PostMapping("save")
  public SpeciesDto save(@Valid @RequestBody SpeciesDto speciesDto) {
    speciesDto.setId(null);

    return this.speciesService.save(speciesDto);
  }

  @PutMapping("update")
  public SpeciesDto update(@Valid @RequestBody SpeciesDto speciesDto) {
    return this.speciesService.save(speciesDto);
  }
}
