package com.petparade.api.repository;

import com.petparade.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
  @Query(value = "SELECT p FROM Pet AS p WHERE p.user.id=:id")
  Set<Pet> getAllPetsByUserId(@Param("id") Long id);

  @Query(value = "SELECT p FROM Pet AS p WHERE p.species.id=:id")
  Set<Pet> getPetsBySpecies(@Param("id") Long id);

  @Query(value = "SELECT * FROM `Pets` AS p ORDER BY `id` DESC LIMIT 2;", nativeQuery = true)
  Set<Pet> getRecentCreatedPets();

  @Query(value = "SELECT p FROM Pet AS p WHERE p.isFlagged=true")
  Set<Pet> getAllFlaggedPets();
}
