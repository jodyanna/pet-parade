package com.petparade.api.repository;

import com.petparade.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  @Query(value = "SELECT r FROM Role AS r WHERE r.user.id=:id")
  Set<Role> getRolesByUserId(@Param("id") Long id);
}
