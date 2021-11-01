package com.petparade.api.dto;

import com.petparade.api.model.Species;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesDto {
  private Long id;
  private String name;

  public SpeciesDto(Species species) {
    this.setId(species.getId());
    this.setName(species.getName());
  }
}
