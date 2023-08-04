package com.alfath.warnet.controller;

import com.alfath.warnet.model.request.BillingRequest;
import com.alfath.warnet.model.response.BillingResponse;
import com.alfath.warnet.model.response.CommonResponse;
import com.alfath.warnet.model.response.PagingResponse;
import com.alfath.warnet.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/billing")
public class BillingController {
    private final BillingService billingService;
    @PostMapping
    public ResponseEntity<?> createBilling(@RequestBody BillingRequest billing){
        BillingResponse billingResponse = billingService.create(billing);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully create billing")
                        .data(billingResponse)
                        .build()
                );
    }
    @GetMapping
    public ResponseEntity<?> getAllBilling(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Page<BillingResponse> billingResponses = billingService.getAllBillingisUsed(page - 1, size);

        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(billingResponses.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all used billing")
                        .data(billingResponses.getContent())
                        .paging(pagingResponse)
                        .build());

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> setBillingOffById(@PathVariable(name = "id") String id){
        billingService.setBillingOffbyPcId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully set off billing")
                        .build()
                );
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteBillingById(@PathVariable(name = "id")String id){
        billingService.deleteBillingById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete billing")
                        .build()
                );
    }

}

