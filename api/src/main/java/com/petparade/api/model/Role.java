package com.petparade.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "role")
  private String role;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "FK_Roles_Users")
  private User user;

  // Constructors
  public Role() {}

  public Role(String role, User user) {
    this.role = role;
    this.user = user;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getRole() {
    return role;
  }

  public User getUser() {
    return user;
  }

  // Setters
  public void setId(Long id) {
    this.id = id;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role role1 = (Role) o;
    return getId().equals(role1.getId()) && getRole().equals(role1.getRole()) && getUser().equals(role1.getUser());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getRole());
  }

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", role='" + role + '\'' +
        '}';
  }
}

