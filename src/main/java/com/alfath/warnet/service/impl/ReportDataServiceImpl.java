package com.alfath.warnet.service.impl;

import com.alfath.warnet.entity.ReportData;
import com.alfath.warnet.model.response.ReportDataResponse;
import com.alfath.warnet.repository.ReportDataRepository;
import com.alfath.warnet.service.ReportDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportDataServiceImpl implements ReportDataService {
    private final ReportDataRepository reportDataRepository;

    @Override
    public ReportData create(ReportData reportData) {
        try {
            return reportDataRepository.saveAndFlush(reportData);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Reportdata already used");
        }
    }

    @Override
    public Page<ReportDataResponse> getAllReportData(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ReportData> reportData = reportDataRepository.findAll(pageable);

        List<ReportData> reportDataList = new ArrayList<>(reportData.getContent());
        List<ReportDataResponse> reportDataResponses = reportDataList.stream().map(report -> ReportDataResponse.builder()
                .customerName(report.getCustomerName())
                .ComputerName(report.getComputerName())
                .rentalTime(report.getRentalTime())
                .total_price(report.getTotal_price())
                .createAt(report.getCreateAt())
                .build()).collect(Collectors.toList());

        return new PageImpl<>(reportDataResponses, pageable, reportData.getTotalElements());
    }
}
