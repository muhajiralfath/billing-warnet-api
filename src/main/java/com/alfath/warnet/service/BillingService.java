package com.alfath.warnet.service;

import com.alfath.warnet.entity.Billing;
import com.alfath.warnet.model.response.BillingResponse;
import org.springframework.data.domain.Page;

public interface BillingService {
    BillingResponse create(Billing billing);
    Page<BillingResponse> getAllBilling(Integer page, Integer size);
    BillingResponse setBillingOff(Billing billing);
    void deleteBillingById(String id);
    BillingResponse updateBilling(Billing billing);
}
