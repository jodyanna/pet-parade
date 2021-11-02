package com.petparade.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Species")
public class Species {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  // Constructors
  public Species() {}

  public Species(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Species species = (Species) o;
    return getName().equals(species.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  @Override
  public String toString() {
    return "Species{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}

