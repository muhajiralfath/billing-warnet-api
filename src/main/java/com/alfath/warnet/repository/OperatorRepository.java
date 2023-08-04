package com.alfath.warnet.repository;

import com.alfath.warnet.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, String> {
    @Query(value = "SELECT * FROM m_operator WHERE id= :id", nativeQuery = true)
    Optional<Operator> findOperatorById(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM m_operator WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") String id);
}
