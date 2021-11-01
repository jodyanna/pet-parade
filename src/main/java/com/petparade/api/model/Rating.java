package com.petparade.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Ratings")
public class Rating {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "rating")
  private Integer rating;

  @Column(name = "dateCreated")
  @Temporal(TemporalType.DATE)
  private Date dateCreated;

  @JsonBackReference(value = "ratingUser")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "FK_Ratings_Users")
  private User user;

  @JsonBackReference(value = "ratedPet")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "FK_Ratings_Pets")
  private Pet pet;

  // Constructors
  public Rating() {}

  public Rating(Integer rating, Date dateCreated, User user, Pet pet) {
    this.rating = rating;
    this.dateCreated = dateCreated;
    this.user = user;
    this.pet = pet;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public Integer getRating() {
    return rating;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public User getUser() {
    return user;
  }

  public Pet getPet() {
    return pet;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Rating rating1 = (Rating) o;
    return getId().equals(rating1.getId()) && getRating().equals(rating1.getRating()) && getDateCreated().equals(rating1.getDateCreated()) && getUser().equals(rating1.getUser()) && getPet().equals(rating1.getPet());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getRating(), getDateCreated());
  }

  @Override
  public String toString() {
    return "Rating{" +
        "id=" + id +
        ", rating=" + rating +
        ", dateCreated=" + dateCreated +
        ", user=" + user +
        ", pet=" + pet +
        '}';
  }
}

