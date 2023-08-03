package com.alfath.warnet.repository;

import com.alfath.warnet.entity.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, String> {
    @Query(value = "SELECT * FROM m_computer WHERE id = :id",nativeQuery = true)
    Optional<Computer> findComputerById(@Param("id")String id);

    @Modifying
    @Query(value = "UPDATE m_computer SET name= :name, price= :price WHERE id= :id", nativeQuery = true)
    void updateComputer(@Param("id") String id ,@Param("name")String name, @Param("price")Long price);

    @Query(value = "SELECT * FROM m_computer", countQuery = "SELECT count(*) FROM m_computer", nativeQuery = true)
    Page<Computer> findAllComputersWithPagination(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM m_computer WHERE id = :id", nativeQuery = true)
    void deleteComputerById(@Param("id")String id);

}
