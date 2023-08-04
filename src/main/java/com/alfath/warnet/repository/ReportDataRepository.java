package com.alfath.warnet.repository;

import com.alfath.warnet.entity.ReportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDataRepository extends JpaRepository<ReportData, String> {
}
