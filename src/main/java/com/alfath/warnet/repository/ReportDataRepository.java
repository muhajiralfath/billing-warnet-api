package com.alfath.warnet.repository;

import com.alfath.warnet.entity.ReportData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDataRepository extends JpaRepository<ReportData, String> {
    @Query(value = "SELECT * FROM t_report", nativeQuery = true)
    Page<ReportData> findAllReportData(Pageable pageable);

}
