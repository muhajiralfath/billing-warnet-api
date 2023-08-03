package com.alfath.warnet.repository;

import com.alfath.warnet.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(value = "SELECT * FROM m_role WHERE role = :role", nativeQuery = true)
    Optional<Role> findByRoleName(@Param("role") String role);

}
