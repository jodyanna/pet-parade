package com.petparade.api.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "dateCreated")
  @Temporal(TemporalType.DATE)
  private Date dateCreated;

  @Column(name = "dateModified")
  @Temporal(TemporalType.DATE)
  private Date dateModified;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private Set<Role> roles;

  @OneToMany(mappedBy = "user")
  private Set<Pet> pets;

  @ManyToMany
  @JoinTable(
      name = "Likes",
      joinColumns = @JoinColumn(name = "FK_Likes_Users"),
      inverseJoinColumns = @JoinColumn(name = "FK_Likes_Pets")
  )
  private Set<Pet> likedPets;

  @OneToMany(mappedBy = "user")
  private Set<Rating> ratings;

  // Constructors
  public User() {}

  public User(Long id, String username, String email, String password, String city, String state, Date dateCreated, Date dateModified, Set<Role> roles, Set<Pet> pets, Set<Pet> likedPets, Set<Rating> ratings) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.city = city;
    this.state = state;
    this.dateCreated = dateCreated;
    this.dateModified = dateModified;
    this.roles = roles;
    this.pets = pets;
    this.likedPets = likedPets;
    this.ratings = ratings;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public Date getDateModified() {
    return dateModified;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public Set<Pet> getPets() {
    return pets;
  }

  public Set<Pet> getLikedPets() {
    return likedPets;
  }

  public Set<Rating> getRatings() {
    return ratings;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public void setDateModified(Date dateModified) {
    this.dateModified = dateModified;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void setPets(Set<Pet> pets) {
    this.pets = pets;
  }

  public void setLikedPets(Set<Pet> likedPets) {
    this.likedPets = likedPets;
  }

  public void setRatings(Set<Rating> ratings) {
    this.ratings = ratings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getCity(), user.getCity()) && Objects.equals(getState(), user.getState()) && Objects.equals(getDateCreated(), user.getDateCreated()) && Objects.equals(getDateModified(), user.getDateModified()) && Objects.equals(getRoles(), user.getRoles()) && Objects.equals(getPets(), user.getPets()) && Objects.equals(getLikedPets(), user.getLikedPets()) && Objects.equals(getRatings(), user.getRatings());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), getCity(), getState(), getDateCreated(), getDateModified());
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", dateCreated=" + dateCreated +
        ", dateModified=" + dateModified +
        ", roles=" + roles +
        '}';
  }
}

