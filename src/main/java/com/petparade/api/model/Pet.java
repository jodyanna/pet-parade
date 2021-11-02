package com.petparade.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Pets")
public class Pet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "bio")
  private String bio;

  @Column(name = "birthday")
  @Temporal(TemporalType.DATE)
  private Date birthday;

  @Column(name = "isFlagged")
  private Boolean isFlagged;

  @Column(name = "dateCreated")
  @Temporal(TemporalType.DATE)
  private Date dateCreated;

  @Column(name = "dateModified")
  @Temporal(TemporalType.DATE)
  private Date dateModified;

  @Column(name = "image")
  private String image;

  @JsonBackReference(value = "petOwner")
  @ManyToOne
  @JoinColumn(name = "FK_Pets_Users")
  private User user;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "FK_Pets_Species", referencedColumnName = "id")
  private Species species;

  @ManyToMany(mappedBy = "likedPets", cascade = CascadeType.ALL)
  private Set<User> userLikes;

  @OneToMany(mappedBy = "pet")
  private Set<Rating> ratings;

  // Constructors
  public Pet() {}

  public Pet(String name, String bio, Date birthday, Boolean isFlagged, Date dateCreated, Date dateModified, String image, User user, Species species, Set<User> userLikes, Set<Rating> ratings) {
    this.name = name;
    this.bio = bio;
    this.birthday = birthday;
    this.isFlagged = isFlagged;
    this.dateCreated = dateCreated;
    this.dateModified = dateModified;
    this.image = image;
    this.user = user;
    this.species = species;
    this.userLikes = userLikes;
    this.ratings = ratings;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBio() {
    return bio;
  }

  public Date getBirthday() {
    return birthday;
  }

  public Boolean getFlagged() {
    return isFlagged;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public Date getDateModified() {
    return dateModified;
  }

  public String getImage() {
    return image;
  }

  public User getUser() {
    return user;
  }

  public Species getSpecies() {
    return species;
  }

  public Set<User> getUserLikes() {
    return userLikes;
  }

  public Set<Rating> getRatings() {
    return ratings;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public void setFlagged(Boolean flagged) {
    isFlagged = flagged;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public void setDateModified(Date dateModified) {
    this.dateModified = dateModified;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setSpecies(Species species) {
    this.species = species;
  }

  public void setUserLikes(Set<User> userLikes) {
    this.userLikes = userLikes;
  }

  public void setRatings(Set<Rating> ratings) {
    this.ratings = ratings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pet pet = (Pet) o;
    return Objects.equals(getId(), pet.getId()) && Objects.equals(getName(), pet.getName()) && Objects.equals(getBio(), pet.getBio()) && Objects.equals(getBirthday(), pet.getBirthday()) && Objects.equals(isFlagged, pet.isFlagged) && Objects.equals(getDateCreated(), pet.getDateCreated()) && Objects.equals(getDateModified(), pet.getDateModified()) && Objects.equals(getImage(), pet.getImage()) && Objects.equals(getUser(), pet.getUser()) && Objects.equals(getSpecies(), pet.getSpecies()) && Objects.equals(getUserLikes(), pet.getUserLikes()) && Objects.equals(getRatings(), pet.getRatings());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getBio(), getBirthday(), isFlagged, getDateCreated(), getDateModified(), getImage());
  }

  @Override
  public String toString() {
    return "Pet{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", bio='" + bio + '\'' +
        ", birthday=" + birthday +
        ", isFlagged=" + isFlagged +
        ", dateCreated=" + dateCreated +
        ", dateModified=" + dateModified +
        ", image='" + image + '\'' +
        '}';
  }
}

