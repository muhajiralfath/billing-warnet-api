package com.alfath.warnet.repository;

import com.alfath.warnet.entity.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, String> {
    Page<Billing> findAllByIsUsedTrue(Pageable pageable);
    Optional<Billing> findByIdAndIsUsedTrue(String id);
}
