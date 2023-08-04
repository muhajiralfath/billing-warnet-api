package com.alfath.warnet.repository;

import com.alfath.warnet.entity.Customer;
import com.alfath.warnet.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, String> {
    @Query(value = "SELECT * FROM m_operator WHERE id= :id", nativeQuery = true)
    Optional<Operator> findOperatorById(@Param("id") String id);
}
