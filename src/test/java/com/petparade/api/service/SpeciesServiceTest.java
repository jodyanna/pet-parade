package com.petparade.api.service;

import com.petparade.api.dto.SpeciesDto;
import com.petparade.api.model.Species;
import com.petparade.api.repository.SpeciesRepository;
import com.petparade.api.service.impl.SpeciesServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpeciesServiceTest {
  @InjectMocks
  private SpeciesServiceImpl speciesService;

  @Mock
  private SpeciesRepository speciesRepository;

  @Mock
  private SpeciesDto speciesDto;

  @Mock
  private Species species;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);

    this.speciesDto = new SpeciesDto(1L, "DOG");
    this.species = new Species(1L, "DOG");
  }

  @Test
  public void findAll() {
    List<Species> speciesList = Collections.singletonList(species);

    when(speciesRepository.findAll()).thenReturn(speciesList);

    List<SpeciesDto> result = speciesService.findAll();

    assertTrue(result.size() > 0);
  }

  @Test
  public void save() {
    when(speciesRepository.save(any())).thenReturn(species);

    SpeciesDto result = speciesService.save(speciesDto);

    assertEquals(new Long(1), result.getId());
    assertEquals("DOG", result.getName());
  }


}
