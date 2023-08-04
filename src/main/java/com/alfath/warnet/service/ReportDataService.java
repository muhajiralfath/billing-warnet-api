package com.alfath.warnet.service;

import com.alfath.warnet.entity.ReportData;
import com.alfath.warnet.model.response.ReportDataResponse;
import org.springframework.data.domain.Page;


public interface ReportDataService {
    ReportData create(ReportData reportData);
    Page<ReportDataResponse> getAllReportData(Integer page, Integer size);


}
