package com.alfath.warnet.repository;

import com.alfath.warnet.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "SELECT * FROM m_customer", countQuery = "SELECT count(*) FROM m_customer", nativeQuery = true)
    Page<Customer> findAllCustomersWithPagination(Pageable pageable);

    @Query(value = "SELECT * FROM m_customer WHERE id= :id", nativeQuery = true)
    Optional<Customer> findById(@Param("id") String id);

    @Modifying
    @Query(value = "DELETE FROM m_customer WHERE id= :id", nativeQuery = true)
    void deleteById(@Param("id") String id);
}
