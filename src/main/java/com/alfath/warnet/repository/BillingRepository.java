package com.alfath.warnet.repository;

import com.alfath.warnet.entity.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, String> {
    @Query(value = "SELECT * FROM t_billing WHERE is_used = true", nativeQuery = true)
    Page<Billing> findAllByIsUsedTrue(Pageable pageable);
    @Query(value = "SELECT * FROM t_billing WHERE id = :id AND is_used = true", nativeQuery = true)
    Optional<Billing> findByIdAndIsUsedTrue(String id);
    @Query(value = "SELECT * FROM t_billing WHERE end_at < :timestamp", nativeQuery = true)
    List<Billing> findAllByEndAtLessThan(Timestamp timestamp);

    @Query(value = "SELECT * FROM t_billing WHERE computer_id = :id AND is_used = true", nativeQuery = true)
    Optional<Billing> findByComputerIdAndIsUsedTrue(String id);


}
