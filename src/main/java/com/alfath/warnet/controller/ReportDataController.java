package com.alfath.warnet.controller;

import com.alfath.warnet.model.response.BillingResponse;
import com.alfath.warnet.model.response.CommonResponse;
import com.alfath.warnet.model.response.PagingResponse;
import com.alfath.warnet.model.response.ReportDataResponse;
import com.alfath.warnet.service.ReportDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/report")
public class ReportDataController {
    private final ReportDataService reportDataService;
    @GetMapping
    public ResponseEntity<?> getAllBilling(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Page<ReportDataResponse> reportDataResponses = reportDataService.getAllReportData(page - 1, size);

        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(reportDataResponses.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all report data")
                        .data(reportDataResponses.getContent())
                        .paging(pagingResponse)
                        .build());

    }
}
