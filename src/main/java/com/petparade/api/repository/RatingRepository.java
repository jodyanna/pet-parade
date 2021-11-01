package com.petparade.api.repository;

import com.petparade.api.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
  @Query(value = "SELECT r FROM Rating AS r WHERE r.user.id=:id")
  Set<Rating> getAllRatingsByUserId(@Param(value = "id") Long id);

  @Query(value = "SELECT r FROM Rating AS r WHERE r.pet.id=:id")
  Set<Rating> getAllRatingsByPetId(@Param(value = "id") Long id);

  @Query(value = "SELECT AVG(r.rating) FROM Rating AS r WHERE r.pet.id=:id")
  Double getPetRating(@Param("id") Long id);
}
